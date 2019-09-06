package com.renyijia.modules.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.renyija.common.utils.BaseResult;
import com.renyija.common.utils.RedisUtils;
import com.renyijia.common.utils.ValidatorUtils;
import com.renyijia.validator.group.AddGroup;
import com.renyijia.model.LogUser;
import com.renyijia.modules.entity.User;
import com.renyijia.modules.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.renyija.common.utils.BaseResult.*;
import static com.renyija.common.utils.Token.*;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-29
 * @email : zhou_wenya@163.com
 */
@Api("用户登录注册")
@RestController
@RequestMapping("userLogin")
public class LoginController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserService iUserService;

    @ApiOperation("注册")
    @PostMapping("register")
    public BaseResult<Boolean> register(@RequestBody User user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        if (!StringUtils.isEmpty(user.getUserName())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("user_name", user.getUserName()));
            if (list.size() > 0) {
                return getMessage("账号已被使用");
            }
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("email", user.getEmail()));
            if (list.size() > 0) {
                return getMessage("该邮箱已被使用");
            }
        }
        if (!StringUtils.isEmpty(user.getMobile())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("mobile", user.getMobile()));
            if (list.size() > 0) {
                return getMessage("该手机号已被使用");
            }
        }
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        return getBaseResult(iUserService.insert(user));
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public BaseResult<User> login(@RequestBody LogUser logUser) {
        if (StringUtils.isEmpty(logUser.getUserName()) || StringUtils.isEmpty(logUser.getPassword())) {
            return getMessage("账号或密码为空");
        }
        User user = iUserService.selectOne(
                new EntityWrapper<User>().
                        eq("user_name", logUser.getUserName())
                        .or().eq("email", logUser.getUserName())
                        .or().eq("mobile", logUser.getUserName()));
        if (null == user) {
            return getMessage("该账号不存在");
        }
        logUser.setPassword(new Sha256Hash(logUser.getPassword(), user.getSalt()).toHex());
        if (!logUser.getPassword().equals(user.getPassword())) {
            return getMessage("密码错误");
        }
        user.setSalt(null);
        user.setPassword(null);
        String jwt = createJWT(user.getId().toString(), user.getUserName());
        redisUtils.set(jwt, user);
        return getBaseResult(user);

    }

    @ApiOperation("登出")
    @PostMapping("logout")
    public BaseResult<Boolean> logout(@RequestHeader(required = true) String token) {
        Boolean b = tokenCheck(parseJWT(token));
        if (!b) {
            return tokenError();
        }
        redisUtils.delete(token);
        return getBaseResult(true);

    }




}
