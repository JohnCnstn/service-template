package com.letsdev.employeedevelopmentcards.util

import org.apache.commons.lang3.StringUtils.firstNonEmpty
import org.springframework.boot.SpringBootVersion
import java.lang.System.getProperty
import java.lang.System.getenv

val appVersion: String
    get() = firstNonEmpty(getenv("APP_VERSION"), "1970-01-01-00-00-00-unknown")

val springBootVersion: String
    get() = firstNonEmpty(SpringBootVersion.getVersion(), "unknown")

val javaVersion: String
    get() = firstNonEmpty(getProperty("java.vm.version"), getProperty("java.runtime.version"), "unknown")

val javaVendor: String
    get() = firstNonEmpty(getProperty("java.vm.vendor"), getProperty("java.vendor"), "unknown")
