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
