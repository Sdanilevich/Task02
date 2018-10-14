package by.htp.booking.bean;

import java.io.Serializable;
import java.util.Objects;

public class City extends AbstractBean implements Serializable {
    private int id;
    private String name;
    private String nameEn;
    private int countryId;

    public City(){}

    public City(String name){
        this.name = name;
    }

    public City(String name, String nameEn){
        this.name = name;
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null){
            return  false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        City city = (City) o;
        if (id != city.id){
            return false;
        }
        if (countryId != city.countryId){
            return false;
        }
        if (!name.equals(city.name)){
            return false;
        }

        if (!nameEn.equals(city.nameEn)){
            return false;
        }
        return  true;
    }

    @Override
    public int hashCode() {
        int rez = 1;
        rez = rez*15 + rez*id;
        rez = rez*15 + (name==null ? 0: name.hashCode()) *rez;
        rez = rez*15 + (nameEn==null ? 0: nameEn.hashCode()) *rez;
        rez = rez*15 + rez*countryId;
        return rez;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
