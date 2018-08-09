package com.minlia.rocket.samples.web.modules.chess.v1.body;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import com.minlia.rocket.data.body.PageRequestBody;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: PageableQueryRequestBody
 * @copyright:  2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ComputerPageableQueryRequestBody")
public class ComputerPageableQueryRequestBody extends ComputerQueryRequestBody implements
    PageableQueryRequestBody<Computer> {

  @JsonProperty
  @ApiModelProperty(value = "pageable")
  private PageRequestBody pageable;

}
