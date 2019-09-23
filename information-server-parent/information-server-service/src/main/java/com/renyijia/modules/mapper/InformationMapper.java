package com.renyijia.modules.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.renyijia.modules.entity.Information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zhouwenya
 * @since 2019-09-11
 */
@Mapper
@Repository
public interface InformationMapper extends BaseMapper<Information> {

}