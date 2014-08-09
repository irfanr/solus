package com.mascova.solus.service;

import com.mascova.solus.service.LoginService;
import com.mascova.solus.entity.Login;
import java.security.NoSuchAlgorithmException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class LoginServiceTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    LoginService loginService;

    @Test
    public void testEncryptPassword() throws NoSuchAlgorithmException {
        
        String password = "aaa";
        String encryptedPassword = "240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9";
        Login attemptedLogin = new Login();
        attemptedLogin.setPassword(password);
        
        loginService.encryptPassword(attemptedLogin);
        
//        Assert.assertEquals("tidak cocok", encryptedPassword, attemptedLogin.getPassword());
    }

  
}
