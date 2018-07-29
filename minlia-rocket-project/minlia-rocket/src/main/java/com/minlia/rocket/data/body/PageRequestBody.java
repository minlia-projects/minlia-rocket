package com.minlia.rocket.data.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author will
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageRequestBody")
public class PageRequestBody extends PageRequest implements Body {

  @JsonProperty
  @ApiModelProperty(value = "size")
  private Integer size;

  @JsonProperty
  @ApiModelProperty(value = "page")
  private Integer page;

  public PageRequestBody() {
    super(0,10);
  }

  public PageRequestBody(int page, int size) {
    super(page, size);
  }
}
