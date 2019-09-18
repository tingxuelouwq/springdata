package com.kevin.springdata.repository;

import com.kevin.springdata.dao.PersonDao;
import com.kevin.springdata.dto.*;
import com.kevin.springdata.entity.Address;
import com.kevin.springdata.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface PersonRepository extends JpaRepository<Person, Integer>,
        JpaSpecificationExecutor<Person>, PersonDao {

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

    // @Query注解结合@Modifying注解，可以通过JPQL完成UPDATE和DELETE操作
    // 注意：1. JPQL不支持INSERT；2. UPDATE和DELETE操作需要使用事务
    // 默认情况下，SpringData的每个方法上都有事务，但都是一个只读事务，不能完成修改操作，
    // 如果要改变SpringData提供的事务默认方式，可以在方法上注解@Transactinoal声明
    @Modifying
    @Query("UPDATE Person p SET p.email = :email WHERE id = :id")
    void updatePersonEmail(Integer id, String email);

    @Query("SELECT p FROM Person p WHERE id > :id")
    Page<Person> pageByIdGreaterThan(@Param("id") Integer id, Pageable pageable);

    @Query("SELECT new com.kevin.springdata.dto.PersonAddress(p.lastName, p.address) FROM Person p WHERE p.address = :address")
    Page<PersonAddress> findByAddress(@Param("address") Address address, Pageable pageable);

    @Query("SELECT p.lastName AS lastName, p.address AS address FROM Person p WHERE p.address = :address")
    Page<PersonAddress2> findByAddress2(@Param("address") Address address, Pageable pageable);

    @Query("SELECT p FROM Person p WHERE id IN (:ids)")
    Page<Person> pageByIdIn(@Param("ids") List<Integer> ids, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM t_person p WHERE IF(:email != null, email = :email, 1 = 1) AND IF(:isGroupScrap, email = '', 1 = 1)", nativeQuery = true)
    int findByIf(@Param("email") String email, @Param("isGroupScrap") boolean isGroupScrap);

    @Query(nativeQuery = true, value = "SELECT p.last_name AS lastName, addr.city FROM t_person p INNER JOIN t_address addr ON p.address_id = addr.id AND p.address_id = :#{#address.id}")
    List<PersonQuery> queryForSQL(@Param("address") Address address);

    @Query("SELECT new com.kevin.springdata.dto.PersonQuery2(p.lastName, addr.city) FROM  Person p" +
            " INNER JOIN Address addr ON p.address.id = addr.id" +
            " AND p.address.id = :#{#address.id}")
    List<PersonQuery2> queryForHQL(@Param("address") Address address);

    @Query("SELECT new com.kevin.springdata.dto.PersonQuery2(p.lastName, addr.city) FROM  Person p" +
            " INNER JOIN Address addr ON p.address.id = addr.id" +
            " AND p.address.city LIKE  %:#{#address.city}%")
    List<PersonQuery2> queryForHQL2(@Param("address") Address address);

    @Query("SELECT p FROM Person p WHERE p.id IN :ids")
    List<Person> queryForIn(List<Integer> ids);

    @Query("SELECT p FROM Person p WHERE COALESCE(:ids, NULL) IS NULL OR p.id IN :ids")
    List<Person> queryForIn2(List<Integer> ids);

    @Query("SELECT p FROM Person p WHERE (:#{#address.id} IS NULL OR p.address.id = :#{#address.id})")
    List<Person> queryForNull(Address address);

    @Query("SELECT new com.kevin.springdata.dto.PerAddr(p.lastName, p.address.id, p.address.province, p.address.city) FROM Person p WHERE (:#{#address.id} IS NULL OR p.address.id = :#{#address.id})")
    List<PerAddr> queryForEncapsulate(Address address);
}
