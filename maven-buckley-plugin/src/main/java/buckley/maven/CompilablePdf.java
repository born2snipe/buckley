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
package buckley.maven;

import java.io.File;


public class CompilablePdf {
    private File pdfTemplate;
    private File xml;
    private File compiledPdf;

    public CompilablePdf() {
    }

    public CompilablePdf(File pdfTemplate, File xml, File compiledPdf) {
        this.pdfTemplate = pdfTemplate;
        this.xml = xml;
        this.compiledPdf = compiledPdf;
    }

    public File getPdfTemplate() {
        return pdfTemplate;
    }

    public void setPdfTemplate(File pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) {
        this.xml = xml;
    }

    public File getCompiledPdf() {
        return compiledPdf;
    }

    public void setCompiledPdf(File compiledPdf) {
        this.compiledPdf = compiledPdf;
    }
}
