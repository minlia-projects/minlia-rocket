package com.minlia.rocket.data.body;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.rocket.stateful.body.ApiRequestBody;
import com.minlia.rocket.stateful.body.Body;
import com.minlia.rocket.stateful.body.StatefulBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * abstract query request body
 *
 * @author will created on 7/3/18.
 * @since 2.0.3
 */
@Accessors(chain = true)
@ApiModel(value = "QueryRequestBody", description = "QueryRequestBody",subTypes = {PageableQueryRequestBody.class})
public interface QueryRequestBody<T> extends Body {

}
