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
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfWriter;
import org.codeclub.buckley.Border;
import org.codeclub.buckley.Field;
import org.codeclub.buckley.Pdf;

import java.io.IOException;


public abstract class AbstractFieldAdder<T extends Field, F extends BaseField> implements FieldAdder<T> {
    private FontRegistry fontRegistry;

    protected AbstractFieldAdder(FontRegistry fontRegistry) {
        this.fontRegistry = fontRegistry;
    }

    public final void add(Pdf pdf, T field, float documentHeight) {
        float xOffset = field.getX() + field.getWidth();
        float yOffset = documentHeight - (field.getY() + field.getHeight());
        try {
            BaseFont pdfFont;
            float fontSize;
            if (field.getFontName() != null) {
                pdfFont = fontRegistry.getFont(field.getFontName());
                fontSize = field.getFontSize();
            } else {
                pdfFont = fontRegistry.getDefaultFont();
                fontSize = fontRegistry.getDefaultFontSize();
            }
            PdfWriter writer = pdf.getWriter();
            F textField = (F) createField(writer, new Rectangle(field.getX(), yOffset, xOffset, documentHeight - field.getY()), field.getName());
            textField.setFont(pdfFont);
            textField.setFontSize(fontSize);
            textField.setBackgroundColor(field.getBackgroundColor());
            textField.setTextColor(field.getColor());
            textField.setAlignment(field.getAlignment().getiTextCode());
            Border border = field.getBorder();
            if (border != null) {
                textField.setBorderColor(border.getColor());
                textField.setBorderWidth(border.getWidth());
            }
            writer.addAnnotation(createFormField(textField));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract BaseField createField(PdfWriter writer, Rectangle rectangle, String name);

    protected abstract PdfFormField createFormField(F field) throws DocumentException, IOException;
}
