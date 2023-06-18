package com.kevin.springdata.service;

import com.kevin.springdata.dto.PersonDTO;
import com.kevin.springdata.entity.Person;
import com.kevin.springdata.util.NativeQueryHelper;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private NativeQueryHelper nativeQueryHelper;

    public Person findById(Integer id) {
        String sql = "select * from t_person where id = :id";
        Query query = entityManager.createNativeQuery(sql, Person.class);
        query.setParameter("id", id);

        return (Person) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByIds(List<Integer> ids) {
        String sql = "select * from t_person where id in (:ids)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ids", ids);

        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.TO_LIST);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Page<Person> page(int pageIndex, int pageSize) {
        String sql = "select * from t_person";
        Query query = entityManager.createNativeQuery(sql);
        query.setFirstResult(pageIndex * pageSize);
        query.setMaxResults(pageSize);

        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.TO_LIST);
        List<Person> list = query.getResultList();

        String countSql = "select count(*) from t_person";
        Query countQuery = entityManager.createNativeQuery(countSql);

        Object count = countQuery.getSingleResult();
        int total = count != null ? Integer.parseInt(count.toString()) : 0;

        return new PageImpl<>(list, PageRequest.of(pageIndex, pageSize), total);
    }

    public List<PersonDTO> tList() {
        String sql = "select last_name as lastName, email from t_person";
        return nativeQueryHelper.nativeQuery(sql, PersonDTO.class);
    }

    public PersonDTO tSingleResult() {
        String sql = "select last_name as lastName, email from t_person where id = 1";
        return nativeQueryHelper.nativeQuerySingleResult(sql, PersonDTO.class);
    }

    public Page<PersonDTO> tpage(int pageIndex, int pageSize) {
        String sql = "select last_name as lastName, email  from t_person";
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return nativeQueryHelper.nativeQueryPage(sql, pageable, PersonDTO.class);
    }
}