package com.unifacef.tcc.controller.dto;

import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.util.ApplicationUtil;
import com.unifacef.tcc.util.ServerUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class BaseResponseSuccess<T> {
  private Meta meta;
  private List<T> records;

  private BaseResponseSuccess() {
  }

  public static <T> BaseResponseSuccess<T> instanceOf(T object,
                                                      Integer offset,
                                                      Integer limit) {
    return BaseResponseSuccess.getInstance(object, offset, limit);
  }

  private static BaseResponseSuccess getInstance(Object object, Integer offset, Integer limit) {
    if (object == null) {
      return null;
    }

    // default values
    if (offset == null || offset < 0) {
      offset = 0; // index where the search begins
    }
    if (limit == null || limit <= 0) {
      limit = 10; // number of records per page
    }

    Iterable<Object> objects = (object instanceof Iterable<?> ? (Iterable<Object>) object
        : (Iterable<Object>) Arrays.asList(object));

    Iterator<Object> oldList = objects.iterator();
    List<Object> newList = new java.util.ArrayList<>(Collections.emptyList());

    int i = 0;
    while (oldList.hasNext()) {
      if (i >= (offset * limit) && i < ((offset * limit) + limit)) {
        newList.add(oldList.next());
      } else {
        oldList.next();
      }
      i++;
    }

    if (newList.isEmpty()) {
      throw new NotFoundException("Data not found");
    }

    BaseResponseSuccess response = new BaseResponseSuccess();
    response.setRecords(newList);
    response.setMeta(ServerUtil.getHostName(), ApplicationUtil.getVersion(), limit, offset, newList.size());
    return response;
  }

  public Meta getMeta() {
    return this.meta;
  }

  private void setMeta(String version, String server, Integer offset, Integer limit, Integer recordCount) {
    this.meta = new Meta(version, server, offset, limit, recordCount);
  }

  public List<T> getRecords() {
    if (this.records == null) {
      this.records = Collections.emptyList();
    }
    return this.records;
  }

  private void setRecords(List<T> records) {
    this.records = records;
  }

  private class Meta {
    private String version;
    private String server;
    private Integer offset;
    private Integer limit;
    private Integer recordCount;

    public Meta(String version, String server, Integer offset, Integer limit, Integer recordCount) {
      this.version = version;
      this.server = server;
      this.offset = offset;
      this.limit = limit;
      this.recordCount = recordCount;
    }

    public String getVersion() {
      return this.version;
    }

    public String getServer() {
      return this.server;
    }

    public Integer getOffset() {
      return this.offset;
    }

    public Integer getLimit() {
      return this.limit;
    }

    public Integer getRecordCount() {
      return this.recordCount;
    }
  }
}
