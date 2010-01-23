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
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfWriter;
import org.codeclub.buckley.Alignment;
import org.codeclub.buckley.Border;
import org.codeclub.buckley.Pdf;
import org.codeclub.buckley.TextField;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class TextFieldAdderTest {
    private Pdf pdf;
    private FontRegistry fontRegistry;
    private ShuntTextFieldAdder adder;
    private BaseFont font;
    private PdfWriter writer;

    @Before
    public void setUp() throws Exception {
        pdf = mock(Pdf.class);
        fontRegistry = mock(FontRegistry.class);
        adder = new ShuntTextFieldAdder(fontRegistry);
        font = BaseFont.createFont();
        writer = mock(PdfWriter.class);
        adder.field = mock(PdfFormField.class);
    }

    @Test
    public void test_add_NoFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4);
        field.setAlignment(Alignment.RIGHT);
        field.setBackgroundColor(Color.black);
        field.setColor(Color.white);

        when(pdf.getWriter()).thenReturn(writer);
        when(fontRegistry.getDefaultFont()).thenReturn(font);
        when(fontRegistry.getDefaultFontSize()).thenReturn(12.0f);

        adder.add(pdf, field, 100);

        com.lowagie.text.pdf.TextField formField = adder.textField;
        assertEquals(field.getName(), formField.getFieldName());
        assertEquals(font, formField.getFont());
        assertEquals(12.0f, formField.getFontSize(), 0.0);
        assertEquals(field.getBackgroundColor(), formField.getBackgroundColor());
        assertEquals(field.getColor(), formField.getTextColor());
        assertEquals(field.getAlignment().getiTextCode(), formField.getAlignment());

        verify(writer).addAnnotation(adder.field);
    }

    @Test
    public void test_add_SpecificFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4, "Courier", 12.0f);

        when(pdf.getWriter()).thenReturn(writer);
        when(fontRegistry.getFont("Courier")).thenReturn(font);

        adder.add(pdf, field, 100);

        com.lowagie.text.pdf.TextField formField = adder.textField;
        assertEquals(font, formField.getFont());

        verify(writer).addAnnotation(adder.field);
    }

    @Test
    public void test_add_Border() {
        TextField field = new TextField("name", 1, 2, 3, 4, "Courier", 12.0f);
        field.setBorder(new Border(11.0f, Color.black));

        when(pdf.getWriter()).thenReturn(writer);
        when(fontRegistry.getFont("Courier")).thenReturn(font);

        adder.add(pdf, field, 100);

        com.lowagie.text.pdf.TextField formField = adder.textField;
        assertEquals(field.getBorder().getColor(), formField.getBorderColor());
        assertEquals(field.getBorder().getWidth(), formField.getBorderWidth(), 0.0);
    }


    private static class ShuntTextFieldAdder extends TextFieldAdder {
        private PdfFormField field;
        private com.lowagie.text.pdf.TextField textField;

        public ShuntTextFieldAdder(FontRegistry fontRegistry) {
            super(fontRegistry);
        }


        protected PdfFormField createFormField(com.lowagie.text.pdf.TextField formField) throws DocumentException, IOException {
            this.textField = formField;
            return field;
        }
    }
}
