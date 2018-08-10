package com.minlia.rocket.samples.web.modules.chess.v1.body;

import com.minlia.rocket.samples.web.modules.chess.v1.entity.Computer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: QueryRequestBody
 * @copyright: 2018
 * @createTime: 2018-08-09 15:16:21
 * @author: will
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ComputerQueryRequestBody", subTypes = {ComputerPageableQueryRequestBody.class})
public class ComputerQueryRequestBody implements
    com.minlia.rocket.data.body.QueryRequestBody<Computer> {


}
