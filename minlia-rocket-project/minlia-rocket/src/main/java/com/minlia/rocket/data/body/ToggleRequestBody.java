package com.minlia.rocket.data.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

@Data
@ApiModel(value = "Toggle Request Body")
public class ToggleRequestBody<ID extends Serializable> {

  //  @JsonProperty
//  private String property;
  @JsonProperty
  private String value;
  @JsonProperty
  private ID id;

}
