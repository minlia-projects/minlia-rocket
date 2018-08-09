package com.minlia.rocket.samples.web.modules.chess.body;

import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: MerchantQueryRequestBody
 * @copyright:  2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "MerchantQueryRequestBody",subTypes = {MerchantPageableQueryRequestBody.class})
public class MerchantQueryRequestBody implements QueryRequestBody<Merchant> {

  private String name;

  private String content;


}
