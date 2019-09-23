package com.renyijia.modules.controller;


import com.renyija.common.utils.BaseResult;
import com.renyijia.common.utils.RedisUtils;
import com.renyijia.model.LogUser;
import com.renyijia.modules.entity.User;
import com.renyijia.modules.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.renyija.common.utils.BaseResult.getBaseResult;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhouwenya
 * @since 2019-05-29
 */
@RestController
@RequestMapping("/modules/user")
public class UserController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserService iUserService;

    @ApiOperation("/view")
    @GetMapping("/view")
    public BaseResult<User> view(@RequestHeader(required = true) String token) {
        User user = redisUtils.get(token, User.class);
        return getBaseResult(user);
    }

    @ApiOperation("/updateUserInfo")
    @GetMapping("/updateUserInfo")
    public BaseResult<String> updateUserInfo(@RequestBody User user, @RequestHeader(required = true) String token) {
        user.setUserName(null);
        user.setPassword(null);
        user.setSalt(null);
        user.setCreateTime(null);
        user.setUpdateTime(new Date());
        boolean b = iUserService.updateById(user);
        if (!b) {
            return getBaseResult("更新失败");
        }
        redisUtils.set(token, user);
        return getBaseResult(0, "更新成功");
    }

    @ApiOperation("/updatePassword")
    @GetMapping("/updatePassword")
    public BaseResult<String> updatePassword(@RequestBody LogUser logUser, @RequestHeader(required = true) String token) {
        User currentUser = redisUtils.get(token, User.class);
        if (!currentUser.getId().equals(logUser.getUserId())) {
            return getBaseResult("无法修改非当前登录用户信息");
        }
        User updateUser = iUserService.selectById(currentUser.getId());
        if (null == updateUser) {
            return getBaseResult("用户不存在");
        }
        String newPassword = new Sha256Hash(logUser.getPassword(), updateUser.getSalt()).toHex();
        if (!newPassword.equals(updateUser.getPassword())) {
            return getBaseResult("当前密码错误");
        }
        updateUser.setPassword(newPassword);
        boolean b = iUserService.updateById(updateUser);
        if (!b) {
            return getBaseResult("更新密码失败");
        }
        return getBaseResult(0, "修改密码成功");
    }

}
