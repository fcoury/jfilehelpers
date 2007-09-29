package org.coury.jfilehelpers.fields;

import java.io.IOException;
import java.lang.reflect.Field;

import org.coury.jfilehelpers.core.ExtractedInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.enums.MultilineMode;
import org.coury.jfilehelpers.enums.QuoteMode;
import org.coury.jfilehelpers.enums.TrimMode;
import org.coury.jfilehelpers.helpers.StringHelper;

public class DelimitedField extends FieldBase {
	
	private String separator;
	private char quoteChar = '\0';
	private QuoteMode quoteMode;
	private MultilineMode quoteMultiline = MultilineMode.AllowForBoth;
	
	public DelimitedField(Field fi, String sep) {
		super(fi);
		this.separator = sep;
	}

	@Override
	protected ExtractedInfo extractFieldString(LineInfo line) {
		if (isOptional() && line.isEol()) {
			return ExtractedInfo.Empty;
		}
		
		if (isLast()) {
			charsToDiscard = 0;
		}
		else {
			charsToDiscard = separator.length(); 
		}
		
		if (quoteChar == '\0') {
			return basicExtractString(line);
		}
		else {
			//TODO: UnComment and Fix

			if (getTrimMode() == TrimMode.Both || getTrimMode() == TrimMode.Left) {
				line.trimStart(getTrimChars());
			}

			String quotedStr = Character.toString(quoteChar);
			if (line.startsWith(quotedStr)) {
				try {
					return StringHelper.extractQuotedString(line, quoteChar, quoteMultiline == MultilineMode.AllowForBoth || quoteMultiline == MultilineMode.AllowForRead);
				} catch (IOException e) {
					throw new RuntimeException("IOException extracting information from field '" + this.getFieldInfo().getName());
				}
			}
			else {
				if (quoteMode == QuoteMode.OptionalForBoth || quoteMode == QuoteMode.OptionalForRead) {
					return basicExtractString(line);
				}
				else if (line.startsWithTrim(quotedStr)) {
					throw new IllegalArgumentException(
							"The field '" + this.getFieldInfo().getName() + 
							"' has spaces before the QuotedChar at line " + line.getLineNumber() + 
							". Use the TrimAttribute to by pass this error. Field String: " + 
							line.getCurrentString());
				}
				else
					throw new IllegalArgumentException(
							"The field '" + this.getFieldInfo().getName() + 
							"' not begin with the QuotedChar at line "+ 
							line.getLineNumber() + ". " +
							"You can use @FieldQuoted(quoteMode=QuoteMode.OptionalForRead) " +
							"to allow optional quoted field.. " +
							"Field String: " + line.getCurrentString());
			}

		}
	}

	private ExtractedInfo basicExtractString(LineInfo line) {
		ExtractedInfo res;
		
		if (isLast())
			res = new ExtractedInfo(line);
		else {
			int sepPos;

			sepPos = line.indexOf(separator);

			if (sepPos == -1) {
				if (this.isNextOptional() == false) {
					String msg = null;

					if (isFirst() && line.isEmptyFromPos())
						msg = 
							"The line " + line.getLineNumber() + 
							" is empty. Maybe you need to use the annotation " +
							"[@IgnoreEmptyLines] in your record class.";
					else
						msg = 
							"The delimiter '" + this.separator + "' " +
							"can't be found after the field '" + 
							this.getFieldInfo().getName() + "' at line " + 
							line.getLineNumber() + 
							" (the record has less fields, the delimiter " +
							"is wrong or the next field must be marked " +
							"as optional).";

					//throw new FileHelpersException(msg);
					throw new IllegalArgumentException(msg);
				}
				else {
					sepPos = line.getLine().length - 1;
				}
			}

			res = new ExtractedInfo(line, sepPos);
		}
		return res;
	}

	@Override
	protected void createFieldString(StringBuffer sb, Object fieldValue) {
		String field = super.baseFieldString(fieldValue);

		boolean hasNewLine = 
			field.indexOf(StringHelper.NEW_LINE) >= 0;

		// If have a new line and this is not allowed throw an exception
		if (hasNewLine &&
			(quoteMultiline == MultilineMode.AllowForRead || 
			 quoteMultiline == MultilineMode.NotAllow)) {

			throw new IllegalArgumentException(
					"One value for the field " + this.getFieldInfo().getName() + 
					" has a new line inside. To allow write this value you must " +
					"add a FieldQuoted attribute with the multiline option in true.");			

		}

		// Add Quotes If:
		//     -  optional == false
		//     -  is optional and contains the separator 
		//     -  is optional and contains a new line

		if ((quoteChar != '\0') && 
			(quoteMode == QuoteMode.AlwaysQuoted || 
				quoteMode == QuoteMode.OptionalForRead || 
				( (quoteMode == QuoteMode.OptionalForWrite || quoteMode == QuoteMode.OptionalForBoth)  
				&& field.indexOf(separator) >= 0) || hasNewLine)) {
			StringHelper.createQuotedString(sb, field, quoteChar);
		}
		else {
			sb.append(field);
		}

		if (!isLast()) {
			sb.append(separator);
		}			
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
		if (isLast()) {
			charsToDiscard = 0;
		}
		else {
			charsToDiscard = separator.length();
		}
	}

}
