package com.minlia.rocket.data.generator.endpoint;


import com.minlia.rocket.data.generator.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@RestController
@RequestMapping("/api/v1/data/reversion/engine")
public class MetaDataEndpoint {

  @Autowired
  private TableDao tableDao;

//    @RequestMapping("/list")
//    public ServiceRowsResult list(String id) {
//        ServiceRowsResult result = new ServiceRowsResult(true);
//        result.setPage(tableDao.listTable());
//        return result;
//    }
//    @RequestMapping("/columns")
//    public ServiceRowsResult info(String tableName) {
//        ServiceRowsResult result = new ServiceRowsResult(true);
//        result.setPage(tableDao.listTableColumn(tableName));
//        return result;
//    }
}