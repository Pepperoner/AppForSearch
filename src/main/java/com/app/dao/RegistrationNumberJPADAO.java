package com.app.dao;

import com.app.entity.RegistrationNumber;
import com.app.jpa.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RegistrationNumberJPADAO implements RegistrationNumberDAO {

    @Override
    public long getRegisId (String name){
        EntityManager em = null;
        RegistrationNumber registrationNumber;
        long regID = 0;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            registrationNumber = em.find(RegistrationNumber.class,name);
            regID = registrationNumber.getId();
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
        }finally {
            if (em != null) {
                em.close();
            }
        }
        return regID;
    }

    @Override
    public List<RegistrationNumber> getAll() {
        EntityManager em = null;
        List<RegistrationNumber> registrationNumberList = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            TypedQuery<RegistrationNumber> namedQuery = em.createNamedQuery("RegistrationNumber.getAll", RegistrationNumber.class);
            registrationNumberList = namedQuery.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return registrationNumberList;
    }
}
