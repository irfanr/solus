package com.mascova.solus.service;

import com.mascova.solus.dao.LinkDao;
import com.mascova.solus.entity.Link;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkDao linkDao;

    @Override
    public long countAll() {
        return linkDao.count();
    }

    @Override
    public void delete(Link link) {
        linkDao.delete(link);
    }

    @Override
    public Link find(Long id) {
        return linkDao.findOne(id);
    }

    @Override
    public List<Link> findAll() {
        return linkDao.findAll();
    }
    
    @Override
    public List<Link> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public List<Link> findEntries(int firstResult, int maxResults) {
        return linkDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public Link save(Link link) {
        return linkDao.save(link);
    }

    @Override
    public Link update(Link link) {
        return linkDao.save(link);
    }

    @Override
    public List<Link> save(List<Link> linkList) {
        for (Link link : linkList) {
            save(link);
        }
        
        return linkList;
    }

    @Override
    public List<Link> update(List<Link> linkList) {
        for (Link link : linkList) {
            update(link);
        }
        
        return linkList;
    }

    @Override
    public void delete(List<Link> linkList) {
        for (Link link : linkList) {
            delete(link);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
