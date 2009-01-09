package org.codeclub.buckley.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codeclub.buckley.Document;
import org.codeclub.buckley.compile.Compiler;
import org.codeclub.buckley.io.XmlSerializer;

/**
 * @goal compile
 */

public class CompileMojo extends AbstractMojo {
    private XmlSerializer serializer;

    /**
     * List of files to exclude. Specified as fileset patterns.
     *
     * @required
     * @parameter
     */
    private CompilablePdf[] compilables;

    public CompileMojo() {
        this(new XmlSerializer());
    }

    public CompileMojo(XmlSerializer serializer) {
        this.serializer = serializer;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (compilables.length == 0) {
            getLog().info("No pdf compilables given");
            return;
        }

        for (CompilablePdf pdf : compilables) {
            getLog().info("Compiling " + pdf.getPdfTemplate() + " against " + pdf.getXml() + " output to " + pdf.getCompiledPdf());
            Document document = serializer.deserialize(pdf.getXml());
            Compiler compiler = initializeCompiler(document);
            compiler.compile(pdf.getPdfTemplate(), pdf.getCompiledPdf(), document);
        }
    }

    protected Compiler initializeCompiler(Document document) {
        return new Compiler(document.getFontRegistry());
    }

    public void setCompilables(CompilablePdf[] compilables) {
        this.compilables = compilables;
    }
}
