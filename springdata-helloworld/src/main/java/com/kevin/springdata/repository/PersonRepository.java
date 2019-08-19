package com.kevin.springdata.repository;

import com.kevin.springdata.entity.Person;
import org.springframework.data.repository.Repository;

/**
 * 类名: PersonRepository<br/>
 * 包名：com.kevin.springdata.repository<br/>
 * 作者：kevin<br/>
 * 时间：2019/8/19 20:18<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public interface PersonRepository extends Repository<Person, Integer> {

    /**
     * 根据lastName获取Person
     * @param lastName
     * @return
     */
    Person getByLastName(String lastName);
}
