package com.example.tram.util;

import java.util.Locale;

public final class ProjectConstants {
    public static final String DEFAULT_ENCODING = "utf-8";

    public static final Locale VIETNAMEESE_LOCALE = new Locale.Builder().setLanguage("vn").setRegion("VN").build();
    public static final Locale DEFAUL_LOCALE = new Locale.Builder().setLanguage("").setRegion("").build();

    public static final Integer ACCESS_TOKEN_EXPIRED_TIME = 60 * 15;
    public static final Integer REFRESH_TOKEN_EXPIRED_TIME = 60 * 60 *24 *30;

    private ProjectConstants(){
        throw new UnsupportedOperationException();
    }
}
