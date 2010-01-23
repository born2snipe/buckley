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

import buckley.Alignment;
import buckley.Field;
import com.lowagie.text.pdf.BaseField;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class AlignmentModifierTest {
    private AlignmentModifier modifier;
    private BaseField iTextField;
    private Field field;

    @Before
    public void setUp() throws Exception {
        modifier = new AlignmentModifier();
        iTextField = mock(BaseField.class);
        field = mock(Field.class);
    }

    @Test
    public void test_noAlignment() {
        when(field.getAlignment()).thenReturn(null);

        modifier.modify(iTextField, field, null);

        verify(iTextField).setAlignment(Alignment.LEFT.getiTextCode());
    }

    @Test
    public void test_hasAlignment() {
        when(field.getAlignment()).thenReturn(Alignment.RIGHT);

        modifier.modify(iTextField, field, null);

        verify(iTextField).setAlignment(Alignment.RIGHT.getiTextCode());
    }
}
