package com.pbaris.jmix.mlf.component;

import com.pbaris.jmix.mlf.data.MultilingualString;
import com.vaadin.flow.data.renderer.BasicRenderer;
import com.vaadin.flow.function.ValueProvider;

/**
 * @author Panos Bariamis (pbaris)
 */
public class MultilingualStringRenderer<S> extends BasicRenderer<S, MultilingualString> {

    private final String defaultLocale;

    public MultilingualStringRenderer(final ValueProvider<S, MultilingualString> valueProvider, final String defaultLocale) {
        super(valueProvider);
        this.defaultLocale = defaultLocale;
    }

    @Override
    protected String getFormattedValue(final MultilingualString object) {
        return object.getContent(defaultLocale, "Undefined for [%s]".formatted(defaultLocale));
    }
}
