package com.kevin.springdata.repository;

import com.kevin.springdata.entity.Person;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

/**
 * 类名: PersonRepository<br/>
 * 包名：com.kevin.springdata.repository<br/>
 * 作者：kevin<br/>
 * 时间：2019/8/19 20:18<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 * 1. Repository是一个空接口，即标记接口<br/>
 * 2. 如果一个接口继承了Repository，则该接口会被IOC容器识别为一个Repository Bean<br/>
 * 3. 你也可以通过@RepositoryDefinition注解来替代继承Repository接口<br/>
 */
public interface PersonRepository extends Repository<Person, Integer> {

    /**
     * 根据lastName获取Person
     * @param lastName
     * @return
     */
    Person getByLastName(String lastName);

    // WHERE lastName LIKE ?% AND id < ?
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    // WHERE lastName like %? AND id < ?
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

    // WHERE email IN (?, ?, ?) AND birth < ?
    List<Person> getByEmailInAndBirthLessThan(List<String> emails, Date birth);

    // WHERE a.id > ?
    List<Person> getByAddressIdGreaterThan(Integer id);

    List<Person> getByAddress_IdGreaterThan(Integer id);


}
