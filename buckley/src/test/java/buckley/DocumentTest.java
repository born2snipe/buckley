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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DocumentTest {
    private Document doc;

    @Test
    public void test_getPage_SinglePage() {
        Page page = new Page(1);

        doc.addPage(page);

        assertSame(page, doc.getPage(1));
    }

    @Test
    public void test_getPage_MultiplePages() {
        doc.addPage(new Page(1));
        Page page = new Page(2);
        doc.addPage(page);

        assertSame(page, doc.getPage(2));
    }

    @Test
    public void test_getPage_PageDoesNotExist() {
        assertEquals(null, doc.getPage(0));
    }

    @Test
    public void test_hasFields_NoPages() {
        assertFalse(doc.hasFields(1));
    }

    @Test
    public void test_hasFields_PageWithNoFields() {
        Page page = mock(Page.class);
        when(page.getNumber()).thenReturn(1);
        when(page.hasFields()).thenReturn(false);

        doc.addPage(page);

        assertFalse(doc.hasFields(1));
    }

    @Test
    public void test_hasFields() {
        Page page = mock(Page.class);
        when(page.getNumber()).thenReturn(1);
        when(page.hasFields()).thenReturn(true);

        doc.addPage(page);

        assertTrue(doc.hasFields(1));
    }

    @Before
    public void setUp() throws Exception {
        doc = new Document();
    }
}
