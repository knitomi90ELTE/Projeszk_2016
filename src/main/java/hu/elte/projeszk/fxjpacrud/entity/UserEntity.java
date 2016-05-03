package hu.elte.projeszk.fxjpacrud.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * felhasználói entitás osztály
 * @author 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UserEntity.findByName", query = "SELECT u FROM UserEntity u WHERE u.name = :name"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByPassword", query = "SELECT u FROM UserEntity u WHERE u.password = :password")
})
public class UserEntity extends PersistentEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    /**
     * alapértelmezett konstruktor
     */
    public UserEntity() {
    }

    /**
     * egyparaméteres konstruktor
     * @param id, azonosító
     */
    public UserEntity(Integer id) {
        this.id = id;
    }

    /**
     * többparaméteres konstruktor
     * @param id, azonosító
     * @param name, név
     * @param email, email
     * @param password, jelszó
     */
    public UserEntity(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * felhasználó nevének lekérdezése
     * @return name, névvel tér vissza
     */
    public String getName() {
        return name;
    }

    /**
     * felhasználó nevének megadása
     * @param name, név paraméter
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * email értékének lekérdezése
     * @return email, email cím
     */
    public String getEmail() {
        return email;
    }

    /**
     * email értékének megadása
     * @param email, paraméterül adott email cím
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * jelszó értékének lekérdezése
     * @return password, felhasználóhoz tartozó jelszó
     */
    public String getPassword() {
        return password;
    }

    /**
     * jelszó értékének beállítása
     * @param password, paraméterül jelszó
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * heskódot generáló metódus
     * @return hash, haskóddal tér vissza  
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * két UserEntity típusú objektum egyenlőségét vizsgáló metódus
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }

    /**
     * adattag lekérdezés oszlopszám szerint
     * @param columnIndex
     * @return az adott columnIndex megfelelő adattagját adja vissza
     */
    @Override
    public Object get(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return id;
            case 1:
                return name;
            case 2:
                return email;
            case 3:
                return password;
            default:
                return null;
        }
    }

    /**
     * adattag beállítása oszlopszám szerint
     * @param columnIndex, oszlopindex
     * @param value, az adott oszlop értéke
     */
    @Override
    public void set(int columnIndex, Object value) {
        switch (columnIndex) {
            case 0:
                setId((Integer) value);
                break;
            case 1:
                setName((String) value);
                break;
            case 2:
                setEmail((String) value);
                break;
            case 3:
                setPassword((String) value);
                break;
        }
    }

}
