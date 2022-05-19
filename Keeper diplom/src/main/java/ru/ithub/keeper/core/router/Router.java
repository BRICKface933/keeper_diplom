package ru.ithub.keeper.core.router;


public interface Router {
    String API = "/api";
    String API_V1 = API + "/v1";

    interface Authentication {
        String NAME = "/auth";
        String ROOT = Router.API_V1 + NAME;

        interface Sign {
            String NAME = "/sign";
            String ROOT = Authentication.ROOT + NAME;
            interface In {
                String NAME = "/in";
                String ROOT = Sign.ROOT + NAME;
            }

            interface Up {
                String NAME = "/up";
                String ROOT = Sign.ROOT + NAME;
            }
        }
    }

    interface User {
        String NAME = "/users";
        String ROOT = Router.API_V1 + NAME;

        interface Id {
            String NAME = "/{id}";
            String ROOT = User.ROOT + NAME;
        }
    }
}