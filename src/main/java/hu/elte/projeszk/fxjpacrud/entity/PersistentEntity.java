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
     * @return id, az azonosító
     */
    public Integer getId() {
        return id;
    }

    /**
     * azonosító érékének beállítása
     * @param id, az azonosító
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * absztrakt metódus ami a columnIndexedik elemmel tér vissza 
     * @param columnIndex
     * @return Object, az adott oszlop típusával tér vissza
     */
    public abstract Object get(int columnIndex);

    /**
     * absztrakt metódus ami a columnIndexedik elemet frissiti 
     * @param columnIndex
     * @param value, frissítő érték
     */
    public abstract void set(int columnIndex, Object value);

}
