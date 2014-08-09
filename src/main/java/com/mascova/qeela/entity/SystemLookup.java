/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.entity;

import static com.mascova.qeela.entity.SystemLookup.entityManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@Entity
@Configurable
public class SystemLookup extends ValueObjectImpl implements Serializable {

    public static final String TYPE_STATUS = "PLTSTTS";
    public static final String TYPE_PEKERJAAN = "OCCU";
    public static final String TYPE_PENDIDIKAN = "EDU";
    public static final String TYPE_STATUS_PERNIKAHAN = "MRTSTS";
    public static final String TYPE_HUBUNGAN = "RELT";
    public static final String TYPE_STATUS_INPUT = "INSR";
    public static final String TYPE_STATUS_REJECTED = "RJCT";
    public static final String TYPE_STATUS_CHECKED = "CHCK";
    public static final String TYPE_STATUS_CANCELED = "CNCL";
    public static final String TYPE_STATUS_APPROVED = "APPR";
    public static final int STATUS_GRADE_REJECTED = 0;
    public static final int STATUS_GRADE_INPUTTED = 1;
    public static final int STATUS_GRADE_CANCELED = 2;
    public static final int STATUS_GRADE_CHECKED = 3;
    public static final int STATUS_GRADE_APPROVED = 4;
    public static final String SYSTEM_LOOKUP_GNDR_MALE = "M";
    public static final String SYSTEM_LOOKUP_GNDR_FEMALE = "F";
    public static final String PELATIHAN_TYPE_TOT = "TOT";
    public static final String PELATIHAN_TYPE_PLTH = "PLTH";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_lookup_id_seq")
    @SequenceGenerator(name = "system_lookup_id_seq", sequenceName = "system_lookup_id_seq", allocationSize = 100)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;
    @NotNull
    @Size(min = 1, max = 8)
    private String type;
    @NotNull
    @Size(min = 1, max = 8)
    private String code;
    @NotNull
    @Size(min = 1, max = 128)
    private String literal;

    public SystemLookup() {
    }

    public SystemLookup(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the literal
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * @param literal the literal to set
     */
    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SystemLookup)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        SystemLookup rhs = (SystemLookup) obj;
        return new EqualsBuilder().append(type, rhs.type).append(code, rhs.code).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(type).append(code).append(id).toHashCode();
    }
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new SystemLookup().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countSystemLookups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SystemLookup o", Long.class).getSingleResult();
    }

    public static List<SystemLookup> findAllSystemLookups() {
        return entityManager().createQuery("SELECT o FROM SystemLookup o", SystemLookup.class).getResultList();
    }

    public static SystemLookup findSystemLookup(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(SystemLookup.class, id);
    }

    public static List<SystemLookup> findSystemLookupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SystemLookup o", SystemLookup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static long countByType(String type) {
        TypedQuery<Long> q = entityManager().createQuery("SELECT COUNT(o) FROM SystemLookup o WHERE o.type=:type", Long.class);
        q.setParameter("type", type);
        return q.getResultList().get(0);
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
            SystemLookup attached = SystemLookup.findSystemLookup(this.id);
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
    public SystemLookup merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        SystemLookup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public static List<SystemLookup> findAllSystemLookupByType(String type) {
        if (type == null || type.length() == 0) {
            throw new IllegalArgumentException("The type argument is required");
        }
        EntityManager em = SystemLookup.entityManager();
        TypedQuery<SystemLookup> q = em.createQuery("SELECT o FROM SystemLookup AS o WHERE o.type = :type", SystemLookup.class);
        q.setParameter("type", type);
        return q.getResultList();
    }

    public static SystemLookup findSystemLookup(String type, String code) {
        if ((type == null || type.length() == 0) && (code == null || code.length() == 0)) {
            throw new IllegalArgumentException("The type and code argument is required");
        }
        EntityManager em = SystemLookup.entityManager();
        TypedQuery<SystemLookup> q = em.createQuery("SELECT o FROM SystemLookup AS o WHERE o.type = :type AND o.code = :code", SystemLookup.class);
        q.setParameter("type", type);
        q.setParameter("code", code);
        return q.getSingleResult();
    }
}
