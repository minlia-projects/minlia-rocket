package com.minlia.rocket.samples.web.openapi.queen.v1.body;

import com.minlia.rocket.data.body.AbstractQueryRequestBody;
import lombok.Data;

@Data
public class QueenQueryRequestBody extends AbstractQueryRequestBody {

  private String name;
}
