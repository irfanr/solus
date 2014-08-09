package com.mascova.solus.entity;

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

@Configurable
@Entity
public class Link extends ValueObjectImpl implements Serializable {

    @NotNull
    @Size(max = 256)
    private String linkPath;
    @Size(max = 256)    
    private String iconPath;

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getLinkPath() {
        return this.linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_id_seq")
    @SequenceGenerator(name = "link_id_seq", sequenceName = "link_id_seq", allocationSize = 100)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;

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

    public boolean equals(Object obj) {
        if (!(obj instanceof Link)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Link rhs = (Link) obj;
        return new EqualsBuilder().append(id, rhs.id).append(linkPath, rhs.linkPath).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(id).append(linkPath).toHashCode();
    }
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Link().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countLinks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Link o", Long.class).getSingleResult();
    }

    public static List<Link> findAllLinks() {
        return entityManager().createQuery("SELECT o FROM Link o", Link.class).getResultList();
    }

    public static Link findLink(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Link.class, id);
    }

    public static List<Link> findLinkEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Link o", Link.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Link attached = Link.findLink(this.id);
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
    public Link merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Link merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
