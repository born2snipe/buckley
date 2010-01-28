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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class LocationAndSizeExtractorTest {
    private LocationAndSizeExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new LocationAndSizeExtractor();
    }

    @Test
    public void test_extract_FieldExistMultipleTimes() {
        Field field = mock(Field.class);
        AcroFields acroFields = mock(AcroFields.class);

        when(acroFields.getFieldPositions("field")).thenReturn(new float[]{0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f});

        extractor.extract(1, field, "field", acroFields);

        verify(field).setX(6.0f);
        verify(field).setY(8.0f);
        verify(field).setWidth(2.0f);
        verify(field).setHeight(2.0f);
    }

    @Test
    public void test_extract() {
        Field field = mock(Field.class);
        AcroFields acroFields = mock(AcroFields.class);

        when(acroFields.getFieldPositions("field")).thenReturn(new float[]{0.0f, 1.0f, 2.0f, 3.0f, 4.0f});

        extractor.extract(0, field, "field", acroFields);

        verify(field).setX(1.0f);
        verify(field).setY(3.0f);
        verify(field).setWidth(2.0f);
        verify(field).setHeight(2.0f);
    }

    @Test
    public void test_canExtract() {
        assertTrue(extractor.canExtract(null));
    }


}
