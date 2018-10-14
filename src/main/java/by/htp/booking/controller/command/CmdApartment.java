package by.htp.booking.controller.command;

import by.htp.booking.bean.Apartment;
import by.htp.booking.bean.Facility;

import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.ApartmentFacilityService;
import by.htp.booking.service.impl.ApartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdApartment extends Cmd {

    ApartmentService apartmentService = ActionFactory.apartmentService;

    ApartmentFacilityService apartmentFacilityService = ActionFactory.apartmentFacilityService;

    private static final Logger log = Logger.getLogger(CmdApartment.class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        if (req.getParameter("id")!=null){

            Apartment apartment = apartmentService.getApartmentById(Integer.valueOf(req.getParameter("id")));
            List<Facility> listFacility = apartmentFacilityService.getListFacilityForApartment(apartment.getId());
            List<String> listPathImg = apartmentService.getListPathImg(apartment.getId());

            req.setAttribute("listFacility", listFacility);
            req.setAttribute("apartment", apartment);
            log.info("Go to page apartment's review ");
        }
        return null;
    }
}
