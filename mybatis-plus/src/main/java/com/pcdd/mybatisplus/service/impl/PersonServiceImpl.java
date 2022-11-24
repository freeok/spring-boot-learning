package com.pcdd.mybatisplus.service.impl;

import com.pcdd.mybatisplus.entity.Person;
import com.pcdd.mybatisplus.mapper.PersonMapper;
import com.pcdd.mybatisplus.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author du_pch
 * @since 2021-12-28
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
