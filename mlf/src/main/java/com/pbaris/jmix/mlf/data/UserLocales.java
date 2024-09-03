package com.pbaris.jmix.mlf.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
public interface UserLocales {
    String getLocales();

    default List<String> getLocalesList() {
        String locales = getLocales();

        if (StringUtils.isNotBlank(locales)) {
            return Arrays.stream(locales.split(","))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .toList();
        }

        return Collections.emptyList();
    }
}
