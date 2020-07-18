package com.liuzhihang.archetype.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuzhihang.archetype.entity.Demo;
import com.liuzhihang.archetype.mapper.DemoMapper;
import com.liuzhihang.archetype.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author liuzhihang
 * @date 2020/7/15 21:46
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
