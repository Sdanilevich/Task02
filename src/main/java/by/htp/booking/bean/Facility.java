package by.htp.booking.bean;

import java.io.Serializable;
import java.util.Objects;

public class Facility extends AbstractBean implements Serializable {
    private int id;
    private String name;
    private String nameEn;

    public Facility(){}

    public Facility(String name){
        this.name = name;
    }
    public Facility(String name, String nameEn){
        this.name = name;
        this.nameEn = nameEn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return id == facility.id &&
                Objects.equals(name, facility.name)&&
                Objects.equals(nameEn, facility.nameEn);
    }

    @Override
    public int hashCode() {
        int rez = 1;
        rez = rez*15 + rez*id;
        rez = rez*15 + (name==null ? 0: name.hashCode()) *rez;
        rez = rez*15 + (nameEn==null ? 0: nameEn.hashCode()) *rez;
        return rez;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
