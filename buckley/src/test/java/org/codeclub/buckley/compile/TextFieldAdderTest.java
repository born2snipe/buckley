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
import com.lowagie.text.pdf.PdfAcroForm;
import junit.framework.TestCase;
import org.codeclub.buckley.TextField;
import static org.mockito.Mockito.*;

import java.io.IOException;


public class TextFieldAdderTest extends TestCase {
    private PdfAcroForm form;
    private FontRegistry fontRegistry;
    private TextFieldAdder adder;
    private BaseFont font;

    public void test_add_NoFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4);

        when(fontRegistry.getDefaultFont()).thenReturn(font);
        when(fontRegistry.getDefaultFontSize()).thenReturn(12.0f);

        adder.add(form, field, 100);

        verify(form).addSingleLineTextField(field.getName(), "", font, 12.0f, 1.0f, 94.0f, 4.0f, 98.0f);
        verify(fontRegistry).getDefaultFont();
        verify(fontRegistry).getDefaultFontSize();
    }
    
    public void test_add_SpecificFont() throws DocumentException, IOException {
        TextField field = new TextField("name", 1, 2, 3, 4, "Courier", 12);

        when(fontRegistry.getFont("Courier")).thenReturn(font);

        adder.add(form, field, 100);

        verify(form).addSingleLineTextField(field.getName(), "", font, 12.0f, 1.0f, 94.0f, 4.0f, 98.0f);
        verify(fontRegistry).getFont("Courier");
    }

    protected void setUp() throws Exception {
        super.setUp();
        form = mock(PdfAcroForm.class);
        fontRegistry = mock(FontRegistry.class);
        adder = new TextFieldAdder(fontRegistry);
        font = BaseFont.createFont();
    }
}
