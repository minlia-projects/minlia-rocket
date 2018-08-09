package com.minlia.rocket.data.body;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * abstract query request body
 *
 * @author will created on 8/3/18.
 * @since 2.0.4
 */
//@Data
//@Accessors(chain = true)
@ApiModel(value = "PageableQueryRequestBody", description = "PageableQueryRequestBody")
public interface PageableQueryRequestBody<T> extends QueryRequestBody<T> {

//  @JsonProperty
//  @ApiModelProperty(value = "pageable")
//  private PageRequestBody pageable;


  public PageRequestBody getPageable();

}
