/*
 * Copyright 2019 Piotr Andzel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.panforge.demeter.core.model.response.guidelines;

import java.net.URL;
import org.apache.commons.lang3.Validate;

/**
 * Text or URL.
 */
public final class TextOrURL {
  /** text */
  public final String text;
  /** URL */
  public final URL url;

  /**
   * Creates text or URL element.
   * @param text text (mandatory)
   */
  public TextOrURL(String text) {
    Validate.notBlank(text, "Missing text.");
    this.text = text;
    this.url = null;
  }
  
  /**
   * Creates text or URL element.
   * @param url URL
   */
  public TextOrURL(URL url) {
    Validate.notNull(url, "Missing URL.");
    this.text = null;
    this.url = url;
  }

  @Override
  public String toString() {
    return "TextOrURL{" + "text=" + text + ", url=" + url + '}';
  }
}
