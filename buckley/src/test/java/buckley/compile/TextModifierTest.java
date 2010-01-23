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

import buckley.Document;
import buckley.TextField;
import com.lowagie.text.pdf.BaseField;
import com.lowagie.text.pdf.BaseFont;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class TextModifierTest {
    private TextField field;
    private BaseField iTextField;
    private Document document;
    private FontRegistry fontRegistry;
    private BaseFont font;
    private TextModifier modifier;

    @Before
    public void setUp() throws Exception {
        field = mock(TextField.class);
        iTextField = mock(BaseField.class);
        document = mock(Document.class);
        fontRegistry = mock(FontRegistry.class);
        font = mock(BaseFont.class);

        modifier = new TextModifier();

        when(document.getFontRegistry()).thenReturn(fontRegistry);
    }

    @Test
    public void test_fontGivenWithZeroSize() {
        when(field.getFontName()).thenReturn("font-name");
        when(field.getFontSize()).thenReturn(0.0f);
        when(fontRegistry.getDefaultFontSize()).thenReturn(12.0f);

        when(fontRegistry.getFont("font-name")).thenReturn(font);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setFont(font);
        verify(iTextField).setFontSize(12.0f);
    }

    @Test
    public void test_fontGivenWithoutSize() {
        when(field.getFontName()).thenReturn("font-name");
        when(field.getFontSize()).thenReturn(null);
        when(fontRegistry.getDefaultFontSize()).thenReturn(12.0f);

        when(fontRegistry.getFont("font-name")).thenReturn(font);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setFont(font);
        verify(iTextField).setFontSize(12.0f);
    }

    @Test
    public void test_fontGivenWithSize() {
        when(field.getFontName()).thenReturn("font-name");
        when(field.getFontSize()).thenReturn(10.0f);

        when(fontRegistry.getFont("font-name")).thenReturn(font);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setFont(font);
        verify(iTextField).setFontSize(10.0f);
    }

    @Test
    public void test_useDefaultFont() {
        when(field.getFontSize()).thenReturn(null);
        when(fontRegistry.getDefaultFont()).thenReturn(font);
        when(fontRegistry.getDefaultFontSize()).thenReturn(2.0f);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setFont(font);
        verify(iTextField).setFontSize(2.0f);
    }


}
