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
import buckley.Field;
import buckley.TextField;
import com.lowagie.text.pdf.AcroFields;


public class FieldFactory {
    public Field build(int iTextFieldType) {
        switch (iTextFieldType) {
            case AcroFields.FIELD_TYPE_TEXT:
                return new TextField();
            case AcroFields.FIELD_TYPE_CHECKBOX:
                return new CheckboxField();
        }
        throw new UnsupportedOperationException("Unsupported form field type");
    }
}
