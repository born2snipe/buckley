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
package buckley.io;

import buckley.Alignment;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;


public class AlignmentValueConverter extends AbstractSingleValueConverter {
    public boolean canConvert(Class type) {
        return type.isInstance(Alignment.LEFT);
    }

    public Object fromString(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        return Alignment.valueOf(str.toUpperCase());
    }
}
