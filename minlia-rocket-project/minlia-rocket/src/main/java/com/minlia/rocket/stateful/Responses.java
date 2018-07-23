package com.minlia.rocket.stateful;


import static com.minlia.rocket.stateful.util.JsonUtils.jsonToType;
import static com.minlia.rocket.stateful.util.JsonUtils.toFilterJson;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Responses {

  public static final String WILDCARD_ALL = "**";
  public static final TypeReference MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
  };
  private volatile Map<String, Object> body;


  public static org.springframework.http.ResponseEntity<String> forbidden() {
    return status(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> forbidden(final T body) {
    return status(HttpStatus.FORBIDDEN, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> forbidden(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return notFound(body);
    }
    return status(HttpStatus.FORBIDDEN, body, excludeProperties);
  }

  // 417 expectation failed

  public static org.springframework.http.ResponseEntity<String> expectationFailed() {
    return status(HttpStatus.EXPECTATION_FAILED, HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> expectationFailed(final T body) {
    return status(HttpStatus.EXPECTATION_FAILED, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> expectationFailed(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return notFound(body);
    }
    return status(HttpStatus.EXPECTATION_FAILED, body, excludeProperties);
  }


  // 201 created
  public static org.springframework.http.ResponseEntity<String> created() {
    return status(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> created(final T body) {
    return status(HttpStatus.CREATED, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> created(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return notFound(body);
    }
    return status(HttpStatus.CREATED, body, excludeProperties);
  }


  public static org.springframework.http.ResponseEntity<String> unauthorized() {
    return status(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> unauthorized(final T body) {
    return status(HttpStatus.UNAUTHORIZED, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> unauthorized(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return notFound(body);
    }
    return status(HttpStatus.UNAUTHORIZED, body, excludeProperties);
  }


  public static org.springframework.http.ResponseEntity<String> notFound() {
    return status(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> notFound(final T body) {
    return status(HttpStatus.NOT_FOUND, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> notFound(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return notFound(body);
    }
    return status(HttpStatus.NOT_FOUND, body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> unprocessableEntity() {
    return status(HttpStatus.UNPROCESSABLE_ENTITY,
        HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> unprocessableEntity(final T body) {
    return status(HttpStatus.UNPROCESSABLE_ENTITY, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> unprocessableEntity(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return unprocessableEntity(body);
    }
    return status(HttpStatus.UNPROCESSABLE_ENTITY, body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> internalServerError() {
    return status(HttpStatus.INTERNAL_SERVER_ERROR,
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> internalServerError(final T body) {
    return status(HttpStatus.INTERNAL_SERVER_ERROR, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> internalServerError(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return internalServerError(body);
    }
    return status(HttpStatus.INTERNAL_SERVER_ERROR, body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> badRequest() {
    return status(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> badRequest(final T body) {
    return status(HttpStatus.BAD_REQUEST, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> badRequest(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return badRequest(body);
    }
    return status(HttpStatus.BAD_REQUEST, body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> accepted() {
    return status(HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> accepted(final T body) {
    return status(HttpStatus.ACCEPTED, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> accepted(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return accepted(body);
    }
    return status(HttpStatus.ACCEPTED, body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> noContent() {
    return status(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.getReasonPhrase());
  }

  public static <T> org.springframework.http.ResponseEntity<T> noContent(final T body) {
    return status(HttpStatus.NO_CONTENT, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> noContent(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return noContent(body);
    }
    return status(HttpStatus.NO_CONTENT, body, excludeProperties);
  }

  private static org.springframework.http.ResponseEntity.BodyBuilder buildStatus(int status) {
    return org.springframework.http.ResponseEntity.status(status);
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final int status) {
    return buildStatus(status).build();
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final int status,
      final T body) {
    return buildStatus(status).body(body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final int status,
      final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return status(status, body);
    }
    return status(status, (T) jsonToType(toFilterJson(body, excludeProperties), body.getClass())
    );
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final HttpStatus status) {
    return status(status.value());
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final HttpStatus status,
      final T body) {
    return status(status.value(), body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> status(final HttpStatus status,
      final T body,
      final String excludeProperties) {
    return status(status.value(), body, excludeProperties);
  }

  public static org.springframework.http.ResponseEntity<String> ok() {
    return status(HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
  }


  public static <T> org.springframework.http.ResponseEntity<T> ok(final T body) {
    return status(HttpStatus.OK, body);
  }

  public static <T> org.springframework.http.ResponseEntity<T> ok(final T body,
      final String excludeProperties) {
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return ok(body);
    }
    return status(HttpStatus.OK, body, excludeProperties);
  }

  public static boolean isOk(org.springframework.http.ResponseEntity responseEntity) {
    if (Objects.isNull(responseEntity) || Objects.isNull(responseEntity.getStatusCode())) {
      return false;
    }
    return HttpStatus.OK.equals(responseEntity.getStatusCode());
  }

  public static boolean isNotOk(org.springframework.http.ResponseEntity responseEntity) {
    return !isOk(responseEntity);
  }

  public static boolean is2xxSuccessful(org.springframework.http.ResponseEntity responseEntity) {
    if (Objects.isNull(responseEntity) || Objects.isNull(responseEntity.getStatusCode())) {
      return false;
    }
    return responseEntity.getStatusCode().is2xxSuccessful();
  }

  public static boolean isNot2xxSuccessful(org.springframework.http.ResponseEntity responseEntity) {
    return !is2xxSuccessful(responseEntity);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildNotFound() {
    return Responses.notFound(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildAccepted() {
    return Responses.accepted(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildNoContent() {
    return Responses.noContent(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildUnprocessableEntity() {
    return Responses.unprocessableEntity(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildBadRequest() {
    return Responses.badRequest(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildInternalServerError() {
    return Responses.internalServerError(this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildByStatus(
      final HttpStatus status) {
    return Responses.status(status, this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildByStatus(
      final int status) {
    return Responses.status(status, this.body);
  }

  public org.springframework.http.ResponseEntity<Map<String, Object>> buildOk() {
    return Responses.ok(this.body);
  }

  public Responses add(final String key, final Object value) {
    if (MapUtils.isEmpty(this.body)) {
      this.body = new HashMap<>(2);
      this.body.put(key, value);
      return this;
    }
    this.body.put(key, value);
    return this;
  }

  public Responses flushBodyByFilterFields(final String excludeProperties) {
    if (MapUtils.isEmpty(this.body)) {
      return this;
    }
    if (null == excludeProperties || WILDCARD_ALL.equals(excludeProperties)) {
      return this;
    }
    this.body = jsonToType(toFilterJson(this.body, excludeProperties), MAP_TYPE_REFERENCE);
    return this;
  }


}






