package com.minlia.rocket.data.generator.body;

import com.minlia.rocket.stateful.body.Body;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(value = "CodeGenerationRequestBody",description = "代码生成请求体")
public class CodeGenerationRequestBody implements Body {


  @ApiModelProperty(value = "author", notes = "目标代码的作者",example = "developer")
  private String author;

  @ApiModelProperty(value = "version", notes = "目标代码版本号" ,example = "1.0.0")
  private String version;

  @ApiModelProperty(value = "projectBasePath", notes = "目标项目根目录",example = "./target/code_generated")
  private String projectBasePath;

  @ApiModelProperty(value = "entitiesInclude", notes = "生成时包含的表名" )
  private List<String> entitiesInclude;

//  @ApiModelProperty(value = "entitiesExclude", notes = "生成时排除的表名" )
//  private List<String> entitiesExclude;

  @ApiModelProperty(value = "parentPackageName", notes = "包名",example = "com.companyname.systemname")
  private String parentPackageName;

  @ApiModelProperty(value = "moduleName", notes = "连接在包名后面的模块名称",example = "modulename")
  private String moduleName;


}
