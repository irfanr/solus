/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
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
public class Sales extends ValueObjectImpl implements Serializable {

    @PersistenceContext
    transient EntityManager entityManager;       
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_id_seq")
    @SequenceGenerator(name = "sales_id_seq", sequenceName = "sales_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Version
    @Column(name = "version")
    private Integer version;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modified;
    @NotNull
    private int amount;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date purchaseDate;
    @NotNull
    @ManyToOne
    private Customer customer;
    @NotNull
    @ManyToOne
    private Employee seller;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> productList;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Service> serviceList;

    public Sales() {
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sales)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Sales rhs = (Sales) obj;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
    // BEGIN Active Record    
    public static final EntityManager entityManager() {
        EntityManager em = new Sales().entityManager;
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
            Sales attached = Sales.find(this.id);
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
    public Sales merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Sales merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static Sales find(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Sales.class, id);
    }    

    // END Active Record      

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
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the seller
     */
    public Employee getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Employee seller) {
        this.seller = seller;
    }

    /**
     * @return the productList
     */
    public Set<Product> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    /**
     * @return the serviceList
     */
    public Set<Service> getServiceList() {
        return serviceList;
    }

    /**
     * @param serviceList the serviceList to set
     */
    public void setServiceList(Set<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
