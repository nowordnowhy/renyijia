package com.quntum.modules.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.netflix.discovery.util.StringUtil;
import com.quntum.modules.entity.User;
import com.quntum.modules.service.IUserService;
import com.renyija.common.utils.BaseResult;
import com.renyijia.common.utils.ValidatorUtils;
import com.renyijia.common.validator.group.AddGroup;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
            if (list.size() >= 0) {
                return new BaseResult<>(1, "账号已被使用");
            }
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            List<User> list = iUserService.selectList(new EntityWrapper<User>().eq("email", user.getEmail()));
            if (list.size() >= 0) {
                return new BaseResult<>(1, "该邮箱已被使用");
            }
        }
        return new BaseResult<>(iUserService.insert(user));
    }


}
