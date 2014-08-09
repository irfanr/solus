package com.mascova.solus.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Login extends ValueObjectImpl implements UserDetails, Serializable {

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_NOT_ACTIVE = "N";
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_id_seq")
    @SequenceGenerator(name = "login_id_seq", sequenceName = "login_id_seq", allocationSize = 100)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;
    @NotNull
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modified;
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 25)
    private String username;
    @NotNull
    @Size(min = 0, max = 64)
    private String password;
    @Size(min = 3, max = 80)
    private String email;
    @NotNull
    @Size(min = 1, max = 8)
    private String active = Login.STATUS_ACTIVE;
    @OneToOne(mappedBy = "login")
    private Employee employee;
    @ManyToOne
    private LoginRole loginRole;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LoginRole> loginRoles = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Link> links = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastLogin;

    public boolean equals(Object obj) {
        if (!(obj instanceof Login)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Login rhs = (Login) obj;
        return new EqualsBuilder().append(created, rhs.created).append(id, rhs.id).append(modified, rhs.modified).append(password, rhs.password).append(username, rhs.username).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(created).append(id).append(modified).append(password).append(username).toHashCode();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    // BEGIN JPQL/Criteria
    public static long countLogins() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Login o", Long.class).getSingleResult();
    }

    public static List<Login> findAllLogins() {
        return entityManager().createQuery("SELECT o FROM Login o", Login.class).getResultList();
    }

    public static List<Login> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        EntityManager em = entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Login> p = cb.createQuery(Login.class);

        Root<Login> loginRoot = p.from(Login.class);

        p.select(loginRoot);

        // BEGIN Order
        if (StringUtils.isNotBlank(sortField)) {
            Order order = null;
            if (sortOrder == SortOrder.ASCENDING) {
                order = cb.asc(loginRoot.get(sortField));
            } else if (sortOrder == SortOrder.DESCENDING) {
                order = cb.desc(loginRoot.get(sortField));
            }
            p.orderBy(order);
        }
        // END Order  

        // BEGIN Filter
        Predicate criteria = cb.conjunction();

        if (!filters.isEmpty()) {

            for (Map.Entry<String, String> filter : filters.entrySet()) {
//                System.out.println("Key : " + filter.getKey() + " Value : "
//                        + filter.getValue());

                ParameterExpression<String> peString = null;
                switch (filter.getKey()) {
                    case "username":
                        peString =
                                cb.parameter(String.class, "username");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(loginRoot.<String>get("username")), cb.upper(peString)));
                        break;
                    default:
                        ;
                        break;
                }
            }

        }
        // END Filter

        // active only
        ParameterExpression<String> peActive =
                cb.parameter(String.class, "active");
        criteria = cb.and(
                criteria,
                cb.equal(loginRoot.<String>get("active"), peActive));

        p.where(criteria);

        TypedQuery<Login> q = em.createQuery(p)
                .setFirstResult(first).setMaxResults(pageSize);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), "%" + filter.getValue() + "%");
        }
        q.setParameter(peActive, Login.STATUS_ACTIVE);
        return q.getResultList();
    }

    public static Login findLogin(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Login.class, id);
    }

    public static Login findLoginWithPermissions(Login login) {
        EntityManager em = Login.entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Login> p = cb.createQuery(Login.class);

        Predicate criteria = cb.conjunction();

        Root<Login> loginRoot = p.from(Login.class);

        Join<Login, LoginRole> loginRoleJoin = loginRoot.join("loginRole");
        Join<LoginRole, Permission> permissionJoin = loginRoleJoin.join("permissions", JoinType.LEFT);

        ParameterExpression<Long> peLogin =
                cb.parameter(Long.class, "login.id");
        criteria = cb.and(
                criteria,
                cb.equal(loginRoot.<Long>get("id"), peLogin));

        p.select(loginRoot).distinct(true);

        p.where(criteria);

        TypedQuery<Login> q = em.createQuery(p);
        q.setParameter("login.id", login.getId());
        return q.getSingleResult();
    }

    public static Login findLoginWithPermissions2(Login login) {
        EntityManager em = Login.entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Login> p = cb.createQuery(Login.class);

        Predicate criteria = cb.conjunction();

        Root<Login> loginRoot = p.from(Login.class);

        Join<Login, LoginRole> loginRoleJoin = loginRoot.join("loginRoles");
        Join<LoginRole, Permission> permissionJoin = loginRoleJoin.join("permissions");

        ParameterExpression<Long> peLogin =
                cb.parameter(Long.class, "login.id");
        criteria = cb.and(
                criteria,
                cb.equal(loginRoot.<Long>get("id"), peLogin));

        p.select(loginRoot).distinct(true);

        p.where(criteria);

        TypedQuery<Login> q = em.createQuery(p);
        q.setParameter("login.id", login.getId());
        return q.getSingleResult();
    }

    public static List<Login> findLoginEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Login o", Login.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    // END JPQL/Criteria    
    // BEGIN Active Record    
    public static final EntityManager entityManager() {
        EntityManager em = new Login().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

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
            Login attached = Login.findLogin(this.id);
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
    public Login merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Login merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    // END Active Record    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Login loginWithPermission = Login.findLoginWithPermissions(this);
//        for (LoginRole loginRole : loginWithPermission.getLoginRoles()) {
//            grantedAuthoritySet.addAll(loginRole.getPermissions());
//        }
//        return grantedAuthoritySet;
        return loginWithPermission.getLoginRole().getPermissions();
    }

    public boolean hasPermission(String permissionCode) {

        for (GrantedAuthority authority : getAuthorities()) {
            if (authority.getAuthority().equals(permissionCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equalsIgnoreCase(active, STATUS_ACTIVE);
    }

    public static boolean isUsernameExist(Long loginId, String username) {

        String query = "SELECT count(l) FROM Login l WHERE l.username = :username AND l.active = :active";

        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("The username argument is required");
        }

        if (loginId != null) {
            query += " AND l.id != :id";
        }
        EntityManager em = entityManager();
        TypedQuery<Long> q = em.createQuery(query, Long.class);
        q.setParameter("username", username);
        if (loginId != null) {
            q.setParameter("id", loginId);
        }
        q.setParameter("active", Login.STATUS_ACTIVE);
        long count = q.getSingleResult();

        return count != 0;
    }

    public static Login findLoginWithPelatihAndMitra(Login login) {
        EntityManager em = Login.entityManager();
        TypedQuery<Login> q = em.createQuery("SELECT l FROM Login l LEFT JOIN FETCH l.pelatih AS plth LEFT JOIN FETCH plth.mitra AS m WHERE l.id = :id", Login.class);
        q.setParameter("id", login.getId());
        return q.getSingleResult();
    }

    // BEGIN Getter/Setter
    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the loginRole
     */
    public LoginRole getLoginRole() {
        return loginRole;
    }

    /**
     * @param loginRole the loginRole to set
     */
    public void setLoginRole(LoginRole loginRole) {
        this.loginRole = loginRole;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return this.modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<LoginRole> getLoginRoles() {
        return this.loginRoles;
    }

    public void setLoginRoles(List<LoginRole> loginRoles) {
        this.loginRoles = loginRoles;
    }

    public List<Link> getLinks() {
        return this.links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
    // END Getter/Setter    
}
