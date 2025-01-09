package gr.netmechanics.jmix.mlf.component;

import com.vaadin.flow.data.renderer.BasicRenderer;
import com.vaadin.flow.function.ValueProvider;
import gr.netmechanics.jmix.mlf.data.MultilingualString;

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
        return object == null ? "" : object.getContent(defaultLocale, "Undefined for [%s]".formatted(defaultLocale));
    }
}
