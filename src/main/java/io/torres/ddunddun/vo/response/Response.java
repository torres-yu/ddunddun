package io.torres.ddunddun.vo.response;

import java.util.HashMap;

public class Response extends HashMap {

    public static ResponseBuilder Builder(){
        return new ResponseBuilder();
    }
}
