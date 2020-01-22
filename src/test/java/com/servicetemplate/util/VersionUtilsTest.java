package com.servicetemplate.util;

import com.servicetemplate.unit.AbstractUnitTest;
import org.junit.jupiter.api.Test;

import static com.servicetemplate.util.VersionUtils.getAppVersion;
import static org.assertj.core.api.Assertions.assertThat;

public class VersionUtilsTest extends AbstractUnitTest {

    @Test
    public void testGetAppVersion() {
        //GIVEN
        //WHEN
        var appVersion = getAppVersion();
        //THEN
        assertThat(appVersion).isNotNull();
    }

}

