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
import com.lowagie.text.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FieldSizeFactoryTest {
    @Test
    public void test() {
        FieldSizeFactory factory = new FieldSizeFactory();
        Field field = mock(Field.class);

        when(field.getX()).thenReturn(1.0f);
        when(field.getWidth()).thenReturn(100.0f);
        when(field.getY()).thenReturn(2.0f);
        when(field.getHeight()).thenReturn(200.0f);

        Rectangle rectangle = factory.build(field);

        assertNotNull(rectangle);
        assertEquals(1.0f, rectangle.getLeft(), 0.0);
        assertEquals(101.0f, rectangle.getRight(), 0.0);
        assertEquals(202.0, rectangle.getTop(), 0.0);
        assertEquals(2.0f, rectangle.getBottom(), 0.0);
    }
}
