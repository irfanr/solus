/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.entity.Login;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    LoginService loginService;

    @Override
    public Login login(String username, String password) throws Exception {

        Login authenticatedLogin = null;
        try {
            authenticatedLogin = loginService.findByUsername(username);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new Exception("Username tidak ditemukan");
        }

        if (authenticatedLogin == null) {
//            loginDialog.setWarnMessage("Username tidak ditemukan");
            throw new Exception("Username tidak ditemukan");
//            return false;
        } else {

            Login attemptedLogin = new Login();
            attemptedLogin.setPassword(password);
            loginService.encryptPassword(attemptedLogin);
            
            if (!StringUtils.equals(authenticatedLogin.getPassword(), attemptedLogin.getPassword())) {
//                loginDialog.setWarnMessage("Password tidak cocok");
                throw new Exception("Password tidak cocok");
//                return false;
            } else {
                return authenticatedLogin;
            }

        }
    }
}
