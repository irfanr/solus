/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@Configurable
@Entity
public class Employee extends ValueObjectImpl implements Serializable {

    @PersistenceContext
    transient EntityManager entityManager;       
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
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
    @Size(min = 3, max = 40)
    private String passportNo;
    @NotNull
    @Size(min = 3, max = 40)
    private String tin;
    @NotNull
    @Size(min = 3, max = 80)
    private String name;
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
    @Size(min = 3, max = 40)
    private String motherMaiden;
    @Lob
    private byte[] picture;
    @NotNull
    @ManyToOne
    private Village village;
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "login")
    private Login login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private Set<Sales> salesList;

    public Employee() {
    }

    public Employee(String passportNo, String tin, String name, String address,
            String gender, String placeOfBirth, Date dob, Village village, Login login) {
        this.passportNo = passportNo;
        this.tin = tin;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.dob = dob;
        this.village = village;
        this.login = login;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Employee rhs = (Employee) obj;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
    // BEGIN Active Record    
    public static final EntityManager entityManager() {
        EntityManager em = new Employee().entityManager;
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
            Employee attached = Employee.find(this.id);
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
    public Employee merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Employee merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static Employee find(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Employee.class, id);
    }    

    // END Active Record      
    
    // START JPQL
    
    public static Employee findByName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        EntityManager em = Village.entityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT o FROM Employee AS o WHERE o.name = :name", Employee.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }    
    
    // END JPQL    

    public Long getId() {
        return id;
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
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return the passportNo
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @param passportNo the passportNo to set
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @return the tin
     */
    public String getTin() {
        return tin;
    }

    /**
     * @param tin the tin to set
     */
    public void setTin(String tin) {
        this.tin = tin;
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

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the placeOfBirth
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * @param placeOfBirth the placeOfBirth to set
     */
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the motherMaiden
     */
    public String getMotherMaiden() {
        return motherMaiden;
    }

    /**
     * @param motherMaiden the motherMaiden to set
     */
    public void setMotherMaiden(String motherMaiden) {
        this.motherMaiden = motherMaiden;
    }

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * @return the village
     */
    public Village getVillage() {
        return village;
    }

    /**
     * @param village the village to set
     */
    public void setVillage(Village village) {
        this.village = village;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * @return the salesList
     */
    public Set<Sales> getSalesList() {
        return salesList;
    }

    /**
     * @param salesList the salesList to set
     */
    public void setSalesList(Set<Sales> salesList) {
        this.salesList = salesList;
    }
}
