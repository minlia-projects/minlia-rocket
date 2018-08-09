package com.minlia.rocket.samples.web.modules.chess.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.data.body.PageRequestBody;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: MerchantPageableQueryRequestBody
 * @copyright: 2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MerchantPageableQueryRequestBody")
public class MerchantPageableQueryRequestBody extends MerchantQueryRequestBody implements
    PageableQueryRequestBody<Merchant> {

  @JsonProperty
  @ApiModelProperty(value = "pageable")
  private PageRequestBody pageable;

  private String password;

}
