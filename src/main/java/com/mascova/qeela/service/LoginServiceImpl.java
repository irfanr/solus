package com.mascova.qeela.service;

import com.mascova.qeela.dao.LoginDao;
import com.mascova.qeela.entity.Login;
import com.mascova.qeela.entity.LoginRole;
import com.mascova.qeela.entity.SystemLookup;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.openswing.swing.util.server.JPAUtils;
import static org.openswing.swing.util.server.JPAUtils.applyFiltersAndSorter;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public long countAll() {
        return loginDao.count();
    }

    @Override
    public void delete(Login login) {
//        login.setActive(Login.STATUS_NOT_ACTIVE);
//        try {
//            update(login);
//
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        loginDao.delete(login);
    }

    @Override
    public List<Login> findEntries(int action, int startIndex, int blockSize,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            String baseSQL,
            Object[] paramValues) {
        try {
            ArrayList values = new ArrayList();
            values.addAll(Arrays.asList(paramValues));
            baseSQL = applyFiltersAndSorter(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    baseSQL,
                    values,
                    JPAUtils.getValueObjectAlias(valueObjectType, baseSQL));

            Query q = Login.entityManager().createQuery(baseSQL);
            for (int i = 0; i < values.size(); i++) {
                q.setParameter(i + 1, values.get(i));
            }

            q.setFirstResult(startIndex);
            q.setMaxResults(blockSize);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<Login> findEntries(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, String> filters) {

        return Login.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public Login find(Long id) {
        return loginDao.findOne(id);
    }

    @Override
    public Login findByUsername(String username) {
        if (username == null || username.length() == 0) {
            throw new IllegalArgumentException("The username argument is required");
        }
        EntityManager em = Login.entityManager();
        TypedQuery<Login> q = em.createQuery("SELECT l FROM Login l LEFT JOIN FETCH l.loginRoles as lr LEFT JOIN FETCH lr.menus as m WHERE l.username = :username", Login.class);
        q.setParameter("username", username);
        return q.getSingleResult();
    }

    @Override
    public List<Login> findAll() {
        return loginDao.findAll();
    }

    @Override
    public List<Login> findEntries(int firstResult, int maxResults) {
        return loginDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public Login save(Login login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Login saveAndEncryptPassword(Login login) throws NoSuchAlgorithmException {
        return saveAndEncryptPassword(login, true);
    }

    @Override
    public Login saveAndEncryptPassword(Login login, boolean isCreateBareMinimumPelatih) throws NoSuchAlgorithmException {

        encryptPassword(login);
        return loginDao.save(login);
    }

    @Override
    public Login update(Login login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Login updateAndEncryptPassword(Login login) throws NoSuchAlgorithmException {

        if (StringUtils.isNotBlank(login.getPassword())) {

            // let's assume that uncrypted password max length should < 64
            if (login.getPassword().length() != 64) {
                encryptPassword(login);
            }


        } else {
            String existingEncyrptedPassword = find(login.getId()).getPassword();
            login.setPassword(existingEncyrptedPassword);
        }

        return loginDao.save(login);
    }

    @Override
    public Login emptyPassword(Login login) {
        if (login != null) {
            login.setPassword("");
        }

        return login;
    }

    @Override
    public List<Login> emptyPassword(List<Login> loginList) {

        for (Login login : loginList) {
            emptyPassword(login);
        }

        return loginList;
    }

    @Override
    public Login encryptPassword(Login login) throws NoSuchAlgorithmException {

        // let's assume that uncrypted password max length should < 64
        if (login.getPassword().length() != 64) {

            if (login != null) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA-256");

                md.update(login.getPassword().getBytes());

                byte byteData[] = md.digest();

                //convert the byte to hex format method 1
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

//            System.out.println("Hex format : " + sb.toString());

                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    String hex = Integer.toHexString(0xff & byteData[i]);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }

                login.setPassword(hexString.toString());

            }

        }

        return login;
    }

    @Override
    public Login encryptPassword(List<Login> loginList) throws NoSuchAlgorithmException {

        for (Login login : loginList) {
            encryptPassword(login);
        }

        return encryptPassword(loginList);
    }

    @Override
    public void addRolesToUser(Login login, List<String> loginRoleCodes) {

        login = find(login.getId());
        List<LoginRole> addedLoginRoles =
                LoginRole.findLoginRoleByCodes(loginRoleCodes);

        login.getLoginRoles().addAll(addedLoginRoles);
        loginDao.save(login);

    }

    @Override
    public void removeRolesFromUser(Login login, List<String> loginRoleCodes) {

        login = find(login.getId());
        List<LoginRole> removedLoginRoles =
                LoginRole.findLoginRoleByCodes(loginRoleCodes);

        login.getLoginRoles().removeAll(removedLoginRoles);
        loginDao.save(login);
    }

    @Override
    public Login findLoginWithPelatihAndMitra(Login login) {
        return Login.findLoginWithPelatihAndMitra(login);
    }

    @Override
    public Login findLoginWithPermissions(Login login) {
        return Login.findLoginWithPermissions(login);
    }

    @Override
    public boolean isUnique(Long loginId, String username) {

        return !Login.isUsernameExist(loginId, username);
    }

    @Override
    public List<Login> save(List<Login> loginList) {
        for (Login login : loginList) {
            save(login);
        }

        return loginList;
    }

    @Override
    public List<Login> update(List<Login> loginList) {
        for (Login login : loginList) {
            update(login);
        }

        return loginList;
    }

    @Override
    public void delete(List<Login> loginList) {
        for (Login login : loginList) {
            delete(login);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
