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
import java.io.Writer;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.coury.jfilehelpers.core.ForwardReader;
import org.coury.jfilehelpers.helpers.ProgressHelper;
import org.coury.jfilehelpers.helpers.StringHelper;
import org.coury.jfilehelpers.interfaces.NotifyRead;
import org.coury.jfilehelpers.interfaces.NotifyWrite;
import org.coury.jfilehelpers.events.*;

public class FileHelperEngine<T> extends EngineBase<T> implements Iterator<T>, Iterable<T>, Collection<T> {
    private boolean isResource = false;
    private int isEmpty = -1;
    private String file = null;
    private int maxRecords = 0;
    private int currentRecord = 0;
    private int recIndex = 0;
    private int size = -1;
    private LineInfo line;
    private String currentLine;
    private String completeLine;
    private FileReader fr = null;
    private FileWriter fw = null;
    private ForwardReader freader = null;
    private BeforeReadRecordHandler<T> beforeReadRecordHandler;
    private AfterReadRecordHandler<T> afterReadRecordHandler;
    private BeforeWriteRecordHandler<T> beforeWriteRecordHandler;
    private AfterWriteRecordHandler<T> afterWriteRecordHandler;

    public FileHelperEngine(Class<T> recordClass) {
        super(recordClass);
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
        for (T rec : records) {
            
        }
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
    
    public List<T> readFile(String fileName) throws IOException {
        return readFile(fileName, Integer.MAX_VALUE);
    }

    public List<T> readFile(String fileName, int maxRecords) throws IOException {
        openFile(fileName, maxRecords);
        List<T> list = new ArrayList<T>();
        for (T t : this) {
            list.add(t);
        }
        return list;
    }

    public List<T> readResource(String resourceName) throws IOException {
        return readResource(resourceName, Integer.MAX_VALUE);
    }

    public List<T> readResource(String fileName, int maxRecords) throws IOException {
        openResource(fileName, maxRecords);
        List<T> list = new ArrayList<T>();
        for (T t : this) {
            list.add(t);
        }
        return list;
    }

    public void openFile(String fileName) throws IOException {
        openFile(fileName, Integer.MAX_VALUE);
    }

    public void openFile(String fileName, int maxRecords) throws IOException {
        isResource = false;
        file = fileName;
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        fr = new FileReader(f);
        fw = new FileWriter(f, true);
        this.maxRecords = maxRecords;
    }

    public void openResource(String resourceName) throws IOException {
        openResource(resourceName, Integer.MAX_VALUE);
    }

    public void openResource(String fileName, int maxRecords) throws IOException {
        isResource = true;
        file = fileName;
        Reader r = null;
        try {
            r = new InputStreamReader(getClass().getResourceAsStream(fileName));
            openStream(r, maxRecords);
        } finally {
            if (r != null) {
                r.close();
            }
        }
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
        if (fw != null) {
            fw.close();
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
        if (totalRecords == 0) {
            try {
                openStream(fr, maxRecords);
            } catch (IOException ex) {
                throw new Error(ex);
            }
        }
        return (currentLine != null);
    }

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

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterator<T> iterator() {
        return this;
    }

    public int size() {
        FileHelperEngine<T> fileHelperEngine = null;
        try {
            if (size == -1) {
                fileHelperEngine = new FileHelperEngine<T>(recordClass);
                if (isResource) {
                    fileHelperEngine.openResource(file, maxRecords);
                } else {
                    fileHelperEngine.openFile(file, maxRecords);
                }
                for (T t : fileHelperEngine) {
                }
                size = fileHelperEngine.getTotalRecords();
            }
            return size;
        } catch (IOException ex) {
            throw new Error(ex);
        } finally {
            if (fileHelperEngine == null) {
                try {
                    fileHelperEngine.close();
                } catch (IOException ex) {
                    throw new Error(ex);
                }
            }
        }
    }

    public boolean isEmpty() {
        FileHelperEngine<T> fileHelperEngine = null;
        try {
            if (isEmpty == -1) {
                fileHelperEngine = new FileHelperEngine<T>(recordClass);
                if (isResource) {
                    fileHelperEngine.openResource(file, maxRecords);
                } else {
                    fileHelperEngine.openFile(file, maxRecords);
                }
                for (T t : fileHelperEngine) {
                    isEmpty = 0;
                    return false;
                }
                isEmpty = 1;
                return true;
            }
            if (isEmpty == 0) {
                return false;
            } else if (isEmpty == 1) {
                return true;
            }
            return true;
        } catch (IOException ex) {
            throw new Error(ex);
        } finally {
            if (fileHelperEngine == null) {
                try {
                    fileHelperEngine.close();
                } catch (IOException ex) {
                    throw new Error(ex);
                }
            }
        }
    }

    public boolean contains(Object o) {
        FileHelperEngine<T> fileHelperEngine = null;
        try {
            fileHelperEngine = new FileHelperEngine<T>(recordClass);
            if (isResource) {
                fileHelperEngine.openResource(file, maxRecords);
            } else {
                fileHelperEngine.openFile(file, maxRecords);
            }
            for (T t : fileHelperEngine) {
                if (recordInfo.recordToStr(o).equals(recordInfo.recordToStr(t))) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            throw new Error(ex);
        } finally {
            if (fileHelperEngine == null) {
                try {
                    fileHelperEngine.close();
                } catch (IOException ex) {
                    throw new Error(ex);
                }
            }
        }
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean add(T e) {
        try {
            BufferedWriter writer = new BufferedWriter(fw);
            if (totalRecords == 0) {
                resetFields();
                if (getHeaderText() != null && getHeaderText().length() != 0) {
                    if (getHeaderText().endsWith(StringHelper.NEW_LINE)) {
                        fw.write(getHeaderText());
                    } else {
                        fw.write(getHeaderText() + StringHelper.NEW_LINE);
                    }
                }
                ProgressHelper.notify(notifyHandler, progressMode, 0, maxRecords);
            }
        
            this.lineNumber++;

            if (e == null) {
                throw new IllegalArgumentException(
                        "The record is null.");
            }

            boolean skip = false;
            ProgressHelper.notify(notifyHandler, progressMode, recIndex + 1, maxRecords);
            skip = onBeforeWriteRecord(e);

            if (!skip) {
                currentLine = recordInfo.recordToStr(e);
                currentLine = onAfterWriteRecord(currentLine, e);
                writer.write(currentLine + StringHelper.NEW_LINE);
            }
            recIndex++;
            currentLine = null;
            totalRecords = recIndex;

//			if (mFooterText != null && mFooterText != string.Empty)
//				if (mFooterText.EndsWith(StringHelper.NewLine))
//					writer.Write(mFooterText);
//				else
//					writer.WriteLine(mFooterText);
            writer.flush();
            return true;
        } catch (Exception ex) {
            throw new Error(ex);
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
        } finally {
            try {
                fw.flush();
            } catch (IOException ex) {
                throw new Error(ex);
            }
        }
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsAll(Collection<?> c) {
        boolean[] found = new boolean[c.size()];
        for (int x = 0; x < found.length; x++) {
            found[x] = false;
        }
        FileHelperEngine<T> fileHelperEngine = null;
        try {
            fileHelperEngine = new FileHelperEngine<T>(recordClass);
            if (isResource) {
                fileHelperEngine.openResource(file, maxRecords);
            } else {
                fileHelperEngine.openFile(file, maxRecords);
            }
            for (T t : fileHelperEngine) {
                int i = 0;
                for (Object o : c) {
                    if (recordInfo.recordToStr(o).equals(recordInfo.recordToStr(t))) {
                        found[i] = true;
                    }
                    i++;
                }
                int countFound = 0;
                for (int x = 0; x < found.length; x++) {
                    if (found[x]) {
                        countFound++;
                    }
                }
                if (countFound == found.length) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            throw new Error(ex);
        } finally {
            if (fileHelperEngine == null) {
                try {
                    fileHelperEngine.close();
                } catch (IOException ex) {
                    throw new Error(ex);
                }
            }
        }
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            if (!add(t)) {
                return false;
            }
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
