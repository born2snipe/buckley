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

import com.lowagie.text.pdf.PdfAnnotation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class StubPdf extends Pdf {
    private List<PdfAnnotation> annotations = new ArrayList<PdfAnnotation>();

    public StubPdf() throws FileNotFoundException {
        super(new FileInputStream(new File("checkbox.pdf")), new ByteArrayOutputStream());
    }

    public List<PdfAnnotation> getAnnotations() {
        return annotations;
    }
}
