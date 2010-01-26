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
package buckley;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;


public class PageTest {
    @Test
    public void test_fieldsSortedByName() {
        Field field1 = new TextField();
        field1.setName("field-1");

        Field field2 = new TextField();
        field2.setName("field-2");

        Page page = new Page(1);
        page.addField(field2);
        page.addField(field1);

        assertEquals(asList(field1, field2), page.getFields());
    }

    @Test
    public void test_constructor_Negative() {
        try {
            new Page(-1);
            fail();
        } catch (IllegalArgumentException err) {
            assertEquals("Page number must be >= 1", err.getMessage());
        }
    }

    @Test
    public void test_constructor_ZeroPageNumber() {
        try {
            new Page(0);
            fail();
        } catch (IllegalArgumentException err) {
            assertEquals("Page number must be >= 1", err.getMessage());
        }
    }

    @Test
    public void test_hasFields_NoFields() {
        Page page = new Page(1);
        assertFalse(page.hasFields());
    }

    @Test
    public void test_hasFields_HasAField() {
        Page page = new Page(1);
        page.addField(new TextField());
        assertTrue(page.hasFields());
    }
}
