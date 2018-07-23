package com.minlia.rocket.swagger.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.swagger2.web.Swagger2Controller;

/**
 * 去除operationId后面自动添加的_1递增型后缀
 */
@ControllerAdvice(assignableTypes = Swagger2Controller.class)
public class ApiDocsAdvice implements ResponseBodyAdvice<Object> {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    try {
      JsonNode document = this.objectMapper.readTree(((Json) body).value());
      this.sanitize(document, "operationId");
      return document;
    } catch (Exception e) {
      return body;
    }
  }

  private void sanitize(JsonNode parent, String fieldName) {
    if (parent.has(fieldName)) {
      String text = ((ObjectNode) parent).get(fieldName).textValue();
      if (null != text) {
        int pos = text.indexOf("_");
        if (pos > -1) {
          ((ObjectNode) parent).set(fieldName, new TextNode(text.substring(0, pos)));
        }
      }
    }

    for (JsonNode child : parent) {
      this.sanitize(child, fieldName);
    }
  }

}