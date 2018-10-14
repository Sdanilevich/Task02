package by.htp.booking.controller.command;

import by.htp.booking.bean.Apartment;
import by.htp.booking.bean.City;
import by.htp.booking.bean.Country;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;
import by.htp.booking.service.impl.ApartmentService;
import by.htp.booking.service.impl.CityService;
import by.htp.booking.service.impl.CountryService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class CmdSearchApartment extends Cmd {

    ApartmentService apartmentService = ActionFactory.apartmentService;

    CountryService countryService = ActionFactory.countryService;

    CityService cityService = ActionFactory.cityService;

    private static final Logger log = Logger.getLogger(CmdSearchApartment.class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        if (req.getMethod().equalsIgnoreCase("post")) {
            req.getParameter("dateBegin");
            req.getParameter("dateEnd");
            req.getParameter("city");

            HttpSession session = req.getSession();
            session.setAttribute("dateBegin",req.getParameter("dateBegin"));
            session.setAttribute("dateEnd",req.getParameter("dateEnd"));
            session.setAttribute("city",Integer.valueOf(req.getParameter("city")));
            session.setAttribute("country",Integer.valueOf(req.getParameter("country")));
            log.info("Search apartment by parameters");
            return ActionsClient.SEARCHAPARTMENT.command;
        }
        else {
            log.info("Go to a page search");
            if (isNewSearch(req)){
                log.info("Go to a new page search");
                removeSearchParamFromSession(req);
            }
            else {
                HttpSession session = req.getSession();
                Object dateBeginFromSession = session.getAttribute("dateBegin");
                Object dateEndFromSession = session.getAttribute("dateEnd");
                Object cityIdFromSession = session.getAttribute("city");
                Object countryIdFromSession = session.getAttribute("country");

                if ((dateBeginFromSession != null) && (dateEndFromSession != null) && (cityIdFromSession != null) && (countryIdFromSession != null)) {
                    String  dateBeginReq = (String) session.getAttribute("dateBegin");
                    String dateEndReq = (String) session.getAttribute("dateEnd");

                    Date dateBegin = Util.getDateFromFormat(dateBeginReq);
                    Date dateEnd = Util.getDateFromFormat(dateEndReq);
                    int city_id = (int) session.getAttribute("city");
                    int country_id = (int) session.getAttribute("country");
                    int begin = 1;
                    if (req.getParameter("beginPortion") != null) {
                        begin = Integer.valueOf(req.getParameter("beginPortion"));
                    }
                    List<Apartment> listApartment = apartmentService.getPortion(dateEnd.getTime(), country_id, city_id, begin, ActionFactory.countApartmentOnPage);
                    req.setAttribute("countApartment", apartmentService.getCountFreeApartments(dateEnd.getTime(), country_id, city_id));

                    req.setAttribute("listApartment", listApartment);
                    log.info("Get list apartment");
                }
            }

            List<Country> listCountry = countryService.getAll("");
            //List<City> listCity = cityService.getlistCityByCountryId(listCountry.get(0).getId());
            List<City> listCity = cityService.getAll("");
            req.setAttribute("listCountry", listCountry);
            req.setAttribute("listCity", listCity);

            return null;
        }
    }

    private boolean isNewSearch(HttpServletRequest req){
        String newSearch = req.getParameter("newSearch");
        if (newSearch!=null) {
            if (newSearch.equalsIgnoreCase("true")){
                return true;
            }
        }
        return false;
    }
    private void removeSearchParamFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("dateBegin");
        session.removeAttribute("dateEnd");
        session.removeAttribute("city");
        session.removeAttribute("country");
       }
}
