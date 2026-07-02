package gr.netmechanics.jmix.mlf.locales;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.streams.DownloadHandler;

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
        String code = locale != null ? locale.toLowerCase() : "xx";
        String path = "/META-INF/resources/icons/%s.png".formatted(code);

        DownloadHandler src;
        try {
            LocaleIcon.valueOf(code.toUpperCase());
            src = DownloadHandler.forClassResource(LocaleIcon.class, path);

        } catch (Exception e) {
            src = DownloadHandler.forClassResource(LocaleIcon.class, "/META-INF/resources/icons/xx.png");
            code = "unknown";
        }

        Image icon = new Image(src, code);
        icon.setWidth("20px");
        return icon;
    }
}
