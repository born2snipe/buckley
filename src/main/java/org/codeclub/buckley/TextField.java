package org.codeclub.buckley;


public class TextField extends Field {
    private Font font;

    public TextField(String name, int x, int y, int width, int height) {
        super(name, x, y, height, width);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
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
