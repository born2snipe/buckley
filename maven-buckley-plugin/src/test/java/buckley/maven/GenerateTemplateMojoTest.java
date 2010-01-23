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

import buckley.Document;
import buckley.io.DocumentWriter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.*;


public class GenerateTemplateMojoTest {
    @Test
    public void test() throws MojoExecutionException, MojoFailureException {
        DocumentWriter writer = mock(DocumentWriter.class);
        Log log = mock(Log.class);

        GenerateTemplateMojo mojo = new GenerateTemplateMojo(writer);
        mojo.setLog(log);

        mojo.execute();

        verify(log).info("Creating buckley template (template-file.buckley)");
        verify(writer).write(eq(new File("template-file.buckley")), isA(Document.class));
    }
}
