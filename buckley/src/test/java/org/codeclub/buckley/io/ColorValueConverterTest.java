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

import java.awt.*;


public class ColorValueConverterTest extends TestCase {
    private ColorValueConverter converter;

    public void setUp() {
        converter = new ColorValueConverter();
    }

    public void test_fromString_Null() {
        assertNull(converter.fromString(null));
    }

    public void test_fromString_WhiteSpace() {
        assertNull(converter.fromString("   "));
    }

    public void test_fromString_EmptyString() {
        assertNull(converter.fromString(""));
    }

    public void test_fromString_Blue() {
        assertEquals(Color.white, converter.fromString("ffffffff"));
    }

    public void test_fromString_Black() {
        assertEquals(Color.black, converter.fromString("FF000000"));
        assertEquals(Color.black, converter.fromString("000000"));
    }

    public void test_toString_null() {
        assertNull(converter.toString(null));
    }

    public void test_toString_blue() {
        Color color = Color.blue;
        assertEquals(Integer.toHexString(color.getRGB()), converter.toString(color));
    }

    public void test_toString_black() {
        Color color = Color.black;
        assertEquals(Integer.toHexString(color.getRGB()), converter.toString(color));
    }

    public void test_canConvert() {
        assertTrue(converter.canConvert(Color.class));
        assertFalse(converter.canConvert(String.class));
    }
}
