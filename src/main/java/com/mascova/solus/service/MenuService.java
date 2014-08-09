package com.mascova.solus.service;

import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.Menu;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultTreeModel;

public interface MenuService extends CrudService<Menu>{

    public static final String ROOT_MENU_NAME = "Root";
    
    public abstract Menu findRootMenu();

    public abstract DefaultTreeModel getTreeMenu(Login authUser);

    public abstract Map<Long, Menu> getAllowedMenuMap(Login authUser);

    public abstract List<Menu> getSiblingMenus(Menu menu);
    
    public abstract DefaultTreeModel getTreeMenu();    
}
