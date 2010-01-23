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

import com.lowagie.text.Element;


public enum Alignment {
    LEFT(Element.ALIGN_LEFT), RIGHT(Element.ALIGN_RIGHT), CENTER(Element.ALIGN_CENTER);

    private int iTextCode;

    Alignment(int iTextCode) {
        this.iTextCode = iTextCode;
    }

    public int getiTextCode() {
        return iTextCode;
    }
}
