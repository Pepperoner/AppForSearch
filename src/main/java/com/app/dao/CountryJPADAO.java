package com.app.dao;

import com.app.entity.Country;
import com.app.jpa.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CountryJPADAO implements CountryDAO {

    @Override
    public String getCountryName(long id) {
        EntityManager em = null;
        Country country;
        String countryName = "";
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            country = em.find(Country.class, id);
            countryName = country.getName();
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
        return countryName;
    }

    @Override
    public List<Country> getAll() {
        EntityManager em = null;
        List<Country> countryList = null;
        try {
            em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Country> namedQuery = em.createNamedQuery("Country.getAll", Country.class);
            countryList = namedQuery.getResultList();
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
        return countryList;
    }
}
