package by.htp.booking.controller.command;

public enum ActionsClient {
    SEARCHAPARTMENT {
        {
            this.command = new CmdSearchApartment();
        }
    },
    APARTMENT {
        {
            this.command = new CmdApartment();
        }
    },
    APARTMENTVIEW {
        {
            this.command = new CmdApartmentView();
        }
    },
    USERORDER {
        {
            this.command = new CmdUserOrder();
        }
    },
    EDITORDER {
        {
            this.command = new CmdEditOrder();
        }
    },
    CREATEORDER {
        {
            this.command = new CmdCreateOrder();
        }
    },
    INDEX {
        {
            this.command = new CmdIndex();
        }
    },
    LOGIN {
        {
            this.command = new CmdLogin();
        }
    },
    SIGNUP {
        {
            this.command = new CmdSignup();
        }
    },
    LISTAPARTMENT {
        {
            this.command = new CmdListApartment();
        }
        },
    ERROR {
        {
            this.command = new CmdError();
        }
    };

    public Cmd command;

}
