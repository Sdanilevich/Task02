package by.htp.booking.controller.command;

import by.htp.booking.controller.ActionFactory;

import javax.servlet.http.HttpServletRequest;

public class CmdSignout extends Cmd {

    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        ActionFactory.removeUserFromSession(req);
        return ActionsClient.SEARCHAPARTMENT.command;
    }
}
