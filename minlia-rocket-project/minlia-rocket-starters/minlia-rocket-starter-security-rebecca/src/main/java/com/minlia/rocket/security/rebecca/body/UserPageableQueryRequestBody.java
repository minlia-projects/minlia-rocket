package com.minlia.rocket.security.rebecca.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.data.body.PageRequestBody;
import com.minlia.rocket.data.body.PageableQueryRequestBody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserPageableQueryRequestBody extends UserQueryRequestBody implements
    PageableQueryRequestBody {

  @JsonProperty
  @ApiModelProperty(value = "pageable")
  private PageRequestBody pageable;

  private String password;

}
