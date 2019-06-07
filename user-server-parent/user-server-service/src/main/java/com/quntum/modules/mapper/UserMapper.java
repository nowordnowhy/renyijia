package com.quntum.modules.mapper;

import com.quntum.modules.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zhouwenya
 * @since 2019-05-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}