/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.entity.Login;

/**
 *
 * @author irfan
 */
public interface AuthService {
    
    public abstract Login login(String username, String password) throws Exception;
    
}
