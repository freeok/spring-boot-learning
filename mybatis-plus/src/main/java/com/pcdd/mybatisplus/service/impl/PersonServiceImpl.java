package com.pcdd.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcdd.mybatisplus.entity.Person;
import com.pcdd.mybatisplus.mapper.PersonMapper;
import com.pcdd.mybatisplus.service.IPersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pcdd
 * @date 2021-12-28
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
