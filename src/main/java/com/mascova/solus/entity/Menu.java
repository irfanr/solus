package com.mascova.solus.entity;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openswing.swing.message.receive.java.ValueObjectImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Menu extends ValueObjectImpl implements Serializable {

    public static final Long MENU_ID_DATA_PESERTA = new Long(27);
    public static final Long MENU_ID_DATA_PELATIHAN = new Long(28);
    public static final Long MENU_ID_PELATIH = new Long(30);
    public static final Long MENU_ID_MITRA = new Long(31);
    public static final Long MENU_ID_KOORDINATOR = new Long(32);
    public static final Long MENU_ID_PENGATURAN_USER = new Long(9);
    public static final Long MENU_ID_PEKERJAAN = new Long(17);
    public static final Long MENU_ID_PENDIDIKAN = new Long(18);
    public static final Long MENU_ID_HUBUNGAN = new Long(19);
    public static final Long MENU_ID_POSISI = new Long(20);
    public static final Long MENU_ID_LAPORAN = new Long(6);
    public static final Long MENU_ID_MILES_DASHBOARD = new Long(11);
    public static final Long MENU_ID_ADMIN_DASHBOARD = new Long(12);
    public static final Long MENU_ID_KEBUTUHAN_SERTIFIKAT = new Long(33);
    public static final Long MENU_ID_DATA_BENEFICIARIES = new Long(34);
    @NotNull
    @Size(max = 40)
    private String name;
    @NotNull
    private Integer sequence;
    @NotNull
    private Boolean active;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<LoginRole> loginRoles = new HashSet<LoginRole>();
    @ManyToOne
    private Link link;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentMenu")
    @OrderBy(value = "sequence")
    private Set<com.mascova.solus.entity.Menu> menus = new HashSet<com.mascova.solus.entity.Menu>();
    @ManyToOne
    @JoinColumn(name = "parent_menu")
    private com.mascova.solus.entity.Menu parentMenu;
    @PersistenceContext
    transient EntityManager entityManager;

    public static final EntityManager entityManager() {
        EntityManager em = new Menu().entityManager;
        if (em == null) {
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        }
        return em;
    }

    public Menu() {
    }

    public Menu(Long id) {
        this.id = id;
    }

    public static long countMenus() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Menu o", Long.class).getSingleResult();
    }

    public static List<Menu> findAllMenus() {
        return entityManager().createQuery("SELECT o FROM Menu o", Menu.class).getResultList();
    }

    public static Menu findMenu(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager().find(Menu.class, id);
    }

    public static List<Menu> findMenuEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Menu o", Menu.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Menu attached = Menu.findMenu(this.id);
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
    public Menu merge() {
        if (this.entityManager == null) {
            this.entityManager = entityManager();
        }
        Menu merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<LoginRole> getLoginRoles() {
        return this.loginRoles;
    }

    public void setLoginRoles(Set<LoginRole> loginRoles) {
        this.loginRoles = loginRoles;
    }

    public Link getLink() {
        return this.link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Set<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Menu getParentMenu() {
        return this.parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_seq")
    @SequenceGenerator(name = "menu_id_seq", sequenceName = "menu_id_seq", allocationSize = 100)
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
    private static final long serialVersionUID = 1L;

    public static TypedQuery<Menu> findMenusByLink(Link link) {
        if (link == null) {
            throw new IllegalArgumentException("The link argument is required");
        }
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.link = :link", Menu.class);
        q.setParameter("link", link);
        return q;
    }

    public static TypedQuery<Menu> findMenusByLoginRoles(Set<LoginRole> loginRoles) {
        if (loginRoles == null) {
            throw new IllegalArgumentException("The loginRoles argument is required");
        }
        EntityManager em = Menu.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Menu AS o WHERE");
        for (int i = 0; i < loginRoles.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" :loginRoles_item").append(i).append(" MEMBER OF o.loginRoles");
        }
        TypedQuery<Menu> q = em.createQuery(queryBuilder.toString(), Menu.class);
        int loginRolesIndex = 0;
        for (LoginRole _loginrole : loginRoles) {
            q.setParameter("loginRoles_item" + loginRolesIndex++, _loginrole);
        }
        return q;
    }

    public static TypedQuery<Menu> findMenusByMenus(Set<Menu> menus) {
        if (menus == null) {
            throw new IllegalArgumentException("The menus argument is required");
        }
        EntityManager em = Menu.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Menu AS o WHERE");
        for (int i = 0; i < menus.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" AND");
            }
            queryBuilder.append(" :menus_item").append(i).append(" MEMBER OF o.menus");
        }
        TypedQuery<Menu> q = em.createQuery(queryBuilder.toString(), Menu.class);
        int menusIndex = 0;
        for (Menu _menu : menus) {
            q.setParameter("menus_item" + menusIndex++, _menu);
        }
        return q;
    }

    public static TypedQuery<Menu> findMenusByNameEquals(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.name = :name", Menu.class);
        q.setParameter("name", name);
        return q;
    }

    public static TypedQuery<Menu> findMenusByNameIsNotNull() {
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.name IS NOT NULL", Menu.class);
        return q;
    }

    public static TypedQuery<Menu> findMenusByNameIsNull() {
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.name IS NULL", Menu.class);
        return q;
    }

    public static TypedQuery<Menu> findMenusByNameLike(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Menu.class);
        q.setParameter("name", name);
        return q;
    }

    public static TypedQuery<Menu> findMenusByNameNotEquals(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("The name argument is required");
        }
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.name != :name", Menu.class);
        q.setParameter("name", name);
        return q;
    }

    public static TypedQuery<Menu> findMenusByParentMenu(Menu parentMenu) {
        if (parentMenu == null) {
            throw new IllegalArgumentException("The parentMenu argument is required");
        }
        EntityManager em = Menu.entityManager();
        TypedQuery<Menu> q = em.createQuery("SELECT o FROM Menu AS o WHERE o.parentMenu = :parentMenu", Menu.class);
        q.setParameter("parentMenu", parentMenu);
        return q;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
