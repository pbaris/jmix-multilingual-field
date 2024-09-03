package com.pbaris.jmix.mlf;

import java.util.List;
import java.util.Locale;

import com.pbaris.jmix.mlf.component.MultilingualField;
import io.jmix.core.CoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mlf_LocalesProvider")
public class LocalesProvider {

    private CoreProperties coreProperties;
    private UserLocalesProvider userLocalesProvider;

    public List<String> getAvailableLocales(final MultilingualField.Mode mode) {
        return switch (mode) {
            case SYSTEM -> getSystemLocales();
            case USER -> getUserLocales();
        };
    }

    public String getDefaultLocale(final MultilingualField.Mode mode) {
        return getAvailableLocales(mode).get(0);
    }

    private List<String> getSystemLocales() {
        coreProperties.get
        return coreProperties.getAvailableLocales().stream()
            .map(Locale::getLanguage)
            .toList();
    }

    private List<String> getUserLocales() {
        return userLocalesProvider != null ? userLocalesProvider.getUserLocales() : getSystemLocales();
    }

    @Autowired
    public void setCoreProperties(final CoreProperties coreProperties) {
        this.coreProperties = coreProperties;
    }

    @Autowired(required = false)
    public void setUserLocalesProvider(final UserLocalesProvider userLocalesProvider) {
        this.userLocalesProvider = userLocalesProvider;
    }
}