package com.mascova.qeela.service;

import com.mascova.qeela.dao.MenuDao;
import com.mascova.qeela.entity.Login;
import com.mascova.qeela.entity.Menu;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.lang3.StringUtils;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;  

    @Override
    public long countAll() {
        return menuDao.count();
    }

    @Override
    public void delete(Menu menu) {
        menuDao.delete(menu);
    }

    @Override
    public Menu find(Long id) {
        return menuDao.findOne(id);
    }

    @Override
    public Menu findRootMenu() {
        return Menu.findMenusByNameEquals(ROOT_MENU_NAME).getSingleResult();
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public DefaultTreeModel getTreeMenu() {

        int functionCounter = 0;
        Menu rootMenu = findRootMenu();
            ApplicationFunction rootAf = new ApplicationFunction(
                rootMenu.getName(), "F" + functionCounter, null,
                rootMenu.getLink().getLinkPath());
        functionCounter++;

        DefaultTreeModel dtm = new DefaultTreeModel(rootAf);

        // recursive        
        appendChildIntoTreeModel(rootMenu, rootAf, functionCounter);

        return dtm;
    }

    public List<Menu> findMenuEntries(int firstResult, int maxResults) {
        return menuDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public Menu save(Menu menu) {
        return menuDao.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        return menuDao.save(menu);
    }

    private void appendChildIntoTreeModel(
            Menu parentMenu, ApplicationFunction parentAf, int functionCounter) {

        if (parentMenu.getMenus().size() > 0) {

            for (Menu childMenu : parentMenu.getMenus()) {

                if (childMenu.getActive()) {

                    ApplicationFunction childAf;
                    if (StringUtils.equals(childMenu.getLink().getLinkPath(), "#")) {
                        childAf = new ApplicationFunction(childMenu.getName(), null);
                    } else {
                        childAf = new ApplicationFunction(
                                childMenu.getName(), "F" + functionCounter, childMenu.getLink().getIconPath(),
                                childMenu.getLink().getLinkPath());
                    }
                    functionCounter++;

                    // recursive
                    appendChildIntoTreeModel(childMenu, childAf, functionCounter);

                    parentAf.add(childAf);

                }

            }

        }

    }

    @Override
    public DefaultTreeModel getTreeMenu(Login authUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Long, Menu> getAllowedMenuMap(Login authUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> getSiblingMenus(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(List<Menu> t) {
        for (Menu menu : t) {
            delete(menu);
        }

    }

    @Override
    public List<Menu> findEntries(int firstResult, int maxResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Menu> save(List<Menu> menuList) {
        for (Menu menu : menuList) {
            save(menu);
        }
        return menuList;
    }

    @Override
    public List<Menu> update(List<Menu> menuList) {
        for (Menu menu : menuList) {
            update(menu);
        }
        return menuList;
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
