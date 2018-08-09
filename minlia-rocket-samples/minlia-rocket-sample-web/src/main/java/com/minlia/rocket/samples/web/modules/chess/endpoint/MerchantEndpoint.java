package com.minlia.rocket.samples.web.modules.chess.endpoint;

import com.minlia.rocket.abstraction.endpoint.AbstractEndpoint;
import com.minlia.rocket.abstraction.service.ConditionalService;
import com.minlia.rocket.abstraction.service.PageableConditionalService;
import com.minlia.rocket.data.interfaces.IRawService;
import com.minlia.rocket.samples.web.modules.chess.body.MerchantPageableQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.body.MerchantQueryRequestBody;
import com.minlia.rocket.samples.web.modules.chess.entity.Merchant;
import com.minlia.rocket.samples.web.modules.chess.service.MerchantBatisService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商户管理接口
 * @since: 2018
 * @createTime: 2018-08-03 23:26:54
 * @author: will
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/open/merchant")
@Api(tags = "Merchant")//,produces =MediaType.APPLICATION_JSON_UTF8_VALUE
public class MerchantEndpoint implements
    AbstractEndpoint<Merchant, Long, MerchantQueryRequestBody,MerchantPageableQueryRequestBody>
{

  /**
   * 当模拟数据被创建之前执行
   * @param entityGenerated
   */
  @Override
  public void beforeMocked(Merchant entityGenerated) {
    entityGenerated.setName(RandomStringUtils.randomAlphanumeric(12));
  }

  @Autowired
  private MerchantBatisService batisService;


  @Override
  public IRawService<Merchant, Long> getRawService() {
    return batisService;
  }

  @Override
  public ConditionalService<Merchant, MerchantQueryRequestBody> getConditionalService() {
    return batisService;
  }


  @Override
  public PageableConditionalService<Merchant, MerchantPageableQueryRequestBody> getPageableConditionalService() {
    return batisService;
  }

}
