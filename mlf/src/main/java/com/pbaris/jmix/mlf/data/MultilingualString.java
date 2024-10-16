package com.pbaris.jmix.mlf.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;

/**
 * @author Panos Bariamis (pbaris)
 */
public class MultilingualString implements Serializable, Comparable<MultilingualString> {
    private static final TypeReference<HashMap<String, String>> TYPE_REFERENCE = new TypeReference<>() {};

    private final Map<String, String> contents;

    public MultilingualString() {
        this((MultilingualString) null);
    }

    public MultilingualString(final MultilingualString copy) {
        this.contents = new HashMap<>(copy == null ? Collections.emptyMap() : copy.contents);
    }

    public MultilingualString(final Map<String, String> contents) {
        this.contents = new HashMap<>(contents);
    }

    public String getContent(final String locale) {
        return getContent(locale, "");
    }

    public String getContent(final String locale, final String defaultValue) {
        return contents.getOrDefault(locale, defaultValue);
    }

    public void addContent(final String locale, final String content) {
        contents.put(locale, content);
    }

    @Override
    public String toString() {
        return toJson(this);
    }

    @Override
    public int compareTo(@Nullable final MultilingualString o) {
        String defaultLocale = "en"; //LocaleUtils.getDefaultLocale(); //TODO somehow

        String s1 = getContent(defaultLocale, null);

        if (o == null && s1 == null) {
            return 0;
        }

        if (o == null) {
            return 1;
        }

        String s2 = o.getContent(defaultLocale, null);
        if (s2 == null) {
            return 1;
        }

        if (s1 == null) {
            return -1;
        }

        return s1.compareTo(s2);
    }

    public static String toJson(final MultilingualString mlstr) {
        try {
            return new ObjectMapper().writeValueAsString(mlstr.contents);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert to Json", e);
        }
    }

    public static MultilingualString fromJson(final String json) {
        try {
            Map<String, String> values = new ObjectMapper().readValue(json, TYPE_REFERENCE);
            return new MultilingualString(values);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert from Json", e);
        }
    }
}
