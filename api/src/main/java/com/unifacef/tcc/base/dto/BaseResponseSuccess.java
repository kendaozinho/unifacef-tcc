package com.unifacef.tcc.base.dto;

import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.util.ApplicationUtil;
import com.unifacef.tcc.util.ServerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public final class BaseResponseSuccess<T> {
  private Meta meta;
  private ArrayList<T> records;

  private BaseResponseSuccess() {
  }

  public static <T> BaseResponseSuccess<T> instanceOf(T object) {
    return BaseResponseSuccess.getInstance(object, 0, 100);
  }

  public static <T> BaseResponseSuccess<T> instanceOf(Iterable<T> object) {
    return BaseResponseSuccess.getInstance(object, 0, 100);
  }

  public static <T> BaseResponseSuccess<T> instanceOf(Iterable<T> object, Integer offset, Integer limit) {
    return BaseResponseSuccess.getInstance(object, offset, limit);
  }

  private static BaseResponseSuccess getInstance(Object object, Integer offset, Integer limit) {
    if (object == null) {
      throw new NotFoundException("Data not found");
    }

    if (offset == null || offset < 0) {
      offset = 0;
    }

    if (limit == null || limit < 0) {
      limit = 0;
    }

    Iterable<Object> objects =
        (object instanceof Iterable<?> ? (Iterable<Object>) object : Collections.singletonList(object));

    Iterator<Object> oldList = objects.iterator();
    ArrayList<Object> newList = new java.util.ArrayList<>();

    int count = 0;
    while (oldList.hasNext()) {
      if (count >= (offset * limit) && count < ((offset * limit) + limit)) {
        newList.add(oldList.next());
      } else {
        oldList.next();
      }
      count++;
    }

    if (newList.isEmpty()) {
      throw new NotFoundException("Data not found");
    }

    BaseResponseSuccess response = new BaseResponseSuccess();
    response.setMeta(ServerUtil.getHostName(), ApplicationUtil.getVersion(), offset, limit, newList.size());
    response.setRecords(newList);
    return response;
  }

  public Meta getMeta() {
    return this.meta;
  }

  private void setMeta(String server, String version, Integer offset, Integer limit, Integer recordCount) {
    this.meta = new Meta(server, version, offset, limit, recordCount);
  }

  public ArrayList<T> getRecords() {
    if (this.records == null) {
      this.records = new ArrayList();
    }
    return this.records;
  }

  private void setRecords(ArrayList<T> records) {
    this.records = records;
  }

  private class Meta {
    private String server;
    private String version;
    private Integer offset;
    private Integer limit;
    private Integer recordCount;

    public Meta(String server, String version, Integer offset, Integer limit, Integer recordCount) {
      this.server = server;
      this.version = version;
      this.offset = offset;
      this.limit = limit;
      this.recordCount = recordCount;
    }

    public String getServer() {
      return this.server;
    }

    public String getVersion() {
      return this.version;
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
