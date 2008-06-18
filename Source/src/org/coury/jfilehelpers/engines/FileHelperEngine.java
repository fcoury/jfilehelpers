/*
 * FileHelperEngine.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.coury.jfilehelpers.engines;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.coury.jfilehelpers.core.ForwardReader;
import org.coury.jfilehelpers.events.AfterReadRecordEventArgs;
import org.coury.jfilehelpers.events.AfterReadRecordHandler;
import org.coury.jfilehelpers.events.AfterWriteRecordEventArgs;
import org.coury.jfilehelpers.events.AfterWriteRecordHandler;
import org.coury.jfilehelpers.events.BeforeReadRecordEventArgs;
import org.coury.jfilehelpers.events.BeforeReadRecordHandler;
import org.coury.jfilehelpers.events.BeforeWriteRecordEventArgs;
import org.coury.jfilehelpers.events.BeforeWriteRecordHandler;
import org.coury.jfilehelpers.helpers.ProgressHelper;
import org.coury.jfilehelpers.helpers.StringHelper;
import org.coury.jfilehelpers.interfaces.NotifyRead;
import org.coury.jfilehelpers.interfaces.NotifyWrite;

public class FileHelperEngine<T> extends EngineBase<T> implements Iterable<T> {

    private int maxRecords = 0;
    private int currentRecord = 0;
    private LineInfo line;
    private String currentLine;
    private String completeLine;
    private FileReader fr = null;
    private ForwardReader freader = null;
    
    private BeforeReadRecordHandler<T> beforeReadRecordHandler;
    private AfterReadRecordHandler<T> afterReadRecordHandler;
    private BeforeWriteRecordHandler<T> beforeWriteRecordHandler;
    private AfterWriteRecordHandler<T> afterWriteRecordHandler;

    public FileHelperEngine(Class<T> recordClass) {
        super(recordClass);
    }

    public List<T> readFile(String fileName) throws IOException {
        return readFile(fileName, Integer.MAX_VALUE);
    }

    public void writeFile(String fileName, List<T> records) throws IOException {
        writeFile(fileName, records, -1);
    }

    public void writeFile(String fileName, List<T> records, int maxRecords) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(fileName));
            //fw.write("ABCDEF\n");
            writeStream(fw, records, maxRecords);
        } finally {
            if (fw != null) {
                fw.flush();
                fw.close();
            }
        }
    }

    private void writeStream(Writer osr, Iterable<T> records, int maxRecords) throws IOException {
        BufferedWriter writer = new BufferedWriter(osr);

        try {
            resetFields();
            if (getHeaderText() != null && getHeaderText().length() != 0) {
                if (getHeaderText().endsWith(StringHelper.NEW_LINE)) {
                    writer.write(getHeaderText());
                } else {
                    writer.write(getHeaderText() + StringHelper.NEW_LINE);
                }
            }

            int max = maxRecords;
            if (records instanceof Collection) {
                max = Math.min(max < 0 ? Integer.MAX_VALUE : max, ((Collection<T>) records).size());
            }

            ProgressHelper.notify(notifyHandler, progressMode, 0, max);

            int recIndex = 0;
            boolean first = true;

            for (T rec : records) {
                if (recIndex == maxRecords) {
                    break;
                }

                this.lineNumber++;

                try {
                    if (rec == null) {
                        throw new IllegalArgumentException(
                                "The record at index " + recIndex + " is null.");
                    }

                    if (first) {
                        first = false;
                    }

                    boolean skip = false;
                    ProgressHelper.notify(notifyHandler, progressMode, recIndex + 1, max);
                    skip = onBeforeWriteRecord(rec);

                    if (!skip) {
                        currentLine = recordInfo.recordToStr(rec);
                        currentLine = onAfterWriteRecord(currentLine, rec);
                        writer.write(currentLine + StringHelper.NEW_LINE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                // TODO error manager
//				switch (mErrorManager.ErrorMode)
//				{
//					case ErrorMode.ThrowException:
//						throw;
//					case ErrorMode.IgnoreAndContinue:
//						break;
//					case ErrorMode.SaveAndContinue:
//						ErrorInfo err = new ErrorInfo();
//						err.mLineNumber = mLineNumber;
//						err.mExceptionInfo = ex;
////						err.mColumnNumber = mColumnNum;
//						err.mRecordString = currentLine;
//						mErrorManager.AddError(err);
//						break;
//				}
                }
                recIndex++;
            }
            currentLine = null;
            totalRecords = recIndex;

//			if (mFooterText != null && mFooterText != string.Empty)
//				if (mFooterText.EndsWith(StringHelper.NewLine))
//					writer.Write(mFooterText);
//				else
//					writer.WriteLine(mFooterText);
        } finally {
            writer.flush();
        }
    }
    
    public List<T> readFile(String fileName, int maxRecords) throws IOException {
        List<T> tempRes = null;
        Reader r = null;
        try {
            r = new FileReader(new File(fileName));
            tempRes = readStream(r, maxRecords);
        } finally {
            if (r != null) {
                r.close();
            }
        }
        return tempRes;
    }

    public List<T> readResource(String resourceName) throws IOException {
        return readResource(resourceName, Integer.MAX_VALUE);
    }

    public List<T> readResource(String fileName, int maxRecords) throws IOException {
        List<T> tempRes = null;
        Reader r = null;
        try {
            r = new InputStreamReader(getClass().getResourceAsStream(fileName));
            tempRes = readStream(r, maxRecords);
        } finally {
            if (r != null) {
                r.close();
            }
        }

        return tempRes;
    }

    public List<T> readStream(Reader fileReader, int maxRecords) throws IOException {
        List<T> list = null;
        try {
            list = new ArrayList<T>();
            openStream(fileReader, maxRecords);
            for (T t : this) {
                list.add(t);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            close();
        }
        return list;
    }

    public void openFile(String fileName) throws IOException {
        openFile(fileName, Integer.MAX_VALUE);
    }
    
    public void openFile(String fileName, int maxRecords) throws IOException {
        fr = new FileReader(new File(fileName));
        openStream(fr, maxRecords);
    }
    
    public void openResource(String resourceName) throws IOException {
        openResource(resourceName, Integer.MAX_VALUE);
    }

    public void openResource(String fileName, int maxRecords) throws IOException {
        Reader r = null;
        r = new InputStreamReader(getClass().getResourceAsStream(fileName));
        openStream(r, maxRecords);
    }
    
    public void openStream(Reader fileReader, int maxRecords) throws IOException {
        BufferedReader reader = new BufferedReader(fileReader);
        resetFields();
        setHeaderText("");
        setFooterText("");

        freader = new ForwardReader(reader, recordInfo.getIgnoreLast());
        freader.setDiscardForward(true);

        setLineNumber(1);
        completeLine = freader.readNextLine();
        currentLine = completeLine;

        ProgressHelper.notify(notifyHandler, progressMode, 0, -1);

        if (recordInfo.getIgnoreFirst() > 0) {
            for (int i = 0; i < recordInfo.getIgnoreFirst() && currentLine != null; i++) {
                headerText += currentLine + StringHelper.NEW_LINE;
                currentLine = freader.readNextLine();
                lineNumber++;
            }
        }

        // TODO boolean byPass = false;

        if (maxRecords < 0) {
            this.maxRecords = Integer.MAX_VALUE;
        } else {
            this.maxRecords = maxRecords;
        }

        line = new LineInfo(currentLine);
        line.setReader(freader);
    }
    
    public void close() throws IOException {
        if (fr != null) {
            fr.close();
        }
    }
    
    public void setBeforeReadRecordHandler(BeforeReadRecordHandler<T> beforeReadRecordHandler) {
        this.beforeReadRecordHandler = beforeReadRecordHandler;
    }

    public void setAfterReadRecordHandler(AfterReadRecordHandler<T> afterReadRecordHandler) {
        this.afterReadRecordHandler = afterReadRecordHandler;
    }

    public void setBeforeWriteRecordHandler(BeforeWriteRecordHandler<T> beforeWriteRecordHandler) {
        this.beforeWriteRecordHandler = beforeWriteRecordHandler;
    }

    public void setAfterWriteRecordHandler(AfterWriteRecordHandler<T> afterWriteRecordHandler) {
        this.afterWriteRecordHandler = afterWriteRecordHandler;
    }

    private boolean onBeforeReadRecord(BeforeReadRecordEventArgs<T> e) {
        if (beforeReadRecordHandler != null) {
            beforeReadRecordHandler.handleBeforeReadRecord(this, e);
            return e.getSkipThisRecord();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean onAfterReadRecord(String line, T record) {
        if (recordInfo.isNotifyRead()) {
            ((NotifyRead<T>) record).afterRead(this, line);
        }
        if (afterReadRecordHandler != null) {
            AfterReadRecordEventArgs<T> e = new AfterReadRecordEventArgs<T>(line, record, lineNumber);
            afterReadRecordHandler.handleAfterReadRecord(this, e);
            return e.getSkipThisRecord();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean onBeforeWriteRecord(T record) {
        if (recordInfo.isNotifyWrite()) {
            ((NotifyWrite<T>) record).beforeWrite(this);
        }
        if (beforeWriteRecordHandler != null) {
            BeforeWriteRecordEventArgs<T> e = new BeforeWriteRecordEventArgs<T>(record, lineNumber);
            beforeWriteRecordHandler.handleBeforeWriteRecord(this, e);
            return e.getSkipThisRecord();
        }
        return false;
    }

    private String onAfterWriteRecord(String line, T record) {
        if (afterWriteRecordHandler != null) {
            AfterWriteRecordEventArgs<T> e = new AfterWriteRecordEventArgs<T>(record, lineNumber, line);
            afterWriteRecordHandler.handleAfterWriteRecord(this, e);
            return e.getRecordLine();
        }
        return line;
    }

    public boolean hasNext() {
        return (currentLine != null);
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

			@Override
			public boolean hasNext() {
		        return (currentLine != null);
			}

			@Override
			public T next() {
		        T record = null;
		        if (currentLine != null && currentRecord < maxRecords) {
		            try {
		                totalRecords++;
		                currentRecord++;
		                line.reload(currentLine);
		                boolean skip = false;
		                ProgressHelper.notify(notifyHandler, progressMode, currentRecord, -1);
		                BeforeReadRecordEventArgs<T> e = new BeforeReadRecordEventArgs<T>(currentLine, lineNumber);
		                skip = onBeforeReadRecord(e);
		                if (e.getRecordLineChanged()) {
		                    line.reload(e.getRecordLine());
		                }
		                if (!skip) {
		                    record = recordInfo.strToRecord(line);
		                    skip = onAfterReadRecord(currentLine, record);
		                }
		                currentLine = freader.readNextLine();
		                completeLine = currentLine;
		                lineNumber++;
		            } catch (IOException ex) {
		                throw new Error(ex);
		            }
		        }
		        return record;
			}

			@Override
			public void remove() {
		        throw new UnsupportedOperationException("Not supported yet.");
			}
        	
        };
    }
    
}
