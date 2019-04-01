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
package com.panforge.demeter.core.model.request;

import com.panforge.demeter.core.utils.ParamProcessor;
import com.panforge.demeter.core.model.Verb;
import com.panforge.demeter.core.api.exception.BadArgumentException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.panforge.demeter.core.utils.DateTimeUtils.parseRequestTimestamp;
import static com.panforge.demeter.core.utils.QueryUtils.trimParams;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.lang3.Validate;

/**
 * ListRecords request.
 */
public class ListRecordsRequest extends RequestWithToken {

  private String metadataPrefix;
  private OffsetDateTime from;
  private OffsetDateTime until;
  private String set;
  
  /**
   * Creates instance of the request.
   * @param resumptionToken resumption token
   */
  public ListRecordsRequest(String resumptionToken) {
    super(Verb.ListRecords);
    this.resumptionToken = resumptionToken;
    Validate.notEmpty(resumptionToken, "Missing resumption token");
  }

  /**
   * Creates instance of the request.
   * @param from the 'from' date
   * @param until the 'until' date
   * @param metadataPrefix the metadata prefix
   * @param set the set
   */
  public ListRecordsRequest(String metadataPrefix, OffsetDateTime from, OffsetDateTime until, String set) {
    super(Verb.ListRecords);
    this.from = from;
    this.until = until;
    this.metadataPrefix = metadataPrefix;
    this.set = set;
    Validate.notNull(metadataPrefix, "Missing metadata prefix");
  }

  /**
   * Creates instance of the request.
   */
  ListRecordsRequest() {
    super(Verb.ListRecords);
  }

  /**
   * Gets 'from' date.
   * @return 'from' date
   */
  public OffsetDateTime getFrom() {
    return from;
  }

  /**
   * Gets 'until' date.
   * @return 'until' date
   */
  public OffsetDateTime getUntil() {
    return until;
  }

  /**
   * Gets metadata prefix.
   * @return metadata prefix
   */
  public String getMetadataPrefix() {
    return metadataPrefix;
  }

  /**
   * Gets set.
   * @return set
   */
  public String getSet() {
    return set;
  }

  /**
   * Creates request from parameters.
   * @param params parameters
   * @return request
   * @throws BadArgumentException if creation fails
   */
  public static ListRecordsRequest create(Map<String, List<String>> params) throws BadArgumentException {
    params = trimParams(params);
    ListRecordsRequest request = new ListRecordsRequest();
    if (params.containsKey("resumptionToken")) {
      ParamProcessor
              .with("resumptionToken", v -> {
                if (v != null) {
                  if (v.size() > 1) {
                    throw new BadArgumentException(String.format("Illegal number of resumption tokens: %s", v.stream().collect(Collectors.joining(", "))));
                  }
                  request.resumptionToken = v.get(0);
                }
              })
              .build().execute(params);
    } else {
      ParamProcessor
              .with("from", v -> {
                if (v != null) {
                  if (v.size() > 1) {
                    throw new BadArgumentException(String.format("Illegal number of identifiers: %s", v.stream().collect(Collectors.joining(", "))));
                  }
                  request.from = parseRequestTimestamp(v.get(0));
                }
              })
              .with("until", v -> {
                if (v != null) {
                  if (v.size() > 1) {
                    throw new BadArgumentException(String.format("Illegal number of identifiers: %s", v.stream().collect(Collectors.joining(", "))));
                  }
                  request.until = parseRequestTimestamp(v.get(0));
                }
              })
              .with("metadataPrefix", v -> {
                if (v == null) {
                  throw new BadArgumentException(String.format("Missing metadataPrefix"));
                }
                if (v.size() > 1) {
                  throw new BadArgumentException( String.format("Illegal number of identifiers: %s", v.stream().collect(Collectors.joining(", "))));
                }
                request.metadataPrefix = v.get(0);
              })
              .with("set", v -> {
                if (v != null) {
                  if (v.size() > 1) {
                    throw new BadArgumentException(String.format("Illegal number of identifiers: %s", v.stream().collect(Collectors.joining(", "))));
                  }
                  request.set = v.get(0);
                }
              })
              .build().execute(params);
    }
    return request;
  }

  @Override
  public Map<String, List<String>> getParameters() {
    Map<String,List<String>> parameters = new HashMap<>();
    parameters.put("verb", Arrays.asList(new String[]{ verb.name() }));
    if (resumptionToken!=null) {
      parameters.put("resumptionToken", Arrays.asList(new String[]{ resumptionToken }));
    }
    if (from!=null) {
      parameters.put("from", Arrays.asList(new String[]{ from.format(DateTimeFormatter.ISO_DATE_TIME) }));
    }
    if (until!=null) {
      parameters.put("from", Arrays.asList(new String[]{ until.format(DateTimeFormatter.ISO_DATE_TIME) }));
    }
    if (metadataPrefix!=null) {
      parameters.put("from", Arrays.asList(new String[]{ metadataPrefix }));
    }
    if (set!=null) {
      parameters.put("from", Arrays.asList(new String[]{ set }));
    }
    return parameters;
  }
}