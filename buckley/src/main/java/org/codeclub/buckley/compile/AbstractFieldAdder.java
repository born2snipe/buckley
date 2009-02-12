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
package org.codeclub.buckley.compile;

import com.lowagie.text.pdf.PdfAcroForm;
import org.codeclub.buckley.Field;


public abstract class AbstractFieldAdder<T extends Field> implements FieldAdder<T> {
    public final void add(PdfAcroForm form, T field, float documentHeight) {
        float xOffset = field.getX() + field.getWidth();
        float yOffset = documentHeight - (field.getY() + field.getHeight());
        addField(form, field, field.getX(), yOffset, xOffset, documentHeight - field.getY());
    }

    protected abstract void addField(PdfAcroForm form, T field, float x, float y, float offX, float offY);
}
