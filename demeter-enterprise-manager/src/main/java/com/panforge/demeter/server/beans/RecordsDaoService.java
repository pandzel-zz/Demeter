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

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.panforge.demeter.server.Connection;
import com.panforge.demeter.server.RecordsDao;
import com.panforge.demeter.server.elements.RecordData;
import com.panforge.demeter.server.elements.SetData;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Records DAO service.
 */
@Service
public class RecordsDaoService implements RecordsDao {
  private final Logger LOG = LoggerFactory.getLogger(RecordsDaoService.class);
  
  private Connection conn;
  
  /**
   * Creates instance of the DAO service
   * @param conn connection
   */
  @Autowired
  public RecordsDaoService(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public List<RecordData> listRecords() {
    ResultSet rs = conn.execute("select * from records");
    return rs.all().stream()
            .map(row -> {
              RecordData recordData = new RecordData();
              readRow(recordData, row);
              return recordData;
            })
            .collect(Collectors.toList());
  }
  
  private void readRow(RecordData setData, Row row) {
    setData.id = row.getUuid("id");
    setData.title = row.getString("title");
    setData.creator = row.getString("creator");
    setData.subject = row.getString("subject");
    setData.description = row.getString("description");
    setData.publisher = row.getString("publisher");
    setData.contributor = row.getString("contributor");
    setData.date = row.getLocalDate("date");
    setData.format = row.getString("format");
    setData.identifier = row.getString("identifier");
    setData.source = row.getString("source");
    setData.language = row.getString("language");
    setData.relation = row.getString("relation");
    setData.coverage = row.getString("coverage");
    setData.rights = row.getString("rights");
  }
  
}
