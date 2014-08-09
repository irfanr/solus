/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@Entity
@Configurable
public class Permission extends ValueObjectImpl implements GrantedAuthority, Serializable {

    public static final String PERMISSION_MITRA_READ = "MITRA_READ";
    public static final String PERMISSION_MITRA_CREATE = "MITRA_CREATE";
    public static final String PERMISSION_MITRA_UPDATE = "MITRA_UPDATE";
    public static final String PERMISSION_MITRA_DELETE = "MITRA_DELETE";
    public static final String PERMISSION_PELATIH_READ = "PELATIH_READ";
    public static final String PERMISSION_PELATIH_CREATE = "PELATIH_CREATE";
    public static final String PERMISSION_PELATIH_UPDATE = "PELATIH_UPDATE";
    public static final String PERMISSION_PELATIH_DELETE = "PELATIH_DELETE";
    public static final String PERMISSION_PESERTA_READ = "PESERTA_READ";
    public static final String PERMISSION_PESERTA_CREATE = "PESERTA_CREATE";
    public static final String PERMISSION_PESERTA_UPDATE = "PESERTA_UPDATE";
    public static final String PERMISSION_PESERTA_DELETE = "PESERTA_DELETE";
    public static final String PERMISSION_PELATIHAN_READ = "PELATIHAN_READ";
    public static final String PERMISSION_PELATIHAN_CREATE = "PELATIHAN_CREATE";
    public static final String PERMISSION_PELATIHAN_UPDATE = "PELATIHAN_UPDATE";
    public static final String PERMISSION_PELATIHAN_DELETE = "PELATIHAN_DELETE";
    public static final String PERMISSION_KOORDINATOR_READ = "KOORDINATOR_READ";
    public static final String PERMISSION_KOORDINATOR_UPDATE = "KOORDINATOR_UPDATE";
    public static final String PERMISSION_PENUGASAN_KOORD_PELATIH = "PENUGASAN_KOORD_PELATIH";
    public static final String PERMISSION_CHECK_DATA = "CHECK_DATA";
    public static final String PERMISSION_APPROVE_DATA = "APPROVE_DATA";
    public static final String PERMISSION_VIEW_ALL_DATA = "VIEW_ALL_DATA";
    public static final String PERMISSION_PERMISSION_READ = "PERMISSION_READ";
    public static final String PERMISSION_PEKERJAAN_READ = "PEKERJAAN_READ";
    public static final String PERMISSION_PEKERJAAN_CREATE = "PEKERJAAN_CREATE";
    public static final String PERMISSION_PEKERJAAN_UPDATE = "PEKERJAAN_UPDATE";
    public static final String PERMISSION_PEKERJAAN_DELETE = "PEKERJAAN_DELETE";
    public static final String PERMISSION_PENDIDIKAN_READ = "PENDIDIKAN_READ";
    public static final String PERMISSION_PENDIDIKAN_CREATE = "PENDIDIKAN_CREATE";
    public static final String PERMISSION_PENDIDIKAN_UPDATE = "PENDIDIKAN_UPDATE";
    public static final String PERMISSION_PENDIDIKAN_DELETE = "PENDIDIKAN_DELETE";
    public static final String PERMISSION_HUBUNGAN_READ = "HUBUNGAN_READ";
    public static final String PERMISSION_HUBUNGAN_CREATE = "HUBUNGAN_CREATE";
    public static final String PERMISSION_HUBUNGAN_UPDATE = "HUBUNGAN_UPDATE";
    public static final String PERMISSION_HUBUNGAN_DELETE = "HUBUNGAN_DELETE";
    public static final String PERMISSION_POSISI_READ = "POSISI_READ";
    public static final String PERMISSION_POSISI_CREATE = "POSISI_CREATE";
    public static final String PERMISSION_POSISI_UPDATE = "POSISI_UPDATE";
    public static final String PERMISSION_POSISI_DELETE = "POSISI_DELETE";
    public static final String PERMISSION_LAPORAN_READ = "LAPORAN_READ";
    public static final String KEBUTUHAN_SERTIFIKAT_READ = "KEBUTUHAN_SERTIFIKAT_READ";
    public static final String DATA_BENEFICIARIES_READ = "DATA_BENEFICIARIES_READ";
    public static final String PERMISSION_MILES_DSBRD_READ = "MILES_DSBRD_READ";
    public static final String PERMISSION_ADMIN_DSBRD_READ = "ADMIN_DSBRD_READ";
    @PersistenceContext
    transient EntityManager entityManager;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_id_seq")
    @SequenceGenerator(name = "permission_id_seq", sequenceName = "permission_id_seq", allocationSize = 100)
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
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "permissions")
    private Set<LoginRole> loginRoles = new HashSet<>();

    public Permission() {
    }

    public Permission(String code) {
        this.code = code;
    }

    public static final EntityManager entityManager() {
        EntityManager em = new Permission().entityManager;
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
            Permission attached = Permission.findPermission(this.id);
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
    public Permission merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Permission merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public static Permission findPermission(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Permission.class, id);
    }

    public static List<Permission> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Permission findPermissionByCode(String code) {
        EntityManager em = Permission.entityManager();
        TypedQuery<Permission> q = em.createQuery("SELECT p FROM Permission p WHERE p.code = :code )", Permission.class);
        q.setParameter("code", code);
        return q.getSingleResult();
    }

    public static List<Permission> findPermissionByLogin(Login login) {
        EntityManager em = Permission.entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Permission> p = cb.createQuery(Permission.class);

        Predicate criteria = cb.conjunction();

        Root<Permission> permissionRoot = p.from(Permission.class);

        Join<Permission, LoginRole> loginRoleJoin = permissionRoot.join("loginRoles");
        Join<LoginRole, Login> loginJoin = loginRoleJoin.join("logins");

        ParameterExpression<Long> peLogin =
                cb.parameter(Long.class, "login.id");
        criteria = cb.and(
                criteria,
                cb.equal(loginJoin.<Long>get("id"), peLogin));

        p.select(permissionRoot).distinct(true);

        p.where(criteria);

        TypedQuery<Permission> q = em.createQuery(p);
        q.setParameter("login.id", login.getId());
        return q.getResultList();

    }

    public static List<Permission> findPermissionByLoginRole(Login login) {
        EntityManager em = Permission.entityManager();
        TypedQuery<Permission> q = em.createQuery("SELECT p FROM Permission p  WHERE p.loginRoles.logins.id = :id )", Permission.class);
        q.setParameter("id", login.getId());
        return q.getResultList();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Permission)) {
            return false;
        }
        if (this.getCode().equals(((Permission) obj).getCode())) {
            return true;
        }
        Permission rhs = (Permission) obj;
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).toHashCode();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @return the loginRoles
     */
    public Set<LoginRole> getLoginRoles() {
        return loginRoles;
    }

    /**
     * @param loginRoles the loginRoles to set
     */
    public void setLoginRoles(Set<LoginRole> loginRoles) {
        this.loginRoles = loginRoles;
    }

    @Override
    public String getAuthority() {
        return code;
    }
}
