package com.pbaris.jmix.mlf.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Converter(autoApply = true)
public class MultilingualStringConverter implements AttributeConverter<MultilingualString, String> {

    @Override
    public String convertToDatabaseColumn(final MultilingualString mlstr) {
        return mlstr != null ? MultilingualString.toJson(mlstr) : null;
    }

    @Override
    public MultilingualString convertToEntityAttribute(final String dbData) {
        return dbData != null ? MultilingualString.fromJson(dbData) : null;
    }
}
