package by.htp.booking.bean;

import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.ApartmentService;
import by.htp.booking.service.impl.HotelService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order extends AbstractBean implements Serializable {
    private int id;
    private int userId;
    private int apartmentId;
    private long beginDate;
    private long endDate;
    private int countDay;
    private double price;
    private double total;


    private ApartmentService apartmentService = ActionFactory.apartmentService;
    private HotelService hotelService = ActionFactory.hotelService;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getCountDay() {
        return countDay;
    }

    public void setCountDay(int countDay) {
        this.countDay = countDay;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getApartmentName(){
         Apartment apartment = apartmentService.getApartmentById(apartmentId);
         if (apartment!=null) {
             return apartment.getName();
         }
        return  null;
    }
    public String getApartmentNameEn(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null) {
            return apartment.getNameEn();
        }
        return  null;
    }
    public String getAddress(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null) {
            return apartment.getAddress();
        }
        return  null;
    }
    public String getAddressEn(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null) {
            return apartment.getAddressEn();
        }
        return  null;
    }

    public String getHotelName(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null) {
            Hotel hotel = hotelService.getHotelById(apartment.getHotelId());
            return hotel.getName();

        }
        return  null;
    }
    public String getHotelNameEn(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null) {
            Hotel hotel = hotelService.getHotelById(apartment.getHotelId());
            return hotel.getNameEn();

        }
        return  null;
    }

    public String getBeginDateFormat(){
        Date date = new Date(beginDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormat.format(date);
    }

    public String getEndDateFormat(){
        Date date = new Date(endDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return dateFormat.format(date);
    }

    public int getCountGuest(){
        Apartment apartment = apartmentService.getApartmentById(apartmentId);
        if (apartment!=null){
            return apartment.getCountGuest();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                apartmentId == order.apartmentId &&
                countDay == order.countDay &&
                Double.compare(order.price, price) == 0 &&
                Double.compare(order.total, total) == 0 &&
                Objects.equals(beginDate, order.beginDate) &&
                Objects.equals(endDate, order.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, apartmentId, beginDate, endDate, countDay, price, total);
    }
}
