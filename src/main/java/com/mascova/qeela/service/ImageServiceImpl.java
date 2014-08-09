package com.mascova.qeela.service;

import com.mascova.qeela.dao.ImageDao;
import com.mascova.qeela.entity.Image;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Override
    public long countAll() {
        return imageDao.count();
    }

    @Override
    public void delete(Image image) {
        imageDao.delete(image);
    }

    @Override
    public Image find(Long id) {
        return imageDao.findOne(id);
    }

    @Override
    public List<Image> findAll() {
        return imageDao.findAll();
    }
    
    @Override
    public List<Image> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public List<Image> findEntries(int firstResult, int maxResults) {
        return imageDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public Image save(Image image) {
        return imageDao.save(image);
    }

    @Override
    public Image update(Image image) {
        return imageDao.save(image);
    }

    @Override
    public List<Image> save(List<Image> imageList) {
        for (Image image : imageList) {
            save(image);
        }
        
        return imageList;
    }

    @Override
    public List<Image> update(List<Image> imageList) {
        for (Image image : imageList) {
            update(image);
        }
        
        return imageList;
    }

    @Override
    public void delete(List<Image> imageList) {
        for (Image image : imageList) {
            delete(image);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
