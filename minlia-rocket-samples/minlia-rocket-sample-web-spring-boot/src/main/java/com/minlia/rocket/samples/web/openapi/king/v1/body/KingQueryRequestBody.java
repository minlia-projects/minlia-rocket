package com.minlia.rocket.samples.web.openapi.king.v1.body;

import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import lombok.Data;

@Data
public class KingQueryRequestBody extends AbstractQueryRequestBody {

  private String name;

}
