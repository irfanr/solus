package com.mascova.solus.service;

import com.mascova.solus.dao.AddressDao;
import com.mascova.solus.entity.Address;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public long countAll() {
        return addressDao.count();
    }

    @Override
    public void delete(Address address) {
        addressDao.delete(address);
    }

    @Override
    public Address find(Long id) {
        return addressDao.findOne(id);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }
    
    @Override
    public List<Address> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public List<Address> findEntries(int firstResult, int maxResults) {
        return addressDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public Address save(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressDao.save(address);
    }

    @Override
    public List<Address> save(List<Address> addressList) {
        for (Address address : addressList) {
            save(address);
        }
        
        return addressList;
    }

    @Override
    public List<Address> update(List<Address> addressList) {
        for (Address address : addressList) {
            update(address);
        }
        
        return addressList;
    }

    @Override
    public void delete(List<Address> addressList) {
        for (Address address : addressList) {
            delete(address);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
