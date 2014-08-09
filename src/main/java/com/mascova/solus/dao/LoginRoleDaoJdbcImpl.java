/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irfan
 */
@Repository
public class LoginRoleDaoJdbcImpl implements LoginRoleDaoJdbc {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addMenuToLoginRole(Long loginRoleId, Long menuId) {

        this.jdbcTemplate.update(
                "insert into menu_login_roles (menus, login_roles) values (?, ?)",
                menuId, loginRoleId);

    }

    @Override
    public void removeMenuFromLoginRole(Long loginRoleId, Long menuId) {

        this.jdbcTemplate.update(
                "delete from menu_login_roles where menus = ? AND login_roles = ?",
                Long.valueOf(menuId), Long.valueOf(loginRoleId));
    }

    @Override
    public boolean isLoginRoleHasMenuAccess(Long loginRoleId, Long menuId) {

        Integer rowCount = this.jdbcTemplate.queryForInt(
                "select count(*) from menu_login_roles where menus = ? AND login_roles = ?", new Object[]{menuId.intValue(), loginRoleId.intValue()});

        return rowCount > 0;
    }
}
