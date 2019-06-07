package com.quntum.modules.service.impl;

import com.quntum.modules.entity.User;
import com.quntum.modules.mapper.UserMapper;
import com.quntum.modules.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouwenya
 * @since 2019-05-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
}
