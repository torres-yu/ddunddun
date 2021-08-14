package io.torres.practice.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

    private CommonUtils(){}

    public static String getUrl(HttpServletRequest request) {
        return request.getScheme() + "://" +
                request.getServerName() +
                ":" + request.getServerPort();
    }
}
