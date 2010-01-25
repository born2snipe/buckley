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
package buckley.extract;

import buckley.CheckboxField;
import buckley.TextField;
import com.lowagie.text.pdf.AcroFields;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FieldFactoryTest {
    private FieldFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new FieldFactory();
    }

    @Test
    public void test() {
        assertTrue(factory.build(AcroFields.FIELD_TYPE_TEXT) instanceof TextField);
        assertTrue(factory.build(AcroFields.FIELD_TYPE_CHECKBOX) instanceof CheckboxField);
    }

    @Test
    public void test_unsupported() {
        try {
            factory.build(-1);
            fail();
        } catch (UnsupportedOperationException err) {
            assertEquals("Unsupported form field type", err.getMessage());
        }
    }


}
