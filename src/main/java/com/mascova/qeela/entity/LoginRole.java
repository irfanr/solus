package com.mascova.qeela.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurable
public class LoginRole extends ValueObjectImpl implements Serializable {

    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_role_id_seq")
    @SequenceGenerator(name = "login_role_id_seq", sequenceName = "login_role_id_seq", allocationSize = 100)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;
    @NotNull
    @Size(max = 40)
    private String code;
    @NotNull
    @Size(max = 40)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginRole")
    private Set<Login> loginList = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "loginRoles")
    private Set<Menu> logins = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "loginRoles")
    private Set<Menu> menus = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions = new ArrayList<>();

    public boolean equals(Object obj) {
        if (!(obj instanceof LoginRole)) {
            return false;
        }
        if (this.getCode().equals(((LoginRole) obj).getCode())) {
            return true;
        }
        LoginRole rhs = (LoginRole) obj;
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).toHashCode();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    // BEGIN JPQL/Criteria
    public static long countLoginRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM LoginRole o", Long.class).getSingleResult();
    }

    public static List<LoginRole> findAllLoginRoles() {
        return entityManager().createQuery("SELECT o FROM LoginRole o", LoginRole.class).getResultList();
    }

    public static LoginRole findByCode(String code) {

        String qlString = "SELECT o FROM LoginRole o where o.code = :code";
        TypedQuery<LoginRole> q = entityManager().createQuery(qlString, LoginRole.class);

        q.setParameter("code", code);

        return q.getSingleResult();
    }

    public static LoginRole findLoginRole(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(LoginRole.class, id);
    }

    public static List<LoginRole> findLoginRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM LoginRole o", LoginRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<LoginRole> findLoginRoleByCodes(List<String> loginRoleCodes) {

        String qlString = "SELECT o FROM LoginRole o where o.code IN :loginRoleCodes";
        Query q = entityManager().createQuery(qlString, LoginRole.class);

        q.setParameter("loginRoleCodes", loginRoleCodes);

        return q.getResultList();
    }

    public static LoginRole findLoginRoleWithPermissions(Long id) {
        TypedQuery<LoginRole> q = LoginRole.entityManager().createQuery(
                "SELECT lr FROM LoginRole lr LEFT JOIN FETCH lr.permissions WHERE lr.id = :id", LoginRole.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    // END JPQL/Criteria    
    // BEGIN Active Record    
    @Transactional
    public void persist() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            LoginRole attached = LoginRole.findLoginRole(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        this.entityManager.clear();
    }

    @Transactional
    public LoginRole merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        LoginRole merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    // END Active Record    
    public static final EntityManager entityManager() {
        EntityManager em = new LoginRole().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public boolean containPermission(String permissionCode) {

        for (Permission permission : permissions) {
            if (StringUtils.equals(permission.getCode(), permissionCode)) {
                return true;
            }
        }
        return false;

    }

    // BEGIN Getter/Setter
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
    private static final long serialVersionUID = 1L;

    /**
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the logins
     */
    public Set<Menu> getLogins() {
        return logins;
    }

    /**
     * @param logins the logins to set
     */
    public void setLogins(Set<Menu> logins) {
        this.logins = logins;
    }

    /**
     * @return the loginList
     */
    public Set<Login> getLoginList() {
        return loginList;
    }

    /**
     * @param loginList the loginList to set
     */
    public void setLoginList(Set<Login> loginList) {
        this.loginList = loginList;
    }
    // END Getter/Setter    
}
