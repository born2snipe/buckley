/**
 * Copyright 2008-2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
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
