package org.codeclub.buckley;


public class TextField extends Field {
    private String fontName;
    private Float fontSize;

    public TextField(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    public TextField(String name, int x, int y, int width, int height, String fontName, float fontSize) {
        this(name, x, y, width, height);
        this.fontName = fontName;
        this.fontSize = fontSize;
    }


    public String getFontName() {
        return fontName;
    }

    public float getFontSize() {
        return fontSize;
    }
}
