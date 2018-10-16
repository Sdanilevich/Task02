package by.htp.booking.controller;

import by.htp.booking.bean.User;
import by.htp.booking.controller.command.ActionsClient;
import by.htp.booking.controller.command.Cmd;
import by.htp.booking.service.impl.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class ActionFactory {

    public static int countApartmentOnPage=5;
    public static String defaultLocale="Ru";
    public static CountryService countryService;
    public static CityService cityService;
    public static HotelService hotelService;
    public static ApartmentService apartmentService;
    public static FacilityService facilityService;
    public static ApartmentFacilityService apartmentFacilityService;
    public static UserService userService;
    public static OrderService orderService;

    {
        ClassPathXmlApplicationContext serviceContext =
                new ClassPathXmlApplicationContext("serviceContext.xml");
        countryService = serviceContext.getBean("countryService", CountryService.class);
        cityService = serviceContext.getBean("cityService", CityService.class);
        hotelService = serviceContext.getBean("hotelService", HotelService.class);
        apartmentService = serviceContext.getBean("apartmentService", ApartmentService.class);
        facilityService = serviceContext.getBean("facilityService", FacilityService.class);
        apartmentFacilityService = serviceContext.getBean("apartmentFacilityService", ApartmentFacilityService.class);
        userService = serviceContext.getBean("userService",UserService.class);
        orderService = serviceContext.getBean("orderService", OrderService.class);

    }

    public static void removeUserFromSession(HttpServletRequest request) {
        removeSearchParamFromSession(request);
        HttpSession session = request.getSession();
        session.removeAttribute("user");

    }


    Cmd defineCmd(HttpServletRequest req) {
        String commandName = req.getParameter("command");

        Cmd current = ActionsClient.ERROR.command;
        if (commandName != null && !commandName.isEmpty()) {
            try {
                current = ActionsClient.valueOf(commandName.toUpperCase()).command;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Command not found");
            }

        }
        return current;
    }

    public ActionFactory(){

    }

    public boolean changeLocal(HttpServletRequest request) {
        Object localRequest = request.getParameter("local");
        HttpSession session = request.getSession();
        Object localSession = session.getAttribute("local");
        if (localRequest!=null){
            if (localRequest.equals(localSession)){
                return false;
            }
            else {
                session.setAttribute("local", localRequest);
            return true;
            }
        }
        return false;
    }
    public static User getUserFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object objUser = session.getAttribute("user");
        if (objUser != null) {
            return (User) objUser;
        }
        return null;
    }

    public static void putDataToSession(HttpServletRequest request){
        ResourceBundle resource;
        HttpSession httpSession = request.getSession();
        resource = ResourceBundle.getBundle("hotelData");
        countApartmentOnPage = Integer.valueOf(resource.getString("data.countApartmentOnPage"));
        httpSession.setAttribute("countApartmentOnPage", countApartmentOnPage);
        httpSession.setAttribute("local", defaultLocale);
    }

    public static String getLocale(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return (String) httpSession.getAttribute("local");

    }

    public static void removeSearchParamFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("dateBegin");
        session.removeAttribute("dateEnd");
        session.removeAttribute("city");
        session.removeAttribute("country");
    }



    public static void putDataLocalToSession(HttpServletRequest request){

        HttpSession httpSession = request.getSession();
        Object locale = httpSession.getAttribute("local");
        String forBundleName;
        if (locale==null){
            forBundleName = defaultLocale;
        }
        else {
            forBundleName = (String) locale;
        }

        ResourceBundle resource = ResourceBundle.getBundle("menu".concat(forBundleName));

        httpSession.setAttribute("menuSearch",resource.getString("menu.search"));
        httpSession.setAttribute("menuMain",resource.getString("menu.main"));
        httpSession.setAttribute("menuLogin",resource.getString("menu.login"));
        httpSession.setAttribute("menuSignUp",resource.getString("menu.signUp"));
        httpSession.setAttribute("menuSignOut",resource.getString("menu.signOut"));

        httpSession.setAttribute("menuUserName",resource.getString("menu.userName"));
        httpSession.setAttribute("apartmentCountGuest",resource.getString("apartment.countGuest"));
        httpSession.setAttribute("apartmentSizeRoom",resource.getString("apartment.sizeRoom"));
        httpSession.setAttribute("apartmentPrice",resource.getString("apartment.price"));
        httpSession.setAttribute("loginTitle",resource.getString("login.title"));
        httpSession.setAttribute("loginName",resource.getString("login.name"));
        httpSession.setAttribute("loginPassword",resource.getString("login.password"));
        httpSession.setAttribute("loginButton",resource.getString("login.button"));
        httpSession.setAttribute("registrationTitle",resource.getString("registration.title"));
        httpSession.setAttribute("registrationLogin",resource.getString("registration.login"));
        httpSession.setAttribute("registrationName",resource.getString("registration.name"));
        httpSession.setAttribute("registrationPassword",resource.getString("registration.password"));
        httpSession.setAttribute("registrationEmail",resource.getString("registration.email"));
        httpSession.setAttribute("registrationButton",resource.getString("registration.button"));
        httpSession.setAttribute("searchTitle",resource.getString("search.title"));
        httpSession.setAttribute("searchCountryTitle",resource.getString("search.countryTitle"));
        httpSession.setAttribute("searchCityTitle",resource.getString("search.cityTitle"));
        httpSession.setAttribute("searchButton", resource.getString("search.button"));
        httpSession.setAttribute("formOrderTitle", resource.getString("formOrder.title"));
        httpSession.setAttribute("formOrderDateFrom", resource.getString("formOrder.dateFrom"));
        httpSession.setAttribute("formOrderDateTo", resource.getString("formOrder.dateTo"));
        httpSession.setAttribute("formOrderCountDay", resource.getString("formOrder.countDay"));
        httpSession.setAttribute("formOrderHotel", resource.getString("formOrder.hotel"));
        httpSession.setAttribute("formOrderCountGuest", resource.getString("formOrder.countGuest"));
        httpSession.setAttribute("formOrderPrice", resource.getString("formOrder.price"));
        httpSession.setAttribute("formOrderTotal", resource.getString("formOrder.total"));

    }

}
