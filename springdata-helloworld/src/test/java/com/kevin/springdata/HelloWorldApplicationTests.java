package com.kevin.springdata;

import com.kevin.springdata.entity.Person;
import com.kevin.springdata.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

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
}
