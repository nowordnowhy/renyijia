package com.renyijia.modules.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.renyijia.modules.entity.Information;
import com.renyijia.modules.mapper.InformationMapper;
import com.renyijia.modules.service.IInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouwenya
 * @since 2019-09-11
 */
@RestController
@RequestMapping("/modules/information")
public class InformationController {
    @Autowired
    private IInformationService iInformationService;
    @Autowired
    private InformationMapper informationMapper;

    @GetMapping("get")
    public Object get(){
        EntityWrapper<Information> entityWrapper=new EntityWrapper<Information>();
        entityWrapper.eq("author","百次").or().isNull("author");

        return iInformationService.selectList(entityWrapper);
    }

    @GetMapping("get2")
    public Object get2(){
        EntityWrapper<Information> entityWrapper=new EntityWrapper<Information>();
        entityWrapper.eq("author","百次").or().isNull("author")
                .andNew().eq("title","猪肉").or().isNull("title");

        return informationMapper.selectList(entityWrapper);
    }
	
}
