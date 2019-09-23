package com.renyijia.modules.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.renyijia.modules.entity.Information;
import com.renyijia.modules.mapper.InformationMapper;
import com.renyijia.modules.service.IInformationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouwenya
 * @since 2019-09-11
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {
	
}
