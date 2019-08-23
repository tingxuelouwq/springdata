package com.kevin.springdata.service;

import com.kevin.springdata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 类名: PersonService<br/>
 * 包名：com.kevin.springdata.service<br/>
 * 作者：kevin<br/>
 * 时间：2019/8/23 16:37<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void updatePersonEmail(Integer id, String email) {
        personRepository.updatePersonEmail(id, email);
    }
}
