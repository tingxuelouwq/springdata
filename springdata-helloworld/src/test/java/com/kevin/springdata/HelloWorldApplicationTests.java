package com.kevin.springdata;

import com.kevin.springdata.entity.Person;
import com.kevin.springdata.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
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

    }
}
