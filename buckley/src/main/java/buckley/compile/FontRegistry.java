/**
 * Copyright 2008-2010 the original author or authors.
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
package buckley.compile;

import buckley.EmbeddedFont;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

import java.util.HashSet;
import java.util.Set;


public class FontRegistry {
    private static final String DEFAULT_FONT_NAME = BaseFont.HELVETICA;
    private static final float DEFAULT_FONT_SIZE = 12.0f;
    private static final ITextFontFactory ITEXT_FONT_FACTORY = new ITextFontFactory();

    private Set<EmbeddedFont> fonts = new HashSet<EmbeddedFont>();
    private String defaultFontName = DEFAULT_FONT_NAME;
    private float defaultFontSize = DEFAULT_FONT_SIZE;

    public FontRegistry() {
        FontFactory.defaultEmbedding = BaseFont.EMBEDDED;
    }

    public BaseFont getFont(String name) {
        for (EmbeddedFont embeddedFont : fonts) {
            if (embeddedFont.getName().equals(name)) {
                return ITEXT_FONT_FACTORY.build(embeddedFont.getName(), embeddedFont.getEncoding(), embeddedFont.getContent());
            }
        }
        return ITEXT_FONT_FACTORY.build(name, BaseFont.WINANSI, true);
    }

    public BaseFont getDefaultFont() {
        return ITEXT_FONT_FACTORY.build(defaultFontName, BaseFont.WINANSI, true);
    }

    public float getDefaultFontSize() {
        return defaultFontSize;
    }

    public void registerFont(EmbeddedFont font) {
        fonts.add(font);
    }

}
