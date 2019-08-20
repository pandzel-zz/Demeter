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
package com.panforge.demeter.server.beans;

import com.panforge.demeter.core.content.PageCursor;

/**
 *
 * @author Piotr Andzel
 */
public class PageCursorImpl implements PageCursor {
  
  public String pageCursorStr;
  public Integer cursor;

  @Override
  public int cursor() {
    return cursor!=null? cursor: 0;
  }

  public PageCursorImpl(String pageCursorStr, int cursor) {
    this.pageCursorStr = pageCursorStr;
    this.cursor = cursor;
  }
  
}
