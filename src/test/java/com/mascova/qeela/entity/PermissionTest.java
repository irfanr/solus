package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Login;
import com.mascova.qeela.entity.LoginRole;
import com.mascova.qeela.entity.Permission;
import com.mascova.qeela.service.LoginRoleService;
import com.mascova.qeela.service.LoginService;
import com.mascova.qeela.service.PermissionService;
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
