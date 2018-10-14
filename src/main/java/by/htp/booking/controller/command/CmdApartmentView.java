package by.htp.booking.controller.command;

import by.htp.booking.bean.Apartment;
import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.bean.Facility;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.ApartmentFacilityService;
import by.htp.booking.service.impl.ApartmentService;
import by.htp.booking.service.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdApartmentView extends Cmd {

    ApartmentService apartmentService = ActionFactory.apartmentService;
    ApartmentFacilityService apartmentFacilityService = ActionFactory.apartmentFacilityService;
    private static final Logger log = Logger.getLogger(CmdApartmentView.class);

    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        int apartmentId = Integer.valueOf(req.getParameter("id"));
        if (apartmentId!=0){
            Apartment apartment = apartmentService.getApartmentById(apartmentId);
            if (apartment!=null){
                List<Facility> listFacility = apartmentFacilityService.getListFacilityForApartment(apartment.getId());
                List<String> listPathImg = apartmentService.getListPathImg(apartment.getId());

                req.setAttribute("listFacility", listFacility);
                req.setAttribute("apartment", apartment);
                log.info("Go to page review apartment");

            }
            else {
              log.error("Apartment not found");
              return ActionsClient.ERROR.command;
            }

        }
        return null;
    }
}
