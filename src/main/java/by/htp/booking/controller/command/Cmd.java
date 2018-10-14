package by.htp.booking.controller.command;

import javax.servlet.http.HttpServletRequest;

public abstract class Cmd {
    public abstract Cmd execute(HttpServletRequest req) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                .replaceFirst("Cmd", "");
    }

    public String getJsp(){
        return "/views/"+this.toString().toLowerCase()+".jsp";
    }


}
