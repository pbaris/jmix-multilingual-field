package com.pbaris.jmix.mlf.locales;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import io.jmix.appsettings.AppSettings;
import io.jmix.core.CoreProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("mlf_LocalesProvider")
public class LocalesProvider {

    private final ApplicationContext appContext;
    private final CoreProperties coreProperties;
    private final UserLocalesProperties localesProperties;

    public List<String> getAvailableLocales(final LocaleMode mode) {
        return switch (mode) {
            case SYSTEM -> getSystemLocales();
            case USER -> getUserLocales();
        };
    }

    public String getDefaultLocale(final LocaleMode mode) {
        return getAvailableLocales(mode).get(0);
    }

    private List<String> getSystemLocales() {
        return coreProperties.getAvailableLocales().stream()
            .map(Locale::getLanguage)
            .toList();
    }

    private List<String> getUserLocales() {
        List<String> userLocales = getUserLocalesFromClass();

        if (CollectionUtils.isEmpty(userLocales)) {
            userLocales = getLocales(localesProperties.getUserLocales());
        }

        if (CollectionUtils.isEmpty(userLocales)) {
            userLocales = getSystemLocales();
        }

        return userLocales;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<String> getUserLocalesFromClass() {
        String classStr = localesProperties.getUserLocalesAppSettingClass();

        if (StringUtils.isBlank(classStr)) {
            return null;
        }

        try {
            Class.forName(AppSettings.class.getCanonicalName());
            Class clazz = Class.forName(classStr);
            Object obj = appContext.getBean(AppSettings.class).load(clazz);

            if (obj instanceof UserLocales ul) {
                return getLocales(ul.getLocales());
            }
        } catch (Exception e) {
            /* EMPTY */
        }

        return null;
    }

    private List<String> getLocales(final String locales) {
        if (StringUtils.isNotBlank(locales)) {
            return Arrays.stream(locales.split(","))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .toList();
        }

        return null;
    }
}