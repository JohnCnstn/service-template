package com.johncnstn.servicetemplate.util;

import static java.lang.System.getenv;
import static org.apache.commons.lang3.StringUtils.firstNonEmpty;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VersionUtils {

    public static String getAppVersion() {
        return firstNonEmpty(getenv("APP_VERSION"), "1970-01-01-00-00-00-unknown");
    }
}
