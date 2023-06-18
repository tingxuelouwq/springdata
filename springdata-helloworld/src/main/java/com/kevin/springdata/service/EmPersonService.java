package com.kevin.springdata.service;

import com.kevin.springdata.entity.Person;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * kevin<br/>
 * 2023/6/18 16:52<br/>
 */
@Service
@Transactional
public class EmPersonService {

    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(Integer id) {
        String sql = "select * from t_person where id = :id";
        Query query = entityManager.createNativeQuery(sql, Person.class);
        query.setParameter("id", id);

        return (Person) query.getSingleResult();
    }

    public List findByIds(List<Integer> ids) {
        String sql = "select * from t_person where id in (:ids)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ids", ids);

        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.TO_LIST);
        return query.getResultList();
    }
}
