package com.authy.api;

import com.authy.AuthyException;

/**
 * @author Moisés Vargas
 */
public class PhoneInfo extends Resource {
    public static final String PHONE_INFO_API_PATH = "/protected/json/phones/";

    public PhoneInfo(String uri, String key) {
        super(uri, key, Resource.JSON_CONTENT_TYPE);
    }

    public PhoneInfo(String uri, String key, boolean testFlag) {
        super(uri, key, testFlag, Resource.JSON_CONTENT_TYPE);
    }

    public PhoneInfoResponse info(String phoneNumber, String countryCode) throws AuthyException {
        return info(phoneNumber, countryCode, new Params());
    }

    public PhoneInfoResponse info(String phoneNumber, String countryCode, Params params) throws AuthyException {
        params.setAttribute("phone_number", phoneNumber);
        params.setAttribute("country_code", countryCode);
        final Response response = this.get(PHONE_INFO_API_PATH + "info", params);
        PhoneInfoResponse info = new PhoneInfoResponse(response.getStatus(), response.getBody());
        if (!info.isOk()) {
            info.setError(errorFromJson(response.getBody()));
        }
        return info;
    }

}
