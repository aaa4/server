package github.aaa4.server.web.controllers.web;

import github.aaa4.server.web.controllers.web.exceptions.MyResourceNotFoundException;

public class RestPreconditions {
    public static <T> T checkFound(T resource){
        if (resource == null)
            throw new MyResourceNotFoundException();
        return resource;
    }
}
