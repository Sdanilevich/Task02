package by.htp.booking.bean;

import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.ApartmentFacilityService;
import by.htp.booking.service.impl.HotelService;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Apartment extends AbstractBean implements Serializable {

    private int id;
    private String name;
    private String nameEn;
    private String description;
    private String descriptionEn;
    private double roomSize;
    private int countGuest;
    private int hotelId;
    private double price;


    HotelService hotelService = ActionFactory.hotelService;

    ApartmentFacilityService apartmentFacilityService = ActionFactory.apartmentFacilityService;

    public Apartment(){}
    public Apartment(String name, String description,
                     int countGuest, double roomSize, double price){
        this.name = name;
        this.description = description;
        this.countGuest = countGuest;
        this.roomSize = roomSize;
        this.price = price;
    }


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public int getCountGuest() {
        return countGuest;
    }

    public void setCountGuest(int countGuest) {
        this.countGuest = countGuest;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress(){
        if (hotelId!=0) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            if (hotel != null) {
                return hotel.getAddress();
            }
            return "";
        }
        return "";
    }

    public String getAddressEn(){
        if (hotelId!=0) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            if (hotel != null) {
                return hotel.getAddressEn();
            }
            return "";
        }
        return "";
    }

    public String getHotelName(){
        if (hotelId!=0) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            if (hotel != null) {
                return hotel.getName();
            }
            return "";
        }
        return "";
    }

    public String getHotelNameEn(){
        if (hotelId!=0) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            if (hotel != null) {
                return hotel.getNameEn();
            }
            return "";
        }
        return "";
    }



    public List<Facility> getListFacility(){
        return apartmentFacilityService.getListFacilityForApartment(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null ){
            return false;
        }
        if(getClass() != object.getClass()){
            return false;
        }
        Apartment apartment = (Apartment) object;
        return this.id == apartment.id &&
                this.hotelId == apartment.id &&
                this.roomSize == apartment.roomSize &&
                this.countGuest == apartment.countGuest &&
                Double.compare(apartment.price, this.price) == 0 &&
                apartment.name.equals(this.name) &&
                apartment.description.equals(this.description)&&
                apartment.nameEn.equals(this.nameEn) &&
                apartment.descriptionEn.equals(this.descriptionEn);
    }

    @Override
    public int hashCode() {
        int rez = 1;
        rez = rez*15 + rez*id;
        rez = rez*15 + (name==null ? 0: name.hashCode()) *rez;
        rez = rez*15 + (nameEn==null ? 0: nameEn.hashCode()) *rez;
        rez = rez*15 + (description==null ? 0: description.hashCode()) *rez;
        rez = rez*15 + (descriptionEn==null ? 0: descriptionEn.hashCode()) *rez;
        rez = rez*15 + rez*(int)roomSize;
        rez = rez*15 + rez*countGuest;
        rez = rez*15 + rez*hotelId;
        rez = rez*15 + rez*(int)price;
        return rez;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", roomSize=" + roomSize +
                ", countGuest=" + countGuest +
                ", hotelId=" + hotelId +
                ", price=" + price +
                '}';
    }
}
