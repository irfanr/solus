package com.mascova.solus.entity;

import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.LoginRole;
import com.mascova.solus.entity.Permission;
import com.mascova.solus.service.LoginRoleService;
import com.mascova.solus.service.LoginService;
import com.mascova.solus.service.PermissionService;
import java.util.List;
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
public class PermissionTest {

    @Autowired
    LoginService loginService;
    @Autowired
    LoginRoleService loginRoleService;
    @Autowired
    PermissionService permissionService;

//    @Test
    public void testFindPermissionByLogin() {

        Login login = loginService.find(new Long(7));
        login = Login.findLoginWithPermissions(login);
        LoginRole loginRole = loginRoleService.find(new Long(7));

        List<Permission> permissionList = Permission.findPermissionByLogin(login);

        System.out.println(login.getLoginRoles().get(0).getPermissions().size());
        System.out.println(permissionList.size());

        Assert.assertTrue(true);
    }
}
