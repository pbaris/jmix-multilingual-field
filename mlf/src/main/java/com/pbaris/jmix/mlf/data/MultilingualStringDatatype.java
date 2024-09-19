package com.pbaris.jmix.mlf.data;

import java.text.ParseException;
import java.util.Locale;

import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.annotation.Ddl;
import io.jmix.core.metamodel.datatype.Datatype;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
@DatatypeDef(id = "multilingualString", javaClass = MultilingualString.class, defaultForClass = true)
//@Ddl("clob") //TODO default type ???
@Ddl(dbms = "postgres", value = "jsonb")
public class MultilingualStringDatatype implements Datatype<MultilingualString> {

    @NonNull
    @Override
    public String format(final Object value) {
        if (value instanceof MultilingualString mlstr) {
            return MultilingualString.toJson(mlstr);
        }

        return "";
    }

    @NonNull
    @Override
    public String format(final Object value, @NonNull final Locale locale) {
        return format(value);
    }

    @Override
    public MultilingualString parse(final String value) throws ParseException {
        return MultilingualString.fromJson(value);
    }

    @Override
    public MultilingualString parse(final String value, @NonNull final Locale locale) throws ParseException {
        return parse(value);
    }
}
