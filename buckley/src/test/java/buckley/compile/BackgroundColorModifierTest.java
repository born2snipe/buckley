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


public class BackgroundColorModifierTest {
    private BackgroundColorModifier modifier;
    private Field field;
    private BaseField iTextField;

    @Before
    public void setUp() throws Exception {
        modifier = new BackgroundColorModifier();
        field = mock(Field.class);
        iTextField = mock(BaseField.class);
    }

    @Test
    public void test_hasNoBackgroundColor() {
        when(field.getBackgroundColor()).thenReturn(null);

        modifier.modify(iTextField, field, null);

        verifyZeroInteractions(iTextField);
    }

    @Test
    public void test_hasBackgroundColor() {
        when(field.getBackgroundColor()).thenReturn(Color.red);

        modifier.modify(iTextField, field, null);

        verify(iTextField).setBackgroundColor(Color.red);
    }


}
