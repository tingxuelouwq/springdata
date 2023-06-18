package com.kevin.springdata.dao.impl;

import com.kevin.springdata.dao.PersonDao;
import com.kevin.springdata.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @类名: PersonDaoImpl<br />
 * @包名：com.kevin.springdata.dao.impl<br/>
 * @作者：kevin<br/>
 * @时间：2019/8/25 21:49<br/>
 * @版本：1.0<br/>
 * @描述：<br/>
 */
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class, 11);
        System.out.println("===>" + person);
    }
}
