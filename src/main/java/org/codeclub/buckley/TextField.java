package org.codeclub.buckley;


public class TextField extends Field {
    private Font font;

    public TextField(String name, int x, int y, int width, int height) {
        super(name, x, y, height, width);
    }

    public TextField(String name, int x, int y, int width, int height, Font font) {
        this(name, x, y, width, height);
        this.font = font;
    }


    public Font getFont() {
        return font;
    }

    public static class Font {
        private String name;
        private boolean embed = true;
        private float size;

        public Font(String name, float size) {
            this.size = size;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isEmbed() {
            return embed;
        }

        public void setEmbed(boolean embed) {
            this.embed = embed;
        }

        public float getSize() {
            return size;
        }
    }
}
