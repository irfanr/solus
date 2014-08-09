package com.mascova.qeela.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Country extends ValueObjectImpl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_seq")
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 100)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;
    @NotNull
    @Size(min = 1, max = 15)
    private String code;
    @NotNull
    @Size(min = 3, max = 80)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Province> provinces = new HashSet<Province>();
    private static final long serialVersionUID = 1L;

    public boolean equals(Object obj) {
        if (!(obj instanceof Country)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Country rhs = (Country) obj;
        return new EqualsBuilder().append(code, rhs.code).append(id, rhs.id).append(name, rhs.name).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(code).append(id).append(name).toHashCode();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Country().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    // BEGIN JPQL/Criteria    
    public static long countCountrys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Country o", Long.class).getSingleResult();
    }

    public static List<Country> findAllCountrys() {
        return entityManager().createQuery("SELECT o FROM Country o", Country.class).getResultList();
    }

    public static Country findByName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        EntityManager em = Country.entityManager();
        TypedQuery<Country> q = em.createQuery("SELECT o FROM Country AS o WHERE o.name1 = :name1", Country.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    public static Country findCountry(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Country.class, id);
    }

    public static List<Country> findCountryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Country o", Country.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Country> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        EntityManager em = entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> p = cb.createQuery(Country.class);

        Root<Country> countryRoot = p.from(Country.class);

        p.select(countryRoot);

        // BEGIN Order
        if (StringUtils.isNotBlank(sortField)) {
            Order order = null;
            if (sortOrder == SortOrder.ASCENDING) {
                order = cb.asc(countryRoot.get(sortField));
            } else if (sortOrder == SortOrder.DESCENDING) {
                order = cb.desc(countryRoot.get(sortField));
            }
            p.orderBy(order);
        }
        // END Order  

        // BEGIN Filter
        Predicate criteria = cb.conjunction();

        if (!filters.isEmpty()) {

            ParameterExpression<String> peString = null;
            for (Map.Entry<String, String> filter : filters.entrySet()) {
//                System.out.println("Key : " + filter.getKey() + " Value : "
//                        + filter.getValue());

                switch (filter.getKey()) {
                    case "code":
                        peString =
                                cb.parameter(String.class, "code");
                        criteria = cb.and(
                                criteria,
                                cb.like(countryRoot.<String>get("code"), peString));
                        break;
                    case "name":
                        peString =
                                cb.parameter(String.class, "name");
                        criteria = cb.and(
                                criteria,
                                cb.like(countryRoot.<String>get("name"), peString));
                        break;
                    default:
                        ;
                        break;
                }
            }

        }
        // END Filter

        // active only
//        ParameterExpression<String> peActive =
//                cb.parameter(String.class, "active");
//        criteria = cb.and(
//                criteria,
//                cb.equal(countryRoot.<String>get("active"), peActive));

        p.where(criteria);

        TypedQuery<Country> q = em.createQuery(p)
                .setFirstResult(first).setMaxResults(pageSize);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), "%" + filter.getValue() + "%");
        }
//        q.setParameter(peActive, Login.STATUS_ACTIVE);
        return q.getResultList();
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
            Country attached = Country.findCountry(this.id);
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
    public Country merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Country merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    // END Active Record     
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Province> getProvinces() {
        return this.provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }
}
