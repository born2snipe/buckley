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
package buckley.compile;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.collection.PdfCollection;
import org.mockito.Mockito;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class StubPdfWriter extends PdfWriter {
    private List<PdfAnnotation> annotations = new ArrayList<PdfAnnotation>();
    private PdfWriter delegate = Mockito.mock(PdfWriter.class);

    @Override
    public void addAnnotation(PdfAnnotation pdfAnnotation) {
        annotations.add(pdfAnnotation);
    }

    public List<PdfAnnotation> getAnnotations() {
        return annotations;
    }

    @Override
    public float getVerticalPosition(boolean b) {
        return delegate.getVerticalPosition(b);
    }

    @Override
    public PdfContentByte getDirectContent() {
        return delegate.getDirectContent();
    }

    @Override
    public PdfContentByte getDirectContentUnder() {
        return delegate.getDirectContentUnder();
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject) throws IOException {
        return delegate.addToBody(pdfObject);
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject, boolean b) throws IOException {
        return delegate.addToBody(pdfObject, b);
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject, PdfIndirectReference pdfIndirectReference) throws IOException {
        return delegate.addToBody(pdfObject, pdfIndirectReference);
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject, PdfIndirectReference pdfIndirectReference, boolean b) throws IOException {
        return delegate.addToBody(pdfObject, pdfIndirectReference, b);
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject, int i) throws IOException {
        return delegate.addToBody(pdfObject, i);
    }

    @Override
    public PdfIndirectObject addToBody(PdfObject pdfObject, int i, boolean b) throws IOException {
        return delegate.addToBody(pdfObject, i, b);
    }

    @Override
    protected PdfDictionary getCatalog(PdfIndirectReference pdfIndirectReference) {
//        return delegate.getCatalog(pdfIndirectReference);
        return null;
    }

    @Override
    public PdfDictionary getExtraCatalog() {
        return delegate.getExtraCatalog();
    }

    @Override
    public void setLinearPageMode() {
        delegate.setLinearPageMode();
    }

    @Override
    public int reorderPages(int[] ints) throws DocumentException {
        return delegate.reorderPages(ints);
    }

    @Override
    public PdfIndirectReference getPageReference(int i) {
        return delegate.getPageReference(i);
    }

    @Override
    public int getPageNumber() {
        return delegate.getPageNumber();
    }

    @Override
    public int getCurrentPageNumber() {
        return delegate.getCurrentPageNumber();
    }

    @Override
    public void setPageEvent(PdfPageEvent pdfPageEvent) {
        delegate.setPageEvent(pdfPageEvent);
    }

    @Override
    public PdfPageEvent getPageEvent() {
        return delegate.getPageEvent();
    }

    @Override
    public void open() {
        delegate.open();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    protected void addSharedObjectsToBody() throws IOException {
    }

    @Override
    public PdfOutline getRootOutline() {
        return delegate.getRootOutline();
    }

    @Override
    public void setOutlines(List list) {
        delegate.setOutlines(list);
    }

    @Override
    protected void writeOutlines(PdfDictionary pdfDictionary, boolean b) throws IOException {

    }

    @Override
    public void setPdfVersion(char c) {
        delegate.setPdfVersion(c);
    }

    @Override
    public void setAtLeastPdfVersion(char c) {
        delegate.setAtLeastPdfVersion(c);
    }

    @Override
    public void setPdfVersion(PdfName pdfName) {
        delegate.setPdfVersion(pdfName);
    }

    @Override
    public void setViewerPreferences(int i) {
        delegate.setViewerPreferences(i);
    }

    @Override
    public void addViewerPreference(PdfName pdfName, PdfObject pdfObject) {
        delegate.addViewerPreference(pdfName, pdfObject);
    }

    @Override
    public void setPageLabels(PdfPageLabels pdfPageLabels) {
        delegate.setPageLabels(pdfPageLabels);
    }

    @Override
    public void addJavaScript(PdfAction pdfAction) {
        delegate.addJavaScript(pdfAction);
    }

    @Override
    public void addJavaScript(String s, boolean b) {
        delegate.addJavaScript(s, b);
    }

    @Override
    public void addJavaScript(String s) {
        delegate.addJavaScript(s);
    }

    @Override
    public void addJavaScript(String s, PdfAction pdfAction) {
        delegate.addJavaScript(s, pdfAction);
    }

    @Override
    public void addJavaScript(String s, String s1, boolean b) {
        delegate.addJavaScript(s, s1, b);
    }

    @Override
    public void addJavaScript(String s, String s1) {
        delegate.addJavaScript(s, s1);
    }

    @Override
    public void addFileAttachment(String s, byte[] bytes, String s1, String s2) throws IOException {
        delegate.addFileAttachment(s, bytes, s1, s2);
    }

    @Override
    public void addFileAttachment(String s, PdfFileSpecification pdfFileSpecification) throws IOException {
        delegate.addFileAttachment(s, pdfFileSpecification);
    }

    @Override
    public void addFileAttachment(PdfFileSpecification pdfFileSpecification) throws IOException {
        delegate.addFileAttachment(pdfFileSpecification);
    }

    @Override
    public void setOpenAction(String s) {
        delegate.setOpenAction(s);
    }

    @Override
    public void setOpenAction(PdfAction pdfAction) {
        delegate.setOpenAction(pdfAction);
    }

    @Override
    public void setAdditionalAction(PdfName pdfName, PdfAction pdfAction) throws DocumentException {
        delegate.setAdditionalAction(pdfName, pdfAction);
    }

    @Override
    public void setCollection(PdfCollection pdfCollection) {
        delegate.setCollection(pdfCollection);
    }

    @Override
    public PdfAcroForm getAcroForm() {
        return delegate.getAcroForm();
    }

    @Override
    public void addCalculationOrder(PdfFormField pdfFormField) {
        delegate.addCalculationOrder(pdfFormField);
    }

    @Override
    public void setSigFlags(int i) {
        delegate.setSigFlags(i);
    }

    @Override
    public void setXmpMetadata(byte[] bytes) {
        delegate.setXmpMetadata(bytes);
    }

    @Override
    public void setPageXmpMetadata(byte[] bytes) {
        delegate.setPageXmpMetadata(bytes);
    }

    @Override
    public void createXmpMetadata() {
        delegate.createXmpMetadata();
    }

    @Override
    public void setPDFXConformance(int i) {
        delegate.setPDFXConformance(i);
    }

    @Override
    public int getPDFXConformance() {
        return delegate.getPDFXConformance();
    }

    @Override
    public boolean isPdfX() {
        return delegate.isPdfX();
    }

    @Override
    public void setOutputIntents(String s, String s1, String s2, String s3, byte[] bytes) throws IOException {
        delegate.setOutputIntents(s, s1, s2, s3, bytes);
    }

    @Override
    public boolean setOutputIntents(PdfReader pdfReader, boolean b) throws IOException {
        return delegate.setOutputIntents(pdfReader, b);
    }

    @Override
    public void setEncryption(byte[] bytes, byte[] bytes1, int i, int i1) throws DocumentException {
        delegate.setEncryption(bytes, bytes1, i, i1);
    }

    @Override
    public void setEncryption(Certificate[] certificates, int[] ints, int i) throws DocumentException {
        delegate.setEncryption(certificates, ints, i);
    }

    @Override
    public void setEncryption(byte[] bytes, byte[] bytes1, int i, boolean b) throws DocumentException {
        delegate.setEncryption(bytes, bytes1, i, b);
    }

    @Override
    public void setEncryption(boolean b, String s, String s1, int i) throws DocumentException {
        delegate.setEncryption(b, s, s1, i);
    }

    @Override
    public void setEncryption(int i, String s, String s1, int i1) throws DocumentException {
        delegate.setEncryption(i, s, s1, i1);
    }

    @Override
    public boolean isFullCompression() {
        return delegate.isFullCompression();
    }

    @Override
    public void setFullCompression() {
        delegate.setFullCompression();
    }

    @Override
    public int getCompressionLevel() {
        return delegate.getCompressionLevel();
    }

    @Override
    public void setCompressionLevel(int i) {
        delegate.setCompressionLevel(i);
    }

    @Override
    public void releaseTemplate(PdfTemplate pdfTemplate) throws IOException {
        delegate.releaseTemplate(pdfTemplate);
    }

    @Override
    public PdfImportedPage getImportedPage(PdfReader pdfReader, int i) {
        return delegate.getImportedPage(pdfReader, i);
    }

    @Override
    public void freeReader(PdfReader pdfReader) throws IOException {
        delegate.freeReader(pdfReader);
    }

    @Override
    public int getCurrentDocumentSize() {
        return delegate.getCurrentDocumentSize();
    }

    @Override
    protected int getNewObjectNumber(PdfReader pdfReader, int i, int i1) {
//        return delegate.getNewObjectNumber(pdfReader, i, i1);
        return 0;
    }

    @Override
    public void setTagged() {
        delegate.setTagged();
    }

    @Override
    public boolean isTagged() {
        return delegate.isTagged();
    }

    @Override
    public PdfStructureTreeRoot getStructureTreeRoot() {
        return delegate.getStructureTreeRoot();
    }

    @Override
    public PdfOCProperties getOCProperties() {
        return delegate.getOCProperties();
    }

    @Override
    public void addOCGRadioGroup(ArrayList arrayList) {
        delegate.addOCGRadioGroup(arrayList);
    }

    @Override
    public void lockLayer(PdfLayer pdfLayer) {
        delegate.lockLayer(pdfLayer);
    }

    @Override
    protected void fillOCProperties(boolean b) {

    }

    @Override
    public Rectangle getPageSize() {
        return delegate.getPageSize();
    }

    @Override
    public void setCropBoxSize(Rectangle rectangle) {
        delegate.setCropBoxSize(rectangle);
    }

    @Override
    public void setBoxSize(String s, Rectangle rectangle) {
        delegate.setBoxSize(s, rectangle);
    }

    @Override
    public Rectangle getBoxSize(String s) {
        return delegate.getBoxSize(s);
    }

    @Override
    public void setPageEmpty(boolean b) {
        delegate.setPageEmpty(b);
    }

    @Override
    public void setPageAction(PdfName pdfName, PdfAction pdfAction) throws DocumentException {
        delegate.setPageAction(pdfName, pdfAction);
    }

    @Override
    public void setDuration(int i) {
        delegate.setDuration(i);
    }

    @Override
    public void setTransition(PdfTransition pdfTransition) {
        delegate.setTransition(pdfTransition);
    }

    @Override
    public void setThumbnail(Image image) throws PdfException, DocumentException {
        delegate.setThumbnail(image);
    }

    @Override
    public PdfDictionary getGroup() {
        return delegate.getGroup();
    }

    @Override
    public void setGroup(PdfDictionary pdfDictionary) {
        delegate.setGroup(pdfDictionary);
    }

    @Override
    public float getSpaceCharRatio() {
        return delegate.getSpaceCharRatio();
    }

    @Override
    public void setSpaceCharRatio(float v) {
        delegate.setSpaceCharRatio(v);
    }

    @Override
    public void setRunDirection(int i) {
        delegate.setRunDirection(i);
    }

    @Override
    public int getRunDirection() {
        return delegate.getRunDirection();
    }

    @Override
    public float getUserunit() {
        return delegate.getUserunit();
    }

    @Override
    public void setUserunit(float v) throws DocumentException {
        delegate.setUserunit(v);
    }

    @Override
    public PdfDictionary getDefaultColorspace() {
        return delegate.getDefaultColorspace();
    }

    @Override
    public void setDefaultColorspace(PdfName pdfName, PdfObject pdfObject) {
        delegate.setDefaultColorspace(pdfName, pdfObject);
    }

    @Override
    public boolean isStrictImageSequence() {
        return delegate.isStrictImageSequence();
    }

    @Override
    public void setStrictImageSequence(boolean b) {
        delegate.setStrictImageSequence(b);
    }

    @Override
    public void clearTextWrap() throws DocumentException {
        delegate.clearTextWrap();
    }

    @Override
    public PdfName addDirectImageSimple(Image image) throws PdfException, DocumentException {
        return delegate.addDirectImageSimple(image);
    }

    @Override
    public PdfName addDirectImageSimple(Image image, PdfIndirectReference pdfIndirectReference) throws PdfException, DocumentException {
        return delegate.addDirectImageSimple(image, pdfIndirectReference);
    }

    @Override
    protected PdfIndirectReference add(PdfICCBased pdfICCBased) {
//        return delegate.add(pdfICCBased);
        return null;
    }

    @Override
    public boolean fitsPage(Table table, float v) {
        return delegate.fitsPage(table, v);
    }

    @Override
    public boolean fitsPage(Table table) {
        return delegate.fitsPage(table);
    }

    @Override
    public boolean isUserProperties() {
        return delegate.isUserProperties();
    }

    @Override
    public void setUserProperties(boolean b) {
        delegate.setUserProperties(b);
    }

    @Override
    public boolean isRgbTransparencyBlending() {
        return delegate.isRgbTransparencyBlending();
    }

    @Override
    public void setRgbTransparencyBlending(boolean b) {
        delegate.setRgbTransparencyBlending(b);
    }

    @Override
    public boolean add(Element element) throws DocumentException {
        return delegate.add(element);
    }

    @Override
    public boolean setPageSize(Rectangle rectangle) {
        return delegate.setPageSize(rectangle);
    }

    @Override
    public boolean setMargins(float v, float v1, float v2, float v3) {
        return delegate.setMargins(v, v1, v2, v3);
    }

    @Override
    public boolean newPage() {
        return delegate.newPage();
    }

    @Override
    public void setHeader(HeaderFooter headerFooter) {
        delegate.setHeader(headerFooter);
    }

    @Override
    public void resetHeader() {
        delegate.resetHeader();
    }

    @Override
    public void setFooter(HeaderFooter headerFooter) {
        delegate.setFooter(headerFooter);
    }

    @Override
    public void resetFooter() {
        delegate.resetFooter();
    }

    @Override
    public void resetPageCount() {
        delegate.resetPageCount();
    }

    @Override
    public void setPageCount(int i) {
        delegate.setPageCount(i);
    }

    @Override
    public void pause() {
        delegate.pause();
    }

    @Override
    public boolean isPaused() {
        return delegate.isPaused();
    }

    @Override
    public void resume() {
        delegate.resume();
    }

    @Override
    public void flush() {
        delegate.flush();
    }

    @Override
    protected void write(String s) throws IOException {

    }

    @Override
    protected void addTabs(int i) throws IOException {

    }

    @Override
    protected void write(String s, String s1) throws IOException {

    }

    @Override
    protected void writeStart(String s) throws IOException {

    }

    @Override
    protected void writeEnd(String s) throws IOException {

    }

    @Override
    protected void writeEnd() throws IOException {
        ;
    }

    @Override
    protected boolean writeMarkupAttributes(Properties properties) throws IOException {
//        return delegate.writeMarkupAttributes(properties);
        return false;
    }

    @Override
    public boolean isCloseStream() {
        return delegate.isCloseStream();
    }

    @Override
    public void setCloseStream(boolean b) {
        delegate.setCloseStream(b);
    }

    @Override
    public boolean setMarginMirroring(boolean b) {
        return delegate.setMarginMirroring(b);
    }

    @Override
    public PdfIndirectReference getPdfIndirectReference() {
        return delegate.getPdfIndirectReference();
    }


}
