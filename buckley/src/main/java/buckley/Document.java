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

import buckley.compile.FontRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Document {
    private List<Page> pages = new ArrayList<Page>();
    private FontRegistry fontRegistry = new FontRegistry();

    public void addPage(Page page) {
        pages.add(page);
    }

    public List<Page> getPages() {
        return Collections.unmodifiableList(pages);
    }

    public FontRegistry getFontRegistry() {
        return fontRegistry;
    }

    public Page getPage(int pageNumber) {
        for (Page page : pages) {
            if (page.getNumber() == pageNumber) {
                return page;
            }
        }
        return null;
    }

    public boolean hasFields(int pageNumber) {
        Page page = getPage(pageNumber);
        return page == null ? false : page.hasFields();
    }
}
