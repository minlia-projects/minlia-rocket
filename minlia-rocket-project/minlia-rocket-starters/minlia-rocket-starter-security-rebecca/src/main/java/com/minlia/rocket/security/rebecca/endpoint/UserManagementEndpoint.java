package com.minlia.rocket.security.rebecca.endpoint;

import com.minlia.rocket.security.rebecca.entity.User;
import com.minlia.rocket.security.rebecca.service.batis.UserRoleBatisService;
import com.minlia.rocket.security.rebecca.service.jpa.RoleJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.UserJpaService;
import com.minlia.rocket.security.rebecca.service.jpa.UserRoleJpaService;
import com.minlia.rocket.stateful.Responses;
import com.minlia.rocket.stateful.body.StatefulBody;
import com.minlia.rocket.stateful.body.WithResultBody;
import com.minlia.rocket.stateful.body.impl.FailureResponseBody;
import com.minlia.rocket.stateful.body.impl.SuccessResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author rdteam
 */
@Slf4j
@RestController
@Api(description = "用户接口")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success", response = StatefulBody.class),
    @ApiResponse(code = 400, message = "Bad Request", response = StatefulBody.class),
    @ApiResponse(code = 401, message = "Not Authorized", response = StatefulBody.class),
    @ApiResponse(code = 403, message = "Forbidden", response = StatefulBody.class),
    @ApiResponse(code = 417, message = "Expectation Failure", response = StatefulBody.class),
})
@RequestMapping("/api/v1/security/rebecca/user-management")

public class UserManagementEndpoint {

  @Autowired
  private UserJpaService userJpaService;

  @Autowired
  private RoleJpaService roleJpaService;

  @Autowired
  private UserRoleBatisService userRoleBatisService;

  @Autowired
  private UserRoleJpaService userRoleJpaService;

  /**
   * 当锁定屏幕后需要提供密码进行再次验证才可以访问系统，否则报出403，并拒绝服务
   * @return
   */
//  @RequestMapping(value = "/unlock", method = RequestMethod.POST)
//  @ApiOperation(value = "解锁验证密码")
////  public Result<Object> unLock(@RequestParam String password) {
//    public ResponseEntity<StatefulBody> unLock(@RequestParam String password)
//    UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//        .getPrincipal();
//    User u = userJpaService.findByUsername(user.getUsername());
////    if (!new BCryptPasswordEncoder().matches(password, u.getPassword())) {
////      return new ResultUtil<Object>().setErrorMsg("Invalid password");
////    }
////    return new ResultUtil<Object>().setData(null);
////    return Responses.
//    return  Responses.ok(null);
//  }


  /**
   * TODO 变更成Body提交的方式，并细分场景，需要前端配合
   * @param u
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @ApiOperation(value = "修改用户自己资料", notes = "用户名密码不会修改 需要通过id获取原用户信息 需要username更新缓存")
  public ResponseEntity<StatefulBody<WithResultBody>> editOwn(@ModelAttribute User u) {

    User old = userJpaService.findOne(u.getId());

    u.setUsername(old.getUsername());
    u.setPassword(old.getPassword());
    u.setGuid(old.getGuid());

    User user = userJpaService.update(u);

//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("修改失败");
//    }
//    return new ResultUtil<Object>().setSuccessMsg("修改成功");

    WithResultBody withResultBody=new WithResultBody<String>();
    withResultBody.setResult("OK");

    return Responses.ok(SuccessResponseBody.builder().payload(withResultBody).build());
  }
//
//  /**
//   * @param u
//   * @param roles
//   * @return
//   */
//  @AddAuditRecord(description = "Update User Detail")
//  @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
//  @ApiOperation(value = "修改资料", notes = "需要通过id获取原用户信息 需要username更新缓存")
////    @CacheEvict(key = "#u.username")
//  public Result<Object> edit(@ModelAttribute User u,
//      @RequestParam(required = false) String[] roles) {
//
//    Boolean enabled = userJpaService.isEnabledById(u.getId());
//    if (enabled == null) {
//      return new ResultUtil<Object>().setErrorMsg("Product id not exist");
//    } else if (enabled) {
//      return new ResultUtil<Object>().setErrorMsg("Product enabled");
//    }
//
//    User old = userJpaService.get(u.getId());
//    //所修改了用户名
//    if (!old.getUsername().equals(u.getUsername())) {
//      //若修改用户名删除原用户名缓存
////      redisTemplate.delete("user::" + old.getUsername());
//      //判断新用户名是否存在
//      if (userJpaService.findByUsername(u.getUsername()) != null) {
//        return new ResultUtil<Object>().setErrorMsg("该用户名已被存在");
//      }
//      //删除缓存
////      redisTemplate.delete("user::" + u.getUsername());
//    }
//
//    u.setPassword(old.getPassword());
//    User user = userJpaService.update(u);
//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("修改失败");
//    }
//    //删除该用户角色
//    userRoleJpaService.deleteByUserId(u.getId());
//    if (roles != null && roles.length > 0) {
//      //新角色
//      for (String roleId : roles) {
//        UserRole ur = new UserRole();
//        ur.setRoleId(roleId);
//        ur.setUserId(u.getId());
//        userRoleJpaService.save(ur);
//      }
//    }
//    //手动删除缓存
////    redisTemplate.delete("userRole::" + u.getId());
//    return new ResultUtil<Object>().setSuccessMsg("修改成功");
//  }
//
//  /**
//   * 线上demo仅允许ADMIN权限改密码
//   */
//  @AddAuditRecord(description = "Update password")
//  @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
//  @ApiOperation(value = "修改密码")
//  public Result<Object> modifyPass(@ApiParam("需用户id获取原用户数据") @RequestParam String id,
//      @ApiParam("password") @RequestParam String password,
//      @ApiParam("新密码") @RequestParam String newPass) {
//
//    User old = userJpaService.get(id);
//
//    if (!new BCryptPasswordEncoder().matches(password, old.getPassword())) {
//      return new ResultUtil<Object>().setErrorMsg("旧密码不正确");
//    }
//
//    //在线DEMO所需
//    if ("test".equals(old.getUsername()) || "test2".equals(old.getUsername())) {
//      return new ResultUtil<Object>().setErrorMsg("演示账号不支持修改密码");
//    }
//
//    String newEncryptPass = new BCryptPasswordEncoder().encode(newPass);
//    old.setPassword(newEncryptPass);
//    User user = userJpaService.update(old);
//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("修改失败");
//    }
//
//    //手动更新缓存
////    redisTemplate.delete("user::" + user.getUsername());
//
//    return new ResultUtil<Object>().setData(user);
//  }
//
//  @AddAuditRecord(description = "Find User By Conditions")
//  @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
//  @ApiOperation(value = "多条件分页获取用户列表")
//  public Result<Page<User>> getByCondition(@ModelAttribute User user,
//      @ModelAttribute SearchVo searchVo,
//      @ModelAttribute PageVo pageVo) {
//
//    Page<User> page = userJpaService.findByCondition(user, searchVo, PageUtil.initPage(pageVo));
//    for (User u : page.getContent()) {
//      List<Role> list = userRoleBatisService.findByUserId(u.getId());
//      u.setRoles(list);
//      u.setPassword(null);
//    }
//    return new ResultUtil<Page<User>>().setData(page);
//  }
//
//  @AddAuditRecord(description = "Create User")
//  @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
//  @ApiOperation(value = "添加用户")
//  public Result<Object> regist(@ModelAttribute User u,
//      @RequestParam(required = false) String[] roles) {
//
//    if (StrUtil.isBlank(u.getUsername()) || StrUtil.isBlank(u.getPassword())) {
//      return new ResultUtil<Object>().setErrorMsg("Required field empty");
//    }
//
//    if (userJpaService.findByUsername(u.getUsername()) != null) {
//      return new ResultUtil<Object>().setErrorMsg("Username has been registered");
//    }
//    //删除缓存
////    redisTemplate.delete("user::" + u.getUsername());
//
//    String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
//    u.setPassword(encryptPass);
//    User user = userJpaService.save(u);
//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("Failed to create user");
//    }
//    if (roles != null && roles.length > 0) {
//      //添加角色
//      for (String roleId : roles) {
//        UserRole ur = new UserRole();
//        ur.setUserId(u.getId());
//        ur.setRoleId(roleId);
//        userRoleJpaService.save(ur);
//      }
//    }
//
//    return new ResultUtil<Object>().setData(user);
//  }
//
//  @AddAuditRecord(description = "Disable User")
//  @RequestMapping(value = "/admin/disable/{userId}", method = RequestMethod.POST)
//  @ApiOperation(value = "后台禁用用户")
//  public Result<Object> disable(@ApiParam("用户唯一id标识") @PathVariable String userId) {
//
//    User user = userJpaService.get(userId);
//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("通过userId获取用户失败");
//    }
//    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//            .getPrincipal();
//    if(user.getUsername().equals(userDetails.getUsername())) {
//      return new ResultUtil<Object>().setErrorMsg("Current login user can not be disabled");
//    }
//    user.setStatus(Status.DISABLED);
//    userJpaService.update(user);
//    //手动更新缓存
////    redisTemplate.delete("user::" + user.getUsername());
//    return new ResultUtil<Object>().setData(null);
//  }
//
//  @RequestMapping(value = "/admin/enable/{userId}", method = RequestMethod.POST)
//  @ApiOperation(value = "后台启用用户")
//  public Result<Object> enable(@ApiParam("用户唯一id标识") @PathVariable String userId) {
//
//    User user = userJpaService.get(userId);
//    if (user == null) {
//      return new ResultUtil<Object>().setErrorMsg("通过userId获取用户失败");
//    }
//    user.setStatus(Status.ENABLED);
//    userJpaService.update(user);
//    //手动更新缓存
////    redisTemplate.delete("user::" + user.getUsername());
//    return new ResultUtil<Object>().setData(null);
//  }
//
//  @AddAuditRecord(description = "Batch Delete User")
//  @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
//  @ApiOperation(value = "批量通过ids删除")
//  public Result<Object> delAllByIds(@RequestParam String[] ids) {
//    for(String id : ids) {
//      UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//              .getPrincipal();
//      User u = userJpaService.findByUsername(user.getUsername());
//      if (u.getId().equals(id)) {
//        return new ResultUtil<Object>().setErrorMsg("Current login user can not be deleted");
//      }
//    }
//    for (String id : ids) {
//      userJpaService.delete(id);
//    }
//    return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
//  }
//
//  @AddAuditRecord(description = "Delete User")
//  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//  @ApiOperation(value = "通过id删除")
//  public Result<Object> delete(@PathVariable String id) {
//    Boolean enabled = userJpaService.isEnabledById(id);
//    if (enabled == null) {
//      return new ResultUtil<Object>().setErrorMsg("Product id not exist");
//    } else if (enabled) {
//      return new ResultUtil<Object>().setErrorMsg("Product enabled");
//    }
//    UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//            .getPrincipal();
//    User u = userJpaService.findByUsername(user.getUsername());
//    if (u.getId().equals(id)) {
//      return new ResultUtil<Object>().setErrorMsg("Current login user can not be deleted");
//    }
//    userJpaService.delete(id);
//    return new ResultUtil<Object>().setSuccessMsg("通过id删除数据成功");
//  }
}