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


public class TextField extends Field {

    public TextField(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    public TextField(String name, float x, float y, float width, float height, String fontName, Float fontSize) {
        super(name, x, y, width, height, fontName, fontSize);
    }
}
