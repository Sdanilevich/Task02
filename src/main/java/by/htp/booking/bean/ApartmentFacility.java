package by.htp.booking.bean;

import java.io.Serializable;
import java.util.Objects;

public class ApartmentFacility extends AbstractBean implements Serializable {
    private int apartmentId;
    private int facilityId;

    public ApartmentFacility(){}
    public ApartmentFacility(int apartmentId, int facilityId){
        this.apartmentId = apartmentId;
        this.facilityId = facilityId;
    }

    public int getId() {
        String id = Integer.toString(apartmentId)+Integer.toString(facilityId);

        return Integer.valueOf(id);
    }

    public void setId(int id) {

    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int idApartment) {
        this.apartmentId = idApartment;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int idFacility) {
        this.facilityId = idFacility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentFacility that = (ApartmentFacility) o;
        return apartmentId == that.apartmentId &&
                facilityId == that.facilityId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(apartmentId, facilityId);
    }

}
