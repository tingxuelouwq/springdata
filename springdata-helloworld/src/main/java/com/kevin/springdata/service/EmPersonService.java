package com.kevin.springdata.service;

import com.kevin.springdata.dto.PersonDTO;
import com.kevin.springdata.dto.PersonTransDTO;
import com.kevin.springdata.entity.Person;
import com.kevin.springdata.transformer.ToPersonResultTransformer;
import com.kevin.springdata.util.JsonUtil;
import com.kevin.springdata.util.NativeQueryHelper;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String sql = "select last_name as lastName, email, birth from t_person";
        return nativeQueryHelper.nativeQuery(sql, PersonDTO.class);
    }

    public PersonDTO tSingleResult() {
        String sql = "select last_name as lastName, email, birth from t_person where id = 1";
        return nativeQueryHelper.nativeQuerySingleResult(sql, PersonDTO.class);
    }

    public Page<PersonDTO> tpage(int pageIndex, int pageSize) {
        String sql = "select last_name as lastName, email, birth  from t_person";
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return nativeQueryHelper.nativeQueryPage(sql, pageable, PersonDTO.class);
    }

    @SuppressWarnings("unchecked")
    public List<PersonTransDTO> tScalarTransList(List<Integer> ids) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where id in (:ids)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ids", ids);

        query.unwrap(NativeQueryImpl.class)
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("lastName", StandardBasicTypes.STRING)
                .addScalar("email", StandardBasicTypes.STRING)
                .addScalar("birth", StandardBasicTypes.DATE)
                .addScalar("auditStatus", StandardBasicTypes.STRING)
                .addScalar("processStatus", StandardBasicTypes.INTEGER);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PersonTransDTO> tEntityTransList(List<Integer> ids) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where id in (:ids)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ids", ids);

        query.unwrap(NativeQueryImpl.class)
                .setResultTransformer(new ToPersonResultTransformer(PersonTransDTO.class));
        return query.getResultList();
    }

    public List<PersonTransDTO> tEntityTransList2(List<Integer> ids) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where id in (:ids)";
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        return nativeQueryHelper.nativeQuery(sql, param, new ToPersonResultTransformer(PersonTransDTO.class));
    }

    public List<PersonTransDTO> tEntityTransList3(List<Integer> ids) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where id in (:ids)";
        QueryObject queryObject = new QueryObject();
        queryObject.setIds(ids);
        queryObject.setParam1("test");  // 无用参数，无需序列化到map里
        String json = JsonUtil.bean2Json(queryObject);
        Map<String, Object> param = JsonUtil.json2Map(json);
        return nativeQueryHelper.nativeQuery(sql, param, new ToPersonResultTransformer(PersonTransDTO.class));
    }

    public List<PersonTransDTO> tEntityTransList4(List<Integer> ids) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where id in (:ids)";
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        List<PersonTransDTO> persons = nativeQueryHelper.nativeQuery(sql, param, new ToPersonResultTransformer(PersonTransDTO.class));
        persons.forEach(person -> person.setAuditStatusInt(Integer.valueOf(person.getAuditStatus())));
        return persons;
    }

    public Page<PersonDTO> tpage2(int pageIndex, int pageSize) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person";
        String countSql = "select count(*) from t_person";
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return nativeQueryHelper.nativeQueryPage(sql, countSql, null, pageable, new ToPersonResultTransformer(PersonTransDTO.class));
    }

    public Long count() {
        String sql = "select count(*) from t_person";
        return nativeQueryHelper.nativeQueryCount(sql);
    }

    public List<PersonTransDTO> tEntityTransLike(String email) {
        String sql = "select id, last_name as lastName, email, birth, audit_status as auditStatus, process_status as processStatus from t_person where email like concat('%',:email,'%')";
        Map<String, Object> param = new HashMap<>();
        param.put("email", email);
        List<PersonTransDTO> persons = nativeQueryHelper.nativeQuery(sql, param, new ToPersonResultTransformer(PersonTransDTO.class));
        persons.forEach(person -> person.setAuditStatusInt(Integer.valueOf(person.getAuditStatus())));
        return persons;
    }
}
