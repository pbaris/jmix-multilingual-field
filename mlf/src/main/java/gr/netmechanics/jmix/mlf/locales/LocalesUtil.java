package gr.netmechanics.jmix.mlf.locales;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalesUtil {

    private static final LocalesUtil INSTANCE = new LocalesUtil();

    private String defaultLocale;

    public static String getDefaultLocale() {
        return INSTANCE.defaultLocale;
    }

    static void setDefaultLocale(final String defaultLocale) {
        INSTANCE.defaultLocale = defaultLocale;
    }
}
