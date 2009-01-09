package org.codeclub.buckley.compile;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import org.codeclub.buckley.EmbeddedFont;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class FontRegistry {
    private static final String DEFAULT_FONT_NAME = BaseFont.HELVETICA;
    private static final float DEFAULT_FONT_SIZE = 12.0f;

    private Set<EmbeddedFont> fonts = new HashSet<EmbeddedFont>();
    private String defaultFontName = DEFAULT_FONT_NAME;
    private float defaultFontSize = DEFAULT_FONT_SIZE;

    public FontRegistry() {
        FontFactory.defaultEmbedding = BaseFont.EMBEDDED;
    }

    public BaseFont getFont(String name) {
        for (EmbeddedFont embeddedFont : fonts) {
            if (embeddedFont.getName().equals(name)) {
                return createFont(embeddedFont);
            }
        }
        return getFont(name, BaseFont.WINANSI, true);
    }

    public BaseFont getDefaultFont() {
        return getFont(defaultFontName, BaseFont.WINANSI, true);
    }

    private BaseFont createFont(EmbeddedFont embeddedFont) {
        try {
            return BaseFont.createFont(embeddedFont.getName(), embeddedFont.getEncoding(), true, true, embeddedFont.getContent(), new byte[0]);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BaseFont getFont(String name, String encoding, boolean embed) {
        try {
            return BaseFont.createFont(name, encoding, embed);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getDefaultFontSize() {
        return defaultFontSize;
    }

    public void registerFont(EmbeddedFont font) {
        fonts.add(font);
    }

}