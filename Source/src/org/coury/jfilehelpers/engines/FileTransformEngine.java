/*
 * FileTransformEngine.java
 *
 * Copyright (C) 2007 Felipe Gonçalves Coury <felipe.coury@gmail.com>
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

public final class FileTransformEngine<Source, Destination> {
	private Encoding destinationEncoding;
	private Destination destination;
	private Encoding sourceEncoding;
	private Source source;
	
	public FileTransformEngine(Source source, Destination destination) {
		this.source = source;
		this.destination = destination;
	}
	
	public Destination[] transformFile(String sourceFile, String destinationFile) {
		// TODO empty method stub
		return null;
	}

	public Encoding getDestinationEncoding() {
		return destinationEncoding;
	}

	public void setDestinationEncoding(Encoding destinationEncoding) {
		this.destinationEncoding = destinationEncoding;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Encoding getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(Encoding sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
}
