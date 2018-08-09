package com.minlia.rocket.security.rebecca.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.data.enumeration.DataStatusEnumeration;
import lombok.Data;

@Data
public class UserRoleQueryRequestBody implements QueryRequestBody {

  @JsonProperty
  private String code;
  @JsonProperty
  private String label;
  @JsonProperty
  private DataStatusEnumeration dataStatus;
}
