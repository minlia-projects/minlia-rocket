package com.minlia.rocket.stateful.body.impl;

import com.minlia.rocket.stateful.body.ApiResponseBody;
import com.minlia.rocket.stateful.body.StatefulBody;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author will
 */
public class SuccessResponseBody<T> extends ApiResponseBody {

  public SuccessResponseBody() {
    this.setMessage(StatefulBody.SUCCESS_MESSAGE);
    this.setCode(StatefulBody.SUCCESS);
    this.setStatus(StatefulBody.SUCCESS);
    this.setPayload(super.payload);
  }

  public SuccessResponseBody(Integer code, Integer status, String message, T payload) {
    super(StatefulBody.SUCCESS, StatefulBody.SUCCESS, StatefulBody.SUCCESS_MESSAGE, payload);
    this.code = code;
    this.status = status;
    this.message = message;
    this.payload = payload;
  }

  public SuccessResponseBody(Integer code, Integer status, T payload) {
    super(StatefulBody.SUCCESS, StatefulBody.SUCCESS, StatefulBody.SUCCESS_MESSAGE, (Object) null);
    this.code = code;
    this.status = status;
    this.payload = payload;
  }

  public SuccessResponseBody(Integer code, T payload) {
    super(StatefulBody.SUCCESS, StatefulBody.SUCCESS, StatefulBody.SUCCESS_MESSAGE, (Object) null);
    this.code = code;
    this.payload = payload;
  }

  public SuccessResponseBody(Integer code) {
    super(StatefulBody.SUCCESS, StatefulBody.SUCCESS, StatefulBody.SUCCESS_MESSAGE, (Object) null);
    this.code = code;
  }

  public SuccessResponseBody(T payload) {
    super(StatefulBody.SUCCESS, StatefulBody.SUCCESS, StatefulBody.SUCCESS_MESSAGE, (Object) null);
    this.payload = payload;
  }

  public static <T> SuccessResponseBody.SuccessResponseBodyBuilder<T> builder() {
    return new SuccessResponseBody.SuccessResponseBodyBuilder();
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }


  public static class SuccessResponseBodyBuilder<T> {

    SuccessResponseBody instance = null;

    SuccessResponseBodyBuilder() {
      instance = new SuccessResponseBody();
    }

    public SuccessResponseBody<T> build() {
      return instance;
    }

    public SuccessResponseBody.SuccessResponseBodyBuilder<T> code(Integer code) {
      instance.code = code;
      return this;
    }

    public SuccessResponseBody.SuccessResponseBodyBuilder<T> status(Integer status) {
      instance.status = status;
      return this;
    }

    public SuccessResponseBody.SuccessResponseBodyBuilder<T> message(String message) {
      instance.message = message;
      return this;
    }

    public SuccessResponseBody.SuccessResponseBodyBuilder<T> payload(T payload) {
      instance.payload = payload;
      return this;
    }


    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }


  }
}
