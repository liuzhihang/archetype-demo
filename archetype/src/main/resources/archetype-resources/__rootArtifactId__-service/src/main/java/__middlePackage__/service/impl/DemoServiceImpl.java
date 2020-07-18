#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${middlePackage}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package}.${middlePackage}.entity.Demo;
import ${package}.${middlePackage}.mapper.DemoMapper;
import ${package}.${middlePackage}.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author liuzhihang
 * @date 2020/7/15 21:46
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
