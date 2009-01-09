package org.codeclub.buckley.compile;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import org.codeclub.buckley.EmbeddedFont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FontRegistry {
    private List<EmbeddedFont> fonts = new ArrayList<EmbeddedFont>();

    public FontRegistry() {
        FontFactory.defaultEmbedding = BaseFont.EMBEDDED;
    }

    public BaseFont getFont(String name) {
        return null;
    }

    public BaseFont getDefaultFont() {
        try {
            return BaseFont.createFont("Courier", BaseFont.WINANSI, true);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getDefaultFontSize() {
        return 12.0f;
    }
}
