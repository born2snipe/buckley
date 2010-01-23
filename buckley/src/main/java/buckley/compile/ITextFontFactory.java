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

import com.lowagie.text.pdf.BaseFont;


public class ITextFontFactory {
    public BaseFont build(String name, String encoding, boolean embed) {
        try {
            return BaseFont.createFont(name, encoding, embed);
        } catch (Exception e) {
            throw new IllegalStateException("Problem build the iText font", e);
        }
    }

    public BaseFont build(String name, String encoding, byte[] fontFileBytes) {
        try {
            return BaseFont.createFont(name, encoding, true, true, fontFileBytes, new byte[0]);
        } catch (Exception e) {
            throw new IllegalStateException("Problem build the iText font", e);
        }
    }

}
