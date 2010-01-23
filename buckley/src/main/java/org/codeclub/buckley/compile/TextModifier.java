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
package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.BaseFont;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.TextField;


public class TextModifier implements FieldAttributeModifier<TextField> {
    public void modify(BaseField iTextField, TextField field, Document document) {
        FontRegistry fontRegistry = document.getFontRegistry();

        BaseFont font = fontRegistry.getDefaultFont();
        float size = fontRegistry.getDefaultFontSize();

        if (field.getFontName() != null) {
            font = fontRegistry.getFont(field.getFontName());
        }

        if (field.getFontSize() != null && field.getFontSize() > 0.0f) {
            size = field.getFontSize();
        }

        iTextField.setFont(font);
        iTextField.setFontSize(size);
    }
}
