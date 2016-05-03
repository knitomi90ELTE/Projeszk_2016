package hu.elte.projeszk.fxjpacrud.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * entitás osztály
 * @author 
 */
@MappedSuperclass
public abstract class PersistentEntity implements Serializable {

    /**
     * azonosító adattag
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * azonosító érékének lekérdezése
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * azonosító érékének beállítása
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @param columnIndex
     * @return
     */
    public abstract Object get(int columnIndex);

    /**
     * 
     * @param columnIndex
     * @param value
     */
    public abstract void set(int columnIndex, Object value);

}
