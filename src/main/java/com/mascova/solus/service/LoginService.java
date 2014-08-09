package com.mascova.solus.service;

import com.mascova.solus.entity.Login;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

public interface LoginService extends DeskCrudService<Login>{

    public abstract Login findByUsername(String username);

    public abstract Login findLoginWithPelatihAndMitra(Login login);

    public abstract Login findLoginWithPermissions(Login login);

    public boolean isUnique(Long loginId, String username);

    public abstract Login saveAndEncryptPassword(Login login) throws NoSuchAlgorithmException;    
    
    public abstract Login saveAndEncryptPassword(Login login, boolean isCreateBareMinimumPelatih) throws NoSuchAlgorithmException;
    
    public abstract Login updateAndEncryptPassword(Login login) throws NoSuchAlgorithmException;    
        
    public abstract Login emptyPassword(Login login);

    public abstract List<Login> emptyPassword(List<Login> loginList);

    public abstract Login encryptPassword(Login login) throws NoSuchAlgorithmException;

    public abstract Login encryptPassword(List<Login> loginList) throws NoSuchAlgorithmException;

    public abstract void addRolesToUser(Login login, List<String> loginRoleCodes);

    public abstract void removeRolesFromUser(Login login, List<String> loginRoleCodes);
}
