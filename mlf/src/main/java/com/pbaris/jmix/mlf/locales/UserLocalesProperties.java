package com.pbaris.jmix.mlf.locales;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@ConfigurationProperties(prefix = "jmix.mlf")
public class UserLocalesProperties {
    private final String userLocales;
    private final String userLocalesAppSettingClass;

    public UserLocalesProperties(
        @DefaultValue("en") final String userLocales,
        final String userLocalesAppSettingClass) {

        this.userLocales = userLocales;
        this.userLocalesAppSettingClass = userLocalesAppSettingClass;
    }
}
