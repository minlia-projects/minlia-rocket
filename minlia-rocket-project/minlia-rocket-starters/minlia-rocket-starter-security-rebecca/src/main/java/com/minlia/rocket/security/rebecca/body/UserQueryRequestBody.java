package com.minlia.rocket.security.rebecca.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.data.body.QueryRequestBody;
import com.minlia.rocket.enumeration.Gender;
import com.minlia.rocket.enumeration.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "userQueryRequestBody")
public class UserQueryRequestBody implements QueryRequestBody {

  @JsonProperty
  @ApiModelProperty(value = "username",example = "will")
  private String username;

  @JsonProperty
  @ApiModelProperty(value = "mobile",example = "+8613333333333")
  private String mobile;

  @JsonProperty
  @ApiModelProperty(value = "email",example = "will@minlia.com")
  private String email;

  @JsonProperty
  @ApiModelProperty(value = "gender",example = "MALE")
  private Gender gender;

  @JsonProperty
  @ApiModelProperty(value = "type",example = "0")
  private Integer type;

  @JsonProperty
  @ApiModelProperty(value = "status",example = "ENABLED")
  private Status status;

  @JsonProperty
  @ApiModelProperty(value = "startDate",example = "2017-12-12")
  private String startDate;

  @JsonProperty
  @ApiModelProperty(value = "endDate",example = "2018-12-12")
  private String endDate;

}
