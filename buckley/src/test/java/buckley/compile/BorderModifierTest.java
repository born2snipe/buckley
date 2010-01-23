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

import buckley.Border;
import buckley.Document;
import buckley.Field;
import com.lowagie.text.pdf.BaseField;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.*;


public class BorderModifierTest {
    private BorderModifier modifier;
    private BaseField iTextField;
    private Field field;
    private Document document;
    private Border border;

    @Before
    public void setUp() throws Exception {
        iTextField = mock(BaseField.class);
        field = mock(Field.class);
        document = mock(Document.class);
        border = mock(Border.class);

        modifier = new BorderModifier();

        when(field.getBorder()).thenReturn(border);
    }

    @Test
    public void test_noBorder() {
        when(field.getBorder()).thenReturn(null);

        modifier.modify(iTextField, field, document);

        verifyZeroInteractions(iTextField);
    }

    @Test
    public void test_noColorAndNoSize() {
        when(border.getColor()).thenReturn(null);
        when(border.getWidth()).thenReturn(null);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setBorderColor(Color.black);
        verify(iTextField).setBorderWidth(1.0f);
    }

    @Test
    public void test_withColor() {
        when(border.getColor()).thenReturn(Color.red);
        when(border.getWidth()).thenReturn(null);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setBorderColor(Color.red);
        verify(iTextField).setBorderWidth(1.0f);
    }

    @Test
    public void test_withSizeAndColor() {
        when(border.getColor()).thenReturn(Color.black);
        when(border.getWidth()).thenReturn(2.0f);

        modifier.modify(iTextField, field, document);

        verify(iTextField).setBorderColor(Color.black);
        verify(iTextField).setBorderWidth(2.0f);
    }


}
