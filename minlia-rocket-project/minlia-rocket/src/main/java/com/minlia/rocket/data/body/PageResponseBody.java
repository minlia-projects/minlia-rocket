package com.minlia.rocket.data.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.stateful.body.Body;
import java.util.List;
import lombok.Data;

/**
 * @author will
 */
@Data
public class PageResponseBody<T> implements Body {


  @JsonProperty
  private Long page;

  @JsonProperty
  private Long size;

  @JsonProperty
  private Long totalPages;

  @JsonProperty
  private Long totalElements;
  @JsonProperty
  private List<T> items;

}
