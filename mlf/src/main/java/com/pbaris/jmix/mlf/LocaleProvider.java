package com.pbaris.jmix.mlf;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.pbaris.jmix.mlf.component.MultilingualField;
import io.jmix.core.CoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("mlf_LocaleProvider")
public class LocaleProvider {

    private final CoreProperties coreProperties;

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
        return coreProperties.getAvailableLocales().stream()
            .map(Locale::getLanguage)
            .toList();
    }

    private List<String> getUserLocales() {
        return Collections.emptyList();
    }
}