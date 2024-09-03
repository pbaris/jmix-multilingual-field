package com.pbaris.jmix.mlf.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@EqualsAndHashCode(of = "id")
public class MultilingualString implements Serializable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Getter
    private final Long id;

    private final Map<String, String> contents;

    public MultilingualString() {
        this(null);
    }

    public MultilingualString(final MultilingualString copy) {
        this.id = System.currentTimeMillis();
        this.contents = new HashMap<>(copy == null ? Collections.emptyMap() : copy.contents);
    }

    @JsonAnyGetter
    public Map<String, String> getContents() {
        return contents;
    }

    @JsonIgnore
    public String getContent(final String locale) {
        return getContent(locale, "");
    }

    @JsonIgnore
    public String getContent(final String locale, final String defaultValue) {
        return contents.getOrDefault(locale, defaultValue);
    }

    @JsonAnySetter
    public void addContent(final String locale, final String content) {
        contents.put(locale, content);
    }

    @Override
    public String toString() {
        return toJson(this);
    }

    public static String toJson(final MultilingualString mlstr) {
        try {
            return OBJECT_MAPPER.writeValueAsString(mlstr);

        } catch (Exception e) {
            return "{}";
        }
    }

    public static MultilingualString fromJson(final String json) {
        try {
            return OBJECT_MAPPER.readValue(StringUtils.defaultIfBlank(json, "{}"), MultilingualString.class);

        } catch (Exception e) {
            return null;
        }
    }
}
