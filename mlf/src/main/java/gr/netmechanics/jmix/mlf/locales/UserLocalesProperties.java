package gr.netmechanics.jmix.mlf.locales;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@ConfigurationProperties(prefix = "jmix.mlf")
public class UserLocalesProperties {
    private final LocaleMode localeMode;
    private final boolean validateDefaultLocaleOnly;
    private final String userLocales;
    private final String userLocalesAppSettingClass;

    public UserLocalesProperties(
        @DefaultValue("SYSTEM") final LocaleMode localeMode,
        @DefaultValue("false") final boolean validateDefaultLocaleOnly,
        @DefaultValue("en") final String userLocales,
        final String userLocalesAppSettingClass) {

        this.localeMode = localeMode;
        this.validateDefaultLocaleOnly = validateDefaultLocaleOnly;
        this.userLocales = userLocales;
        this.userLocalesAppSettingClass = userLocalesAppSettingClass;
    }
}
