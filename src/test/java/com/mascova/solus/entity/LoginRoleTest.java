package com.mascova.solus.entity;

import com.mascova.solus.entity.Permission;
import com.mascova.solus.entity.Menu;
import com.mascova.solus.entity.LoginRole;
import com.mascova.solus.service.LoginRoleService;
import com.mascova.solus.service.MenuService;
import org.junit.Assert;
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
public class LoginRoleTest {

    @Autowired
    LoginRoleService loginRoleService;
    
    @Autowired
    MenuService menuService;    

//    @Test
    public void testFindLoginRoleWithPermissions() {

        LoginRole loginRole = loginRoleService.findLoginRoleWithPermissions( new Long(1) );
        
        for (Permission permission : loginRole.getPermissions()) {
            System.out.println(""+permission.getCode());
        }
        
        loginRole.containPermission(Permission.PERMISSION_MITRA_UPDATE);
        
        
        Assert.assertTrue(true);
    }
    
//    @Test    
    public void testAddMenuToLoginRole(){
        
        LoginRole loginRole = loginRoleService.find( new Long(8) );
        Menu menu = menuService.find( new Long(19) );
        
        loginRoleService.addMenuToLoginRole(loginRole, menu);
        
    }
    
//    @Test    
    public void removeMenuFromLoginRole(){
        
        LoginRole loginRole = loginRoleService.find( new Long(8) );
        Menu menu = menuService.find( new Long(20) );
        
        loginRoleService.removeMenuFromLoginRole(loginRole, menu);        
        
    }
    
//    @Test    
    public void isLoginRoleHasMenuAccess(){
        
        LoginRole loginRole = loginRoleService.find( new Long(1) );
        Menu menu = menuService.find( new Long(30) );
        
        boolean hasAccess = loginRoleService.isLoginRoleHasMenuAccess(loginRole, menu);
        
        System.out.println("hasAccess: " + hasAccess);
        
    }    

}
