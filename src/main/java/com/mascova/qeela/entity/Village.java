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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class Village extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "village_id_seq")
    @SequenceGenerator(name = "village_id_seq", sequenceName = "village_id_seq", allocationSize = 1)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "village")
    private Set<Address> addresses = new HashSet<Address>();
    @NotNull
    @ManyToOne
    private SubDistrict subDistrict;

    public boolean equals(Object obj) {
        if (!(obj instanceof Village)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Village rhs = (Village) obj;
        return new EqualsBuilder().append(code, rhs.code).append(id, rhs.id).append(name, rhs.name).append(subDistrict, rhs.subDistrict).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(code).append(id).append(name).append(subDistrict).toHashCode();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Village().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    // Begin JPQL/Criteria      
    public static long countEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        EntityManager em = entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> p = cb.createQuery(Long.class);

        Root<Village> villageRoot = p.from(Village.class);

        p.select(cb.count(villageRoot));

        Join<Village, SubDistrict> subDistrictJoin = villageRoot.join("subDistrict");
        Join<SubDistrict, District> districtJoin = subDistrictJoin.join("district");
        Join<District, Province> provinceJoin = districtJoin.join("province");

        // BEGIN Filter
        Predicate criteria = cb.conjunction();

        if (!filters.isEmpty()) {

            ParameterExpression<String> peString = null;
            for (Map.Entry<String, String> filter : filters.entrySet()) {
//                System.out.println("Key : " + filter.getKey() + " Value : "
//                        + filter.getValue());

                switch (filter.getKey()) {
                    case "name":
                        peString =
                                cb.parameter(String.class, "name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(villageRoot.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(subDistrictJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(districtJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.province.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.province.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(provinceJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    default:
                        ;
                        break;
                }
            }

        }
        // END Filter

        p.where(criteria);

        TypedQuery<Long> q = em.createQuery(p);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), "%" + filter.getValue() + "%");
        }
        return q.getSingleResult();
    }

    public static Village findByName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        EntityManager em = Village.entityManager();
        TypedQuery<Village> q = em.createQuery("SELECT o FROM Village AS o WHERE o.name = :name", Village.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    public static long findEntries(Map<String, String> filters) {

        EntityManager em = entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> p = cb.createQuery(Long.class);

        Root<Village> villageRoot = p.from(Village.class);

        p.select(cb.count(villageRoot));

        Join<Village, SubDistrict> subDistrictJoin = villageRoot.join("subDistrict");
        Join<SubDistrict, District> districtJoin = subDistrictJoin.join("district");
        Join<District, Province> provinceJoin = districtJoin.join("province");

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
                                cb.like(cb.upper(villageRoot.<String>get("code")), cb.upper(peString)));
                        break;
                    case "name":
                        peString =
                                cb.parameter(String.class, "name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(villageRoot.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(subDistrictJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(districtJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.province.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.province.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(provinceJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    default:
                        ;
                        break;
                }
            }

        }
        // END Filter

        p.where(criteria);

        TypedQuery<Long> q = em.createQuery(p);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), "%" + filter.getValue() + "%");
        }
        return q.getSingleResult();
    }

    public static long countVillages() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Village o", Long.class).getSingleResult();
    }

    public static List<Village> findAllVillages() {
        return entityManager().createQuery("SELECT o FROM Village o", Village.class).getResultList();
    }

    public static Village findVillage(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Village.class, id);
    }

    public static List<Village> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

        EntityManager em = entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Village> p = cb.createQuery(Village.class);

        Root<Village> villageRoot = p.from(Village.class);

        p.select(villageRoot);

        Join<Village, SubDistrict> subDistrictJoin = villageRoot.join("subDistrict");
        Join<SubDistrict, District> districtJoin = subDistrictJoin.join("district");
        Join<District, Province> provinceJoin = districtJoin.join("province");

        // BEGIN Order
        if (StringUtils.isNotBlank(sortField)) {
            Order order = null;
            if (sortOrder == SortOrder.ASCENDING) {
                if (StringUtils.equals(sortField, "subDistrict.name")) {
                    order = cb.asc(subDistrictJoin.<String>get("name"));
                } else if (StringUtils.equals(sortField, "subDistrict.district.name")) {
                    order = cb.asc(districtJoin.<String>get("name"));
                } else if (StringUtils.equals(sortField, "subDistrict.district.province.name")) {
                    order = cb.asc(provinceJoin.<String>get("name"));
                } else {
                    order = cb.asc(villageRoot.get(sortField));
                }
            } else if (sortOrder == SortOrder.DESCENDING) {
                if (StringUtils.equals(sortField, "subDistrict.name")) {
                    order = cb.desc(subDistrictJoin.<String>get("name"));
                } else if (StringUtils.equals(sortField, "subDistrict.district.name")) {
                    order = cb.desc(districtJoin.<String>get("name"));
                } else if (StringUtils.equals(sortField, "subDistrict.district.province.name")) {
                    order = cb.desc(provinceJoin.<String>get("name"));
                } else {
                    order = cb.desc(villageRoot.get(sortField));
                }
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
                                cb.like(cb.upper(villageRoot.<String>get("code")), cb.upper(peString)));
                        break;
                    case "name":
                        peString =
                                cb.parameter(String.class, "name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(villageRoot.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(subDistrictJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(districtJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    case "subDistrict.district.province.name":
                        peString =
                                cb.parameter(String.class, "subDistrict.district.province.name");
                        criteria = cb.and(
                                criteria,
                                cb.like(cb.upper(provinceJoin.<String>get("name")), cb.upper(peString)));
                        break;
                    default:
                        ;
                        break;
                }
            }

        }
        // END Filter

        p.where(criteria);

        TypedQuery<Village> q = em.createQuery(p)
                .setFirstResult(first).setMaxResults(pageSize);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), "%" + filter.getValue() + "%");
        }
        return q.getResultList();
    }

    public static List<Village> findVillageEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Village o", Village.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Village attached = Village.findVillage(this.id);
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
    public Village merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Village merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    // END Active Record       

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

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public SubDistrict getSubDistrict() {
        return this.subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
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
}
