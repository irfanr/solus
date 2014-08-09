package com.mascova.qeela.service;

import com.mascova.qeela.dao.PersonDao;
import com.mascova.qeela.entity.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.openswing.swing.util.server.JPAUtils;
import static org.openswing.swing.util.server.JPAUtils.applyFiltersAndSorter;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public long countAll() {
        return personDao.count();
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Override
    public Person find(Long id) {
        return personDao.findOne(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public List<Person> findEntries(int firstResult, int maxResults) {
        return personDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Person> findEntries(
            int first,
            int pageSize,
            String sortField,
            SortOrder sortOrder,
            Map<String, String> filters) {

        return Person.findEntries(first, pageSize, sortField, sortOrder, filters);
    }
    
    @Override
    public List<Person> findEntries(int action, int startIndex, int blockSize,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            String baseSQL,
            Object[] paramValues) {
        try {
            ArrayList values = new ArrayList();
            values.addAll(Arrays.asList(paramValues));
            baseSQL = applyFiltersAndSorter(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    baseSQL,
                    values,
                    JPAUtils.getValueObjectAlias(valueObjectType, baseSQL));

            Query q = Person.entityManager().createQuery(baseSQL);
            for (int i = 0; i < values.size(); i++) {
                q.setParameter(i + 1, values.get(i));
            }

            q.setFirstResult(startIndex);
            q.setMaxResults(blockSize);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }    

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public Person update(Person person) {
        return personDao.save(person);
    }

    @Override
    public List<Person> save(List<Person> personList) {
        for (Person person : personList) {
            save(person);
        }
        
        return personList;
    }

    @Override
    public List<Person> update(List<Person> personList) {
        for (Person person : personList) {
            update(person);
        }
        
        return personList;
    }

    @Override
    public void delete(List<Person> personList) {
        for (Person person : personList) {
            delete(person);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
