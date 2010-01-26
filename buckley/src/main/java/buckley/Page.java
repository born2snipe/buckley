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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Page {
    private int number;
    private List<Field> fields = new ArrayList<Field>();

    public Page(int number) {
        if (number < 1) throw new IllegalArgumentException("Page number must be >= 1");
        this.number = number;
    }

    public void addField(Field field) {
        fields.add(field);
        Collections.sort(fields, new Comparator<Field>() {
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public int getNumber() {
        return number;
    }

    public boolean hasFields() {
        return getFields().size() > 0;
    }
}
