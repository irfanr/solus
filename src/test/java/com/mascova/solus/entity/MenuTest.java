package com.mascova.solus.entity;

import com.mascova.solus.entity.Menu;
import com.mascova.solus.service.LoginRoleService;
import com.mascova.solus.service.MenuService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
//@Transactional
//@Configurable
public class MenuTest {

    @Autowired
    LoginRoleService loginRoleService;
    
    @Autowired
    MenuService menuService;    

    @Test
    public void testGetSiblingMenus() {
        
//        Menu menu = menuService.findMenu( new Long(8) );
//
//        List<Menu> menuList =  menuService.getSiblingMenus(menu);
//        
//        System.out.println("menuList.size(): " + menuList.size());
    }

}
