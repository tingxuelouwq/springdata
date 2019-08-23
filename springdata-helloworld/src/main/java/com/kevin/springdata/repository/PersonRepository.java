package com.kevin.springdata.repository;

import com.kevin.springdata.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

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

    // 根据lastName获取Person
    Person getByLastName(String lastName);

    // WHERE lastName LIKE ?% AND id < ?
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    // WHERE lastName like %? AND id < ?
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

    // WHERE email IN (?, ?, ?) AND birth < ?
    List<Person> getByEmailInAndBirthLessThan(List<String> emails, Date birth);

    // WHERE addressId > ?
    List<Person> getByAddressIdGreaterThan(Integer id);

    // WEHRE address.id > ?
    List<Person> getByAddress_IdGreaterThan(Integer id);

    // 查询id值最大的Person
    // 使用@Query注解可以自定义JPQL语句以实现更灵活的查询
    @Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
    Person getMaxIdPerson();

    // 为@Query注解传递参数的方式1：使用占位符
    @Query("SELECT p FROM Person p WHERE p.lastName = ?1 AND p.email = ?2")
    List<Person> queryParamsByPlaceholder(String lastName, String email);

    // 为@Query注解传递参数的方式2：使用命名参数
    @Query("SELECT p FROM Person p WHERE p.lastName = :lastName AND p.email = :email")
    List<Person> queryParamsByNamedParameter(@Param("lastName") String lastName,
                                             @Param("email") String email);

    @Query("SELECT p FROM Person p WHERE p.lastName LIKE ?1 AND p.email LIKE ?2")
    List<Person> queryParamsLike(String lastName, String email);

    // SpringData允许在占位符上添加%
    @Query("SELECT p FROM Person p WHERE p.lastName LIKE ?1% AND p.email LIKE %?2")
    List<Person> queryParamsLike2(String lastName, String email);

    // SpringData允许在命名参数上添加%
    @Query("SELECT p FROM Person p WHERE p.lastName LIKE :lastName% AND p.email LIKE %:email")
    List<Person> queryParamsLike3(@Param("lastName") String lastName,
                                  @Param("email") String email);

    // 设置nativeQuery=true即可使用原生SQL查询
    @Query(value = "SELECT count(1) FROM t_person", nativeQuery = true)
    long getTotalCount();
}
