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
package org.codeclub.buckley;

import junit.framework.TestCase;


public class DocumentTest extends TestCase {
    private Document doc;

    public void test_getPage_SinglePage() {
        Page page = new Page(1);

        doc.addPage(page);

        assertSame(page, doc.getPage(1));
    }

    public void test_getPage_MultiplePages() {
        doc.addPage(new Page(1));
        Page page = new Page(2);
        doc.addPage(page);

        assertSame(page, doc.getPage(2));
    }

    public void test_getPage_PageDoesNotExist() {
        assertNull(doc.getPage(0));
    }

    protected void setUp() throws Exception {
        super.setUp();
        doc = new Document();
    }
}
