package com.kevin.springdata;

import com.kevin.springdata.dto.*;
import com.kevin.springdata.entity.Address;
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
import java.time.LocalDateTime;
import java.util.*;

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
        personService.updatePersonEmail(1, "kevin", "tttx@126.com");
    }

    @Test
    public void testSaveAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            Person person = new Person();
            person.setAddressId(i + 1);
            person.setBirth(LocalDateTime.now());
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
        person.setBirth(LocalDateTime.now());
        person.setEmail("over@126.com");
        person.setLastName("over2");
        person.setId(27);
        Person person1 = personRepository.saveAndFlush(person);
        System.out.println(person == person1);
    }

    // 目标：实现带查询条件(id>5)的分页
    @Test
    public void testJpaSpecificationExecutor() {
        int pageNo = 3;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Specification<Person> spec = new Specification<Person>() {
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
        int pageNo = 1;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order1);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = personRepository.pageByIdGreaterThan(0, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testFindByCondition() {
        Specification<Person> spec = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Path<String> lastName = root.get("lastName");
                Predicate predicate = criteriaBuilder.equal(lastName, "kevin");
                return predicate;
            }
        };
        Optional<Person> optionalPerson = personRepository.findOne(spec);
        if (optionalPerson.isPresent()) {
            System.out.println(optionalPerson.get());
        }
    }

    @Test
    public void testFindBySeveralCondition() {
        Specification<Person> spec = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Path<String> lastName = root.get("lastName");
                Path<Integer> id = root.get("id");
                Predicate p1 = criteriaBuilder.equal(lastName, "kevin");
                Predicate p2 = criteriaBuilder.equal(id, 1);
                Predicate and = criteriaBuilder.and(p1, p2);
                return and;
            }
        };
        Optional<Person> optionalPerson = personRepository.findOne(spec);
        if (optionalPerson.isPresent()) {
            System.out.println(optionalPerson.get());
        }
    }

    @Test
    public void testLike() {
        Specification<Person> spec = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Path<String> lastName = root.get("lastName");
                Predicate like = criteriaBuilder.like(lastName, "%ke%");
                return like;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "lastName");
        List<Person> persons = personRepository.findAll(spec, sort);
        persons.forEach(System.out::println);
    }

    @Test
    public void testFindByAddress() {
        Address address = new Address();
        address.setId(1);
        int pageNo = 0;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PersonAddress> page = personRepository.findByAddress(address, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
        List<PersonAddress> list = page.getContent();
        list.forEach(personAddress -> {
            System.out.println(personAddress.getLastName());
            System.out.println(personAddress.getAddress());
        });
    }

    @Test
    public void testFindByAddress2() {
        Address address = new Address();
        address.setId(1);
        int pageNo = 0;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PersonAddress2> page = personRepository.findByAddress2(address, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
        List<PersonAddress2> list = page.getContent();
        list.forEach(personAddress2 -> {
            System.out.println(personAddress2.getLastName());
            System.out.println(personAddress2.getAddress());
        });
    }

    @Test
    public void testPageByIdIn() {
        List<Integer> ids = Arrays.asList(1, 2);
        int pageNo = 0;
        int pageSize = 5;
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = personRepository.pageByIdIn(ids, pageable);
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前第几页: " + page.getNumber());
        System.out.println("当前页面的List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testFindByIf() {
        String email = null;
        boolean isGroupScrap = true;
        System.out.println(personRepository.findByIf(email, isGroupScrap));
    }

    @Test
    public void testQueryForSQL() {
        Address address = new Address();
        address.setId(1);
        personRepository.queryForSQL(address);
    }

    @Test
    public void testQueryForHQL() {
        Address address = new Address();
        address.setId(1);
        personRepository.queryForHQL(address);
    }

    @Test
    public void testQueryForHQL2() {
        Address address = new Address();
        address.setCity("Bei");
        List<PersonQuery2> list = personRepository.queryForHQL2(address);
        list.forEach(System.out::println);
    }

    @Test
    public void testQueryForHQL3() {
        Address address = new Address();
        address.setId(2);
        List<PersonQuery> list = personRepository.queryForHQL3(address);
        System.out.println(list.size());
    }

    @Test
    public void testQueryForIn() {
        List<Person> personList = personRepository.queryForIn(Arrays.asList(1, 2));
        personList.forEach(System.out::println);
    }

    @Test
    public void testQueryForIn2() {
        List<Person> personList = personRepository.queryForIn2(Arrays.asList(1, 2));
        personList.forEach(System.out::println);
    }

    @Test
    public void testQueryForNull() {
        Address address = new Address();
        address.setId(null);
        List<Person> personList = personRepository.queryForNull(address);
        personList.forEach(System.out::println);
    }

    @Test
    public void testQueryForEncapsulate() {
        Address address = new Address();
        address.setId(1);
        List<PerAddr> list = personRepository.queryForEncapsulate(address);
        list.forEach(System.out::println);
    }

    @Test
    public void testQueryForEncapsulate2() {
        Address address = new Address();
        address.setId(3);
        List<PerAddr> list = personRepository.queryForEncapsulate2(address);
        list.forEach(System.out::println);
    }

    @Test
    public void testQueryForCaseWhen() {
        Person person = new Person();
        person.setLastName("tom");
        person.setAddressId(1);
        List<Person> list = personRepository.queryForCaseWhen(person);
        list.forEach(System.out::println);
    }

    @Test
    public void testDynamicInsert() {
        Person person = personRepository.findById(17).get();
        person.setLastName("Tom");
        personRepository.save(person);
    }

    @Test
    public void testFindByAddress3ByNative() {
        Address address = new Address();
        address.setId(3);
        List<PersonAddress3> list = personRepository.findByAddress3ByNative(address);
        for (PersonAddress3 personAddress3 : list) {
            System.out.println(personAddress3.getLastName() + ", " + personAddress3.getProvince() + ", " + personAddress3.getCity());
        }
    }

    @Test
    public void testQueryForDateTime() {
        PersonQuery3 personQuery3 = personRepository.queryForDateTime(1);
        System.out.println(personQuery3);
    }
}