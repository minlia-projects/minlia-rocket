package com.minlia.rocket.data.generator.service;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.minlia.rocket.data.generator.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * @author will
 */
@Slf4j
public class CodeGenerationServiceImpl implements CodeGenerationService {

  @Autowired
  private DataSourceProperties dataSourceProperties;

  @Override
  public void generation() {
    log.debug("Starting code generation...");

    AutoGenerator mpg = new AutoGenerator();
    final int year = Calendar.getInstance().get(Calendar.YEAR);
    final String createTime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
    final String version = "1.0";

    final String projectBase = "./target/code_generated";
    String author = "will";

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(projectBase + "/src/main/java//");
    gc.setFileOverride(true);
    gc.setActiveRecord(false);
    gc.setEnableCache(false);// XML 二级缓存
    gc.setBaseResultMap(true);// XML ResultMap
    gc.setBaseColumnList(true);// XML columList
    gc.setAuthor(author);
    gc.setOpen(true);
    gc.setServiceName("%sBatisService");
    gc.setServiceImplName("%sBatisServiceImpl");
    gc.setMapperName("%sDao");
    gc.setXmlName("%sDao");
    gc.setControllerName("%sEndpoint");

    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    //gc.setMapperName("%sDao");
    // gc.setXmlName("%sDao");
    // gc.setServiceName("MP%sService");
    // gc.setServiceImplName("%sServiceDiy");
    // gc.setControllerName("%sAction");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    mpg.setDataSource(dataSourceConfig());

    // 自定义需要填充的字段
    List<TableFill> tableFillList = new ArrayList<>();
    tableFillList.add(new TableFill("created_date", FieldFill.INSERT));
    tableFillList.add(new TableFill("last_modified_date", FieldFill.INSERT_UPDATE));

    final String apiPrefix = "api";
    final String resPrefix = "endpoint";
    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
     strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
    strategy.setTablePrefix(new String[]{"t_"});// 此处可以修改为您的表前缀
    strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy.setInclude(new String[]{"t_sys_king","t_web_article_category","t_web_banner","t_web_config","t_web_friend"}); // 需要生成的表
//    strategy.setInclude(new String[]{"King","Queen","Rook","Bishop","Knight","Pawn"}); // 需要生成的表
    strategy.setInclude(new String[]{"Computer"}); // 需要生成的表

    strategy.setTableFillList(tableFillList);
    // strategy.setExclude(new String[]{"test"}); // 排除生成的表
    // 自定义实体父类
    strategy.setSuperEntityClass("com.minlia.rocket.data.entity.AbstractEntity");
    // 自定义实体，公共字段
    strategy.setSuperEntityColumns(
        new String[]{  "created_by", "created_date", "last_modified_by",
            "last_modified_date", "data_status"});
    // 自定义 mapper 父类
    //strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
    // 自定义 service 父类
    strategy.setSuperServiceClass(
        "com.minlia.rocket.data.batis.abstraction.service.AbstractBatisService");
    // 自定义 service 实现类父类
    strategy.setSuperServiceImplClass(
        "com.minlia.rocket.data.batis.abstraction.service.AbstractBatisServiceImpl");
    // 自定义 controller 父类
    strategy.setSuperControllerClass("com.minlia.rocket.abstraction.endpoint.AbstractEndpoint");
    // 【实体】是否生成字段常量（默认 false）
    // public static final String ID = "test_id";
    // strategy.setEntityColumnConstant(true);
    // 【实体】是否为构建者模型（默认 false）
    // public OldUser setName(String name) {this.name = name; return this;}
    // strategy.setEntityBuliderModel(true);
    mpg.setStrategy(strategy);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent("com.minlia.rocket.samples.web.modules.chess");
    pc.setModuleName("v1");
    pc.setController("endpoint");
    pc.setMapper("dao");
    pc.setXml("dao");
    pc.setService("service" );
    pc.setServiceImpl("service" );
    pc.setEntity("entity" );
    mpg.setPackageInfo(pc);


    String parentPackageName=pc.getParent();//+(StringUtils.isEmpty(pc.getModuleName())?"":"."+pc.getModuleName());
    // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", year);
        map.put("copyright", "");
        map.put("repositoryPackage", parentPackageName+".repository");
        map.put("bodyPackage", parentPackageName+".body");
        map.put("createTime", createTime);
        map.put("version", version);
        map.put("apiPrefix", apiPrefix);
        map.put("resPrefix", resPrefix);
        this.setMap(map);
      }
    };



    // 自定义 vue文件 生成
    List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

    focList.add(new FileOutConfig("/templates/rocket/vue.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        String vueFileName = tableInfo.getEntityName().substring(0, 1).toLowerCase() +
            tableInfo.getEntityName().substring(1, tableInfo.getEntityName().length()) + ".vue";
        return projectBase + "/pages/" + resPrefix + "/" + vueFileName;
      }
    });


    //自定义Repository生成
    focList.add(new FileOutConfig("/templates/rocket/repository.java.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"/repository/" + tableInfo.getEntityName()
            + "Repository.java";
      }
    });



    //自定义JpaService生成
    focList.add(new FileOutConfig("/templates/rocket/jpaService.java.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"/service/" + tableInfo.getEntityName()
            + "JpaService.java";
      }
    });


    //自定义JpaService实现类生成
    focList.add(new FileOutConfig("/templates/rocket/jpaServiceImpl.java.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"/service/" + tableInfo.getEntityName()
            + "JpaServiceImpl.java";
      }
    });







    //自定义QueryRequestBody生成

    focList.add(new FileOutConfig("/templates/rocket/queryRequestBody.java.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"/body/" + tableInfo.getEntityName()
            + "QueryRequestBody.java";
      }
    });


    //自定义PageableQueryRequestBody生成

    focList.add(new FileOutConfig("/templates/rocket/pageableQueryRequestBody.java.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"/body/" + tableInfo.getEntityName()
            + "PageableQueryRequestBody.java";
      }
    });


//    // 调整 xml 生成目录演示
    focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return projectBase + "//src//main//resources//dao//" + tableInfo.getEntityName()
            + "Dao.xml";
      }
    });

//
//    focList.add(new FileOutConfig("/templates/entity.java.vm") {
//      @Override
//      public String outputFile(TableInfo tableInfo) {
//        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"//entity//" + tableInfo.getEntityName()
//            + ".Java";
//      }
//    });
//
//    focList.add(new FileOutConfig("/templates/service.java.vm") {
//      @Override
//      public String outputFile(TableInfo tableInfo) {
//        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"//service//" + tableInfo.getEntityName()
//            + "Service.Java";
//      }
//    });
//    focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
//      @Override
//      public String outputFile(TableInfo tableInfo) {
//        return projectBase + "//src//main//java//"+pc.getParent().replaceAll("\\.","/")+"//service//" + tableInfo.getEntityName()
//            + "ServiceImpl.Java";
//      }
//    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 关闭默认 xml 生成，调整生成 至 根目录
    TemplateConfig tc = new TemplateConfig();

    tc.setController("/templates/rocket/controller.java.vm");
    tc.setEntity("/templates/rocket/entity.java.vm");
    tc.setMapper("/templates/rocket/mapper.java.vm");
    //手动配置
    tc.setXml(null);
//    tc.setXml("/templates/rocket/mapper.xml.vm");
//    tc.setService(null);
//    tc.setServiceImpl(null);
    tc.setService("/templates/rocket/service.java.vm");
    tc.setServiceImpl("/templates/rocket/serviceImpl.java.vm");

    mpg.setTemplate(tc);

    // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
    // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
    // TemplateConfig tc = new TemplateConfig();
    // tc.setController("...");
    // tc.setEntity("...");
    // tc.setMapper("...");
    // tc.setXml("...");
    // tc.setService("...");
    // tc.setServiceImpl("...");
    //// 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
    // mpg.setTemplate(tc);

    // 执行生成
    mpg.execute();

    // 打印注入设置【可无】
    System.err.println(mpg.getCfg().getMap().get("abc"));

    log.debug("Finishing code generation...");

  }


  private DataSourceConfig dataSourceConfig() {
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.valueOf(dataSourceProperties.getPlatform().toUpperCase()));
    switch (dataSourceConfig.getDbType()) {
      case MYSQL:
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
          // 自定义数据库表字段类型转换【可选】
          @Override
          public DbColumnType processTypeConvert(String fieldType) {
            System.out.println("转换类型：" + fieldType);
            // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
            return super.processTypeConvert(fieldType);
          }
        });
        break;
      case ORACLE:
        dataSourceConfig.setTypeConvert(new OracleTypeConvert() {
          // 自定义数据库表字段类型转换【可选】
          @Override
          public DbColumnType processTypeConvert(String fieldType) {
            System.out.println("转换类型：" + fieldType);
            // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
            return super.processTypeConvert(fieldType);
          }
        });
        break;
      case SQL_SERVER:

        dataSourceConfig.setTypeConvert(new SqlServerTypeConvert() {
          // 自定义数据库表字段类型转换【可选】
          @Override
          public DbColumnType processTypeConvert(String fieldType) {
            System.out.println("转换类型：" + fieldType);
            // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
            return super.processTypeConvert(fieldType);
          }
        });
        break;
      case POSTGRE_SQL:

        dataSourceConfig.setTypeConvert(new PostgreSqlTypeConvert() {
          // 自定义数据库表字段类型转换【可选】
          @Override
          public DbColumnType processTypeConvert(String fieldType) {
            System.out.println("转换类型：" + fieldType);
            // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
            return super.processTypeConvert(fieldType);
          }
        });
        break;
      default:
        break;
    }

    dataSourceConfig.setDriverName(dataSourceProperties.getDriverClassName());
    dataSourceConfig.setUsername(dataSourceProperties.getUsername());
    dataSourceConfig.setPassword(dataSourceProperties.getPassword());
    dataSourceConfig.setUrl(dataSourceProperties.getUrl());
    return dataSourceConfig;

  }

}
