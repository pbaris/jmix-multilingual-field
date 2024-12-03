package gr.netmechanics.jmix.mlf.locales;

import com.vaadin.flow.component.html.Image;

/**
 * @author Panos Bariamis (pbaris)
 */
public enum LocaleIcon {
    BG("Bulgarian"),
    CS("Czech"),
    DA("Danish"),
    DE("German"),
    EL("Greek"),
    EN("English"),
    ES("Spanish"),
    ET("Estonian"),
    FI("Finnish"),
    FR("French"),
    HE("Hebrew"),
    HI("Hindi"),
    HR("Croatian"),
    HU("Hungarian"),
    HY("Armenian"),
    IT("Italian"),
    JA("Japanese"),
    KO("Korean"),
    LT("Lithuanian"),
    MT("Maltese"),
    NL("Dutch"),
    NO("Norwegian"),
    PL("Polish"),
    PT("Portuguese"),
    RO("Romanian"),
    RU("Russian"),
    SQ("Albanian"),
    SR("Serbian"),
    SV("Swedish"),
    TH("Thai"),
    TR("Turkish"),
    VI("Vietnamese"),
    ZH("Chinese");

    private final String title;

    LocaleIcon(final String title) {
        this.title = title;
    }

    public static String getTitle(final String locale) {
        try {
            LocaleIcon localeIcon = LocaleIcon.valueOf(locale.toUpperCase());
            return localeIcon.title;

        } catch (IllegalArgumentException e) {
            return locale;
        }
    }

    public static Image getIcon(final String locale) {
        Image icon;
        try {
            LocaleIcon.valueOf(locale.toUpperCase());
            icon = new Image("icons/%s.png".formatted(locale.toLowerCase()), locale.toLowerCase());

        } catch (IllegalArgumentException e) {
            icon = new Image("icons/xx.png", "unknown");
        }

        icon.setWidth("24px");
        return icon;
    }
}
