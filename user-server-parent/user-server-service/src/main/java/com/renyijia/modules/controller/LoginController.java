package com.renyijia.modules.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.renyijia.model.LogUser;
import com.renyijia.modules.entity.User;
import com.renyijia.modules.service.IUserService;
import com.renyija.common.utils.BaseResult;
import com.renyijia.common.utils.ValidatorUtils;
import com.renyijia.common.validator.group.AddGroup;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-29
 * @email : zhou_wenya@163.com
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation("注册")
    @PostMapping("register")
    public BaseResult<Boolean> register(@RequestBody User user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        if (!StringUtils.isEmpty(user.getUserName())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("user_name", user.getUserName()));
            if (list.size() > 0) {
                return new BaseResult<>(1, "账号已被使用");
            }
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("email", user.getEmail()));
            if (list.size() > 0) {
                return new BaseResult<>(1, "该邮箱已被使用");
            }
        }
        if (!StringUtils.isEmpty(user.getMobile())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("mobile", user.getMobile()));
            if (list.size() > 0) {
                return new BaseResult<>(1, "该手机号已被使用");
            }
        }
        return new BaseResult<>(iUserService.insert(user));
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public BaseResult<String> login(@RequestBody LogUser logUser) {
        if (!StringUtils.isEmpty(logUser.getUserName())) {
            User user = iUserService.selectOne(
                    new EntityWrapper<User>().
                            eq("user_name", logUser.getUserName())
                            .or().eq("email", logUser.getUserName())
                            .or().eq("mobile", logUser.getUserName()));
            if (null == user) {
                return new BaseResult<>(1, "该账号不存在");
            }
        }

        if (logUser.getPassword().equals(logUser)) {

        }
        return new BaseResult<>("");
    }


}
