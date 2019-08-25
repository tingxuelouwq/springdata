package com.kevin.springdata;

import com.kevin.springdata.entity.Person;
import com.kevin.springdata.repository.PersonRepository;
import com.kevin.springdata.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    public void contextLoads() {
        System.out.println("======>" + dataSource);
    }

    @Test
    public void testGetByLastName() {
        Person person = personRepository.getByLastName("kevin");
        System.out.println(person);
    }

    @Test
    public void testGetByLastNameStartingWithAndIdLessThan() {
        List<Person> personList = personRepository
                .getByLastNameStartingWithAndIdLessThan("ke", 10);
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetByLastNameEndingWithAndIdLessThan() {
        List<Person> personList = personRepository
                .getByLastNameEndingWithAndIdLessThan("vin", 10);
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetByEmailInAndBirthLessThan() {
        List<Person> personList = personRepository.getByEmailInAndBirthLessThan(
                Arrays.asList("admin@126.com", "test@126.com"), new Date());
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetByAddressIdGreaterThan() {
        List<Person> personList = personRepository.getByAddressIdGreaterThan(1);
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetByAddress_IdGreaterThan() {
        List<Person> personList = personRepository.getByAddress_IdGreaterThan(1);
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetMaxIdPerson() {
        Person person = personRepository.getMaxIdPerson();
        System.out.println(person);
    }

    @Test
    public void testQueryParamsByPlaceholder() {
        List<Person> personList = personRepository
                .queryParamsByPlaceholder("kevin", "kevin@126.com");
        personList.forEach(System.out::println);
    }

    @Test
    public void testQueryParamsByNamedParameter() {
        List<Person> personList = personRepository
                .queryParamsByNamedParameter("kevin", "kevin@126.com");
        personList.forEach(System.out::println);
    }

    @Test
    public void queryParamsLike() {
        List<Person> personList = personRepository
                .queryParamsLike("ke%", "%@126.com");
        personList.forEach(System.out::println);
    }

    @Test
    public void queryParamsLike2() {
        List<Person> personList = personRepository
                .queryParamsLike2("ke", "@126.com");
        personList.forEach(System.out::println);
    }

    @Test
    public void queryParamsLike3() {
        List<Person> personList = personRepository
                .queryParamsLike3("ke", "@126.com");
        personList.forEach(System.out::println);
    }

    @Test
    public void testGetTotalCount() {
        System.out.println(personRepository.getTotalCount());
    }

    @Test
    public void testUpdatePersonEmail() {
        personService.updatePersonEmail(1, "tttx@126.com");
    }

    @Test
    public void testSaveAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            Person person = new Person();
            person.setAddressId(i + 1);
            person.setBirth(new Date());
            person.setEmail((char) i + "" + (char) i + "@126.com");
            person.setLastName((char) i + "" + (char) i);
            persons.add(person);
        }
        personService.saveAll(persons);
    }

    @Test
    public void testPagingAndSorting() {
        int pageNo = 3;
        int pageSize = 5;
        // Pageable接口通常使用其PageRequest实现类，其中封装了分页信息
        // Sort封装了排序信息
        // Order是具体针对于某一个属性进行升序还是降序
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = personRepository.findAll(pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testSaveAndFlush() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("over@126.com");
        person.setLastName("over2");
        person.setId(27);
        Person person1 = personRepository.saveAndFlush(person);
        System.out.println(person == person1);
    }

    /**
     * 目标：实现带查询条件(id>5)的分页
     * 实现：调用JpaSpecificationExecutor的Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
     * Specification: 封装了JPA Criteria的查询条件
     * Pageable: 封装了请求分页的信息
     */
    @Test
    public void testJpaSpecificationExecutor() {
        int pageNo = 3;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        // 通常使用Specification的匿名内部类
        Specification<Person> spec = new Specification<Person>() {
            /**
             *
             * @param root  代表查询的实体类
             * @param query 可以从中得到Root对象，即告知JPA Criteria要查询哪一个实体类，
             *              还可以来添加查询条件，还可以结合EntityManager对象得到最终查
             *              询的TypedQuery对象
             * @param criteriaBuilder   用于创建Criteria相关对象的工厂，可以从中获取
             *                          到Predicate对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Person> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Path<Integer> path = root.get("id");
                Predicate predicate = criteriaBuilder.gt(path, 5);
                return predicate;
            }
        };
        Page<Person> page = personRepository.findAll(spec, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testCustomRepository() {
        personRepository.test();
    }

    @Test
    public void testPageByIdGreaterThan() {
        int pageNo = 3;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = personRepository.pageByIdGreaterThan(5, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }
}
