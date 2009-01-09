package org.codeclub.buckley;


public class EmbeddedFont {
    private String name;
    private String encoding;
    private byte[] content;

    public EmbeddedFont(String name, String encoding, byte[] content) {
        this.name = name;
        this.encoding = encoding;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getEncoding() {
        return encoding;
    }

    public byte[] getContent() {
        return content;
    }
}
