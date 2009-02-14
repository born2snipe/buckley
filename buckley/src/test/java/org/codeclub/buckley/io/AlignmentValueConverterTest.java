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
package org.codeclub.buckley.io;

import junit.framework.TestCase;
import org.codeclub.buckley.Alignment;


public class AlignmentValueConverterTest extends TestCase {
    private AlignmentValueConverter converter;

    public void test_fromString_Uppercase() {
        assertEquals(Alignment.LEFT, converter.fromString("LEFT"));
    }

    public void test_fromString_lowerCase() {
        assertEquals(Alignment.RIGHT, converter.fromString("right"));
    }

    public void test_fromString_mixedCase() {
        assertEquals(Alignment.CENTER, converter.fromString("ceNTer"));
    }

    public void test_fromString_whiteSpace() {
        assertNull(converter.fromString("    "));
    }

    public void test_fromString_emptyString() {
        assertNull(converter.fromString(""));
    }

    public void test_fromString_null() {
        assertNull(converter.fromString(null));
    }
    
    public void test_canConvert() {
        assertTrue(converter.canConvert(Alignment.class));
        assertFalse(converter.canConvert(Integer.class));
    }

    protected void setUp() throws Exception {
        super.setUp();
        converter = new AlignmentValueConverter();
    }
}
