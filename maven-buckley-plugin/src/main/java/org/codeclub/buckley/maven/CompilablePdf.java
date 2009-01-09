package org.codeclub.buckley.maven;

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
