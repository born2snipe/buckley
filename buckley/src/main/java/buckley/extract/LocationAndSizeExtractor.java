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

import buckley.Field;
import com.lowagie.text.pdf.AcroFields;


public class LocationAndSizeExtractor implements ITextFieldExtractor {
    public boolean canExtract(Field field) {
        return true;
    }

    public void extract(int sameFieldNameCount, Field field, String fieldName, AcroFields fields) {
        float[] positions = fields.getFieldPositions(fieldName);
        int offset = sameFieldNameCount * 5;
        field.setX(positions[1 + offset]);
        field.setY(positions[3 + offset]);
        field.setWidth(positions[3 + offset] - positions[1 + offset]);
        field.setHeight(positions[4 + offset] - positions[2 + offset]);
    }
}
