package com.mascova.qeela.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Person extends ValueObjectImpl implements Serializable {

    @NotNull
    @Size(min = 3, max = 40)
    private String passportNo;
    @NotNull
    @Size(min = 3, max = 40)
    private String npwp;
    @NotNull
    @Size(min = 3, max = 20)
    private String name1;
    @Size(min = 3, max = 20)
    private String name2;
    @Size(min = 3, max = 20)
    private String name3;
    @Size(min = 3, max = 20)
    private String name4;
    @NotNull
    @Size(min = 3, max = 80)
    private String address;
    @NotNull
    @Size(min = 1, max = 1)
    private String gender;
    @NotNull
    @Size(min = 3, max = 20)
    private String placeOfBirth;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dob;
    @NotNull
    @Size(min = 3, max = 40)
    private String motherMaiden;
    @Lob
    private byte[] picture;
    @NotNull
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modified;
    @NotNull
    @ManyToOne
    private Village village;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Address> addresses = new HashSet<Address>();

    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Person rhs = (Person) obj;
        return new EqualsBuilder().append(address, rhs.address).append(created, rhs.created).append(dob, rhs.dob).append(gender, rhs.gender).append(id, rhs.id).append(modified, rhs.modified).append(motherMaiden, rhs.motherMaiden).append(name1, rhs.name1).append(name2, rhs.name2).append(name3, rhs.name3).append(name4, rhs.name4).append(npwp, rhs.npwp).append(passportNo, rhs.passportNo).append(placeOfBirth, rhs.placeOfBirth).append(village, rhs.village).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(address).append(created).append(dob).append(gender).append(id).append(modified).append(motherMaiden).append(name1).append(name2).append(name3).append(name4).append(npwp).append(passportNo).append(placeOfBirth).append(village).toHashCode();
    }
    private static final long serialVersionUID = 1L;

    public String getPassportNo() {
        return this.passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getNpwp() {
        return this.npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getName1() {
        return this.name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return this.name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return this.name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMotherMaiden() {
        return this.motherMaiden;
    }

    public void setMotherMaiden(String motherMaiden) {
        this.motherMaiden = motherMaiden;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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

    public Village getVillage() {
        return this.village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 100)
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

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static TypedQuery<Person> findPeopleByAddressEquals(String address) {
        if (address == null || address.length() == 0) {
            throw new IllegalArgumentException("The address argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.address = :address", Person.class);
        q.setParameter("address", address);
        return q;
    }

    public static TypedQuery<Person> findPeopleByAddressIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.address IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByAddressIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.address IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByAddressLike(String address) {
        if (address == null || address.length() == 0) {
            throw new IllegalArgumentException("The address argument is required");
        }
        address = address.replace('*', '%');
        if (address.charAt(0) != '%') {
            address = "%" + address;
        }
        if (address.charAt(address.length() - 1) != '%') {
            address = address + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.address) LIKE LOWER(:address)", Person.class);
        q.setParameter("address", address);
        return q;
    }

    public static TypedQuery<Person> findPeopleByAddressNotEquals(String address) {
        if (address == null || address.length() == 0) {
            throw new IllegalArgumentException("The address argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.address != :address", Person.class);
        q.setParameter("address", address);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedBetween(Date minCreated, Date maxCreated) {
        if (minCreated == null) {
            throw new IllegalArgumentException("The minCreated argument is required");
        }
        if (maxCreated == null) {
            throw new IllegalArgumentException("The maxCreated argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created BETWEEN :minCreated AND :maxCreated", Person.class);
        q.setParameter("minCreated", minCreated);
        q.setParameter("maxCreated", maxCreated);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedEquals(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created = :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedGreaterThan(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created > :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedGreaterThanEquals(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created >= :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedLessThan(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created < :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedLessThanEquals(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created <= :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByCreatedNotEquals(Date created) {
        if (created == null) {
            throw new IllegalArgumentException("The created argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.created != :created", Person.class);
        q.setParameter("created", created);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobBetween(Date minDob, Date maxDob) {
        if (minDob == null) {
            throw new IllegalArgumentException("The minDob argument is required");
        }
        if (maxDob == null) {
            throw new IllegalArgumentException("The maxDob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob BETWEEN :minDob AND :maxDob", Person.class);
        q.setParameter("minDob", minDob);
        q.setParameter("maxDob", maxDob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobEquals(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob = :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobGreaterThan(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob > :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobGreaterThanEquals(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob >= :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobLessThan(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob < :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobLessThanEquals(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob <= :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByDobNotEquals(Date dob) {
        if (dob == null) {
            throw new IllegalArgumentException("The dob argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.dob != :dob", Person.class);
        q.setParameter("dob", dob);
        return q;
    }

    public static TypedQuery<Person> findPeopleByGenderEquals(String gender) {
        if (gender == null || gender.length() == 0) {
            throw new IllegalArgumentException("The gender argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.gender = :gender", Person.class);
        q.setParameter("gender", gender);
        return q;
    }

    public static TypedQuery<Person> findPeopleByGenderIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.gender IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByGenderIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.gender IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByGenderLike(String gender) {
        if (gender == null || gender.length() == 0) {
            throw new IllegalArgumentException("The gender argument is required");
        }
        gender = gender.replace('*', '%');
        if (gender.charAt(0) != '%') {
            gender = "%" + gender;
        }
        if (gender.charAt(gender.length() - 1) != '%') {
            gender = gender + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.gender) LIKE LOWER(:gender)", Person.class);
        q.setParameter("gender", gender);
        return q;
    }

    public static TypedQuery<Person> findPeopleByGenderNotEquals(String gender) {
        if (gender == null || gender.length() == 0) {
            throw new IllegalArgumentException("The gender argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.gender != :gender", Person.class);
        q.setParameter("gender", gender);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedBetween(Date minModified, Date maxModified) {
        if (minModified == null) {
            throw new IllegalArgumentException("The minModified argument is required");
        }
        if (maxModified == null) {
            throw new IllegalArgumentException("The maxModified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified BETWEEN :minModified AND :maxModified", Person.class);
        q.setParameter("minModified", minModified);
        q.setParameter("maxModified", maxModified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedEquals(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified = :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedGreaterThan(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified > :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedGreaterThanEquals(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified >= :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedLessThan(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified < :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedLessThanEquals(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified <= :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByModifiedNotEquals(Date modified) {
        if (modified == null) {
            throw new IllegalArgumentException("The modified argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.modified != :modified", Person.class);
        q.setParameter("modified", modified);
        return q;
    }

    public static TypedQuery<Person> findPeopleByMotherMaidenEquals(String motherMaiden) {
        if (motherMaiden == null || motherMaiden.length() == 0) {
            throw new IllegalArgumentException("The motherMaiden argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.motherMaiden = :motherMaiden", Person.class);
        q.setParameter("motherMaiden", motherMaiden);
        return q;
    }

    public static TypedQuery<Person> findPeopleByMotherMaidenIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.motherMaiden IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByMotherMaidenIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.motherMaiden IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByMotherMaidenLike(String motherMaiden) {
        if (motherMaiden == null || motherMaiden.length() == 0) {
            throw new IllegalArgumentException("The motherMaiden argument is required");
        }
        motherMaiden = motherMaiden.replace('*', '%');
        if (motherMaiden.charAt(0) != '%') {
            motherMaiden = "%" + motherMaiden;
        }
        if (motherMaiden.charAt(motherMaiden.length() - 1) != '%') {
            motherMaiden = motherMaiden + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.motherMaiden) LIKE LOWER(:motherMaiden)", Person.class);
        q.setParameter("motherMaiden", motherMaiden);
        return q;
    }

    public static TypedQuery<Person> findPeopleByMotherMaidenNotEquals(String motherMaiden) {
        if (motherMaiden == null || motherMaiden.length() == 0) {
            throw new IllegalArgumentException("The motherMaiden argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.motherMaiden != :motherMaiden", Person.class);
        q.setParameter("motherMaiden", motherMaiden);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName1Equals(String name1) {
        if (name1 == null || name1.length() == 0) {
            throw new IllegalArgumentException("The name1 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name1 = :name1", Person.class);
        q.setParameter("name1", name1);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName1IsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name1 IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName1IsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name1 IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName1Like(String name1) {
        if (name1 == null || name1.length() == 0) {
            throw new IllegalArgumentException("The name1 argument is required");
        }
        name1 = name1.replace('*', '%');
        if (name1.charAt(0) != '%') {
            name1 = "%" + name1;
        }
        if (name1.charAt(name1.length() - 1) != '%') {
            name1 = name1 + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.name1) LIKE LOWER(:name1)", Person.class);
        q.setParameter("name1", name1);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName1NotEquals(String name1) {
        if (name1 == null || name1.length() == 0) {
            throw new IllegalArgumentException("The name1 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name1 != :name1", Person.class);
        q.setParameter("name1", name1);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName2Equals(String name2) {
        if (name2 == null || name2.length() == 0) {
            throw new IllegalArgumentException("The name2 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name2 = :name2", Person.class);
        q.setParameter("name2", name2);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName2IsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name2 IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName2IsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name2 IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName2Like(String name2) {
        if (name2 == null || name2.length() == 0) {
            throw new IllegalArgumentException("The name2 argument is required");
        }
        name2 = name2.replace('*', '%');
        if (name2.charAt(0) != '%') {
            name2 = "%" + name2;
        }
        if (name2.charAt(name2.length() - 1) != '%') {
            name2 = name2 + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.name2) LIKE LOWER(:name2)", Person.class);
        q.setParameter("name2", name2);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName2NotEquals(String name2) {
        if (name2 == null || name2.length() == 0) {
            throw new IllegalArgumentException("The name2 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name2 != :name2", Person.class);
        q.setParameter("name2", name2);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName3Equals(String name3) {
        if (name3 == null || name3.length() == 0) {
            throw new IllegalArgumentException("The name3 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name3 = :name3", Person.class);
        q.setParameter("name3", name3);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName3IsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name3 IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName3IsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name3 IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName3Like(String name3) {
        if (name3 == null || name3.length() == 0) {
            throw new IllegalArgumentException("The name3 argument is required");
        }
        name3 = name3.replace('*', '%');
        if (name3.charAt(0) != '%') {
            name3 = "%" + name3;
        }
        if (name3.charAt(name3.length() - 1) != '%') {
            name3 = name3 + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.name3) LIKE LOWER(:name3)", Person.class);
        q.setParameter("name3", name3);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName3NotEquals(String name3) {
        if (name3 == null || name3.length() == 0) {
            throw new IllegalArgumentException("The name3 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name3 != :name3", Person.class);
        q.setParameter("name3", name3);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName4Equals(String name4) {
        if (name4 == null || name4.length() == 0) {
            throw new IllegalArgumentException("The name4 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name4 = :name4", Person.class);
        q.setParameter("name4", name4);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName4IsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name4 IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName4IsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name4 IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName4Like(String name4) {
        if (name4 == null || name4.length() == 0) {
            throw new IllegalArgumentException("The name4 argument is required");
        }
        name4 = name4.replace('*', '%');
        if (name4.charAt(0) != '%') {
            name4 = "%" + name4;
        }
        if (name4.charAt(name4.length() - 1) != '%') {
            name4 = name4 + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.name4) LIKE LOWER(:name4)", Person.class);
        q.setParameter("name4", name4);
        return q;
    }

    public static TypedQuery<Person> findPeopleByName4NotEquals(String name4) {
        if (name4 == null || name4.length() == 0) {
            throw new IllegalArgumentException("The name4 argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.name4 != :name4", Person.class);
        q.setParameter("name4", name4);
        return q;
    }

    public static TypedQuery<Person> findPeopleByNpwpEquals(String npwp) {
        if (npwp == null || npwp.length() == 0) {
            throw new IllegalArgumentException("The npwp argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.npwp = :npwp", Person.class);
        q.setParameter("npwp", npwp);
        return q;
    }

    public static TypedQuery<Person> findPeopleByNpwpIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.npwp IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByNpwpIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.npwp IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByNpwpLike(String npwp) {
        if (npwp == null || npwp.length() == 0) {
            throw new IllegalArgumentException("The npwp argument is required");
        }
        npwp = npwp.replace('*', '%');
        if (npwp.charAt(0) != '%') {
            npwp = "%" + npwp;
        }
        if (npwp.charAt(npwp.length() - 1) != '%') {
            npwp = npwp + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.npwp) LIKE LOWER(:npwp)", Person.class);
        q.setParameter("npwp", npwp);
        return q;
    }

    public static TypedQuery<Person> findPeopleByNpwpNotEquals(String npwp) {
        if (npwp == null || npwp.length() == 0) {
            throw new IllegalArgumentException("The npwp argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.npwp != :npwp", Person.class);
        q.setParameter("npwp", npwp);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPassportNoEquals(String passportNo) {
        if (passportNo == null || passportNo.length() == 0) {
            throw new IllegalArgumentException("The passportNo argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.passportNo = :passportNo", Person.class);
        q.setParameter("passportNo", passportNo);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPassportNoIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.passportNo IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPassportNoIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.passportNo IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPassportNoLike(String passportNo) {
        if (passportNo == null || passportNo.length() == 0) {
            throw new IllegalArgumentException("The passportNo argument is required");
        }
        passportNo = passportNo.replace('*', '%');
        if (passportNo.charAt(0) != '%') {
            passportNo = "%" + passportNo;
        }
        if (passportNo.charAt(passportNo.length() - 1) != '%') {
            passportNo = passportNo + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.passportNo) LIKE LOWER(:passportNo)", Person.class);
        q.setParameter("passportNo", passportNo);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPassportNoNotEquals(String passportNo) {
        if (passportNo == null || passportNo.length() == 0) {
            throw new IllegalArgumentException("The passportNo argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.passportNo != :passportNo", Person.class);
        q.setParameter("passportNo", passportNo);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPicture(byte[] picture) {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.picture = :picture", Person.class);
        q.setParameter("picture", picture);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPlaceOfBirthEquals(String placeOfBirth) {
        if (placeOfBirth == null || placeOfBirth.length() == 0) {
            throw new IllegalArgumentException("The placeOfBirth argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.placeOfBirth = :placeOfBirth", Person.class);
        q.setParameter("placeOfBirth", placeOfBirth);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPlaceOfBirthIsNotNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.placeOfBirth IS NOT NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPlaceOfBirthIsNull() {
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.placeOfBirth IS NULL", Person.class);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPlaceOfBirthLike(String placeOfBirth) {
        if (placeOfBirth == null || placeOfBirth.length() == 0) {
            throw new IllegalArgumentException("The placeOfBirth argument is required");
        }
        placeOfBirth = placeOfBirth.replace('*', '%');
        if (placeOfBirth.charAt(0) != '%') {
            placeOfBirth = "%" + placeOfBirth;
        }
        if (placeOfBirth.charAt(placeOfBirth.length() - 1) != '%') {
            placeOfBirth = placeOfBirth + "%";
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.placeOfBirth) LIKE LOWER(:placeOfBirth)", Person.class);
        q.setParameter("placeOfBirth", placeOfBirth);
        return q;
    }

    public static TypedQuery<Person> findPeopleByPlaceOfBirthNotEquals(String placeOfBirth) {
        if (placeOfBirth == null || placeOfBirth.length() == 0) {
            throw new IllegalArgumentException("The placeOfBirth argument is required");
        }
        EntityManager em = Person.entityManager();
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE o.placeOfBirth != :placeOfBirth", Person.class);
        q.setParameter("placeOfBirth", placeOfBirth);
        return q;
    }

    public static List<Person> findEntries(
            int first,
            int pageSize,
            String sortField,
            SortOrder sortOrder,
            Map<String, String> filters) {

        EntityManager em = Person.entityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> p = cb.createQuery(Person.class);

        Root<Person> prsn = p.from(Person.class);

        p.select(prsn);

        // BEGIN Order
        if (StringUtils.isNotBlank(sortField)) {
            Order order = null;
            if (sortOrder == SortOrder.ASCENDING) {
                order = cb.asc(prsn.get(sortField));
            } else if (sortOrder == SortOrder.DESCENDING) {
                order = cb.desc(prsn.get(sortField));
            }
            p.orderBy(order);
        }
        // END Order  

        // BEGIN Filter
        Predicate criteria = null;

        if (!filters.isEmpty()) {
            criteria = cb.conjunction();

            for (Map.Entry<String, String> filter : filters.entrySet()) {
//                System.out.println("Key : " + filter.getKey() + " Value : "
//                        + filter.getValue());

                ParameterExpression<String> peString = null;
                switch (filter.getKey()) {
                    case "name1":
                        peString =
                                cb.parameter(String.class, "name1");
                        criteria = cb.and(
                                criteria,
                                cb.like(prsn.<String>get("name1"), peString));
                        break;
                    case "name2":
                        peString =
                                cb.parameter(String.class, "name2");
                        criteria = cb.and(
                                criteria,
                                cb.like(prsn.<String>get("name2"), peString));
                        break;
                    case "name3":
                        peString =
                                cb.parameter(String.class, "name3");
                        criteria = cb.and(
                                criteria,
                                cb.like(prsn.<String>get("name3"), peString));
                        break;
                    case "name4":
                        peString =
                                cb.parameter(String.class, "name4");
                        criteria = cb.and(
                                criteria,
                                cb.like(prsn.<String>get("name4"), peString));
                        break;
                    default:
                        ;
                        break;
                }
            }

            p.where(criteria);
        }
        // END Filter

        TypedQuery<Person> q = em.createQuery(p)
                .setFirstResult(first).setMaxResults(pageSize);
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            q.setParameter(filter.getKey(), filter.getValue());
        }
        return q.getResultList();
    }
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Person().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public static long countPeople() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Person o", Long.class).getSingleResult();
    }

    public static List<Person> findAllPeople() {
        return entityManager().createQuery("SELECT o FROM Person o", Person.class).getResultList();
    }

    public static Person findPerson(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Person.class, id);
    }

    public static List<Person> findPersonEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Person o", Person.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Person attached = Person.findPerson(this.id);
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
    public Person merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Person merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
