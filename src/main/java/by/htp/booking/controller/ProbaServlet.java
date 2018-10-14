package by.htp.booking.controller;


import by.htp.booking.controller.command.ActionsClient;
import by.htp.booking.controller.command.Cmd;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class ProbaServlet extends HttpServlet{
    private ActionFactory actionFactory;

    private static final Logger log = Logger.getLogger(ProbaServlet.class);
    @Override
    public void init() {
        actionFactory = new ActionFactory();
     }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (ActionFactory.getLocale(req)==null){
            ActionFactory.putDataToSession(req);
            ActionFactory.putDataLocalToSession(req);
        }
        Cmd cmd = actionFactory.defineCmd(req);
        if (actionFactory.changeLocal(req)){
            ActionFactory.putDataLocalToSession(req);
        }

        String viewPage;
        try {
            Cmd next = cmd.execute(req);
            if (next == null) {
                viewPage = cmd.getJsp();
                getServletContext().getRequestDispatcher(viewPage).forward(req, resp);
            } else {
                resp.sendRedirect("do?command="+next.toString());
            }
        } catch (Exception e) {
            req.setAttribute("err",e.getMessage());
            log.info(e.getMessage());
            getServletContext().getRequestDispatcher(ActionsClient.ERROR.command.getJsp()).forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cmd cmd = actionFactory.defineCmd(req);
        String viewPage;
        try {

            Cmd next = cmd.execute(req);
            if (next == null) {
               viewPage = cmd.getJsp();
               getServletContext().getRequestDispatcher(viewPage).forward(req, resp);
            } else {
                resp.sendRedirect("do?command="+next.toString());
            }
        } catch (Exception e) {
            req.setAttribute("err",e.getMessage());
            log.info(e.getMessage());
            getServletContext().getRequestDispatcher(ActionsClient.ERROR.command.getJsp()).forward(req, resp);
        }
    }


}
