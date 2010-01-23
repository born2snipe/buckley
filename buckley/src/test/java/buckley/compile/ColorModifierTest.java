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

import buckley.Field;
import com.lowagie.text.pdf.BaseField;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.*;


public class ColorModifierTest {
    private ColorModifier modifier;
    private BaseField iTextField;
    private Field field;

    @Before
    public void setUp() throws Exception {
        modifier = new ColorModifier();
        iTextField = mock(BaseField.class);
        field = mock(Field.class);
    }

    @Test
    public void test_noColor() {
        when(field.getColor()).thenReturn(null);

        modifier.modify(iTextField, field, null);

        verify(iTextField).setTextColor(Color.black);
    }

    @Test
    public void test_hasColor() {
        when(field.getColor()).thenReturn(Color.red);

        modifier.modify(iTextField, field, null);

        verify(iTextField).setTextColor(Color.red);
    }


}
