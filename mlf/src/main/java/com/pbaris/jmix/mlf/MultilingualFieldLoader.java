package com.pbaris.jmix.mlf;

import com.pbaris.jmix.mlf.component.MultilingualField;
import com.pbaris.jmix.mlf.locales.LocaleMode;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowui.xml.layout.support.DataLoaderSupport;
import org.dom4j.Element;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
public class MultilingualFieldLoader extends AbstractComponentLoader<MultilingualField> {

    protected DataLoaderSupport dataLoaderSupport;

    @Override
    @NonNull
    protected MultilingualField createComponent() {
        return factory.create(MultilingualField.class);
    }

    @Override
    public void loadComponent() {
        componentLoader().loadLabel(resultComponent, element);
        componentLoader().loadSizeAttributes(resultComponent, element);
        componentLoader().loadClassNames(resultComponent, element);
        componentLoader().loadAriaLabel(resultComponent, element);
        componentLoader().loadHelperText(resultComponent, element);

        loadMultilineHeight(resultComponent, element);
        loadMultilineMaxHeight(resultComponent, element);
        loadMultilineMinHeight(resultComponent, element);

        loadLocaleMode(resultComponent, element);
        loadFieldType(resultComponent, element);

        getDataLoaderSupport().loadData(resultComponent, element);
        componentLoader().loadEnabled(resultComponent, element);
        componentLoader().loadValueAndElementAttributes(resultComponent, element);
    }

    private void loadLocaleMode(final MultilingualField component, final Element element) {
        LocaleMode localeMode = loaderSupport.loadString(element, "localeMode")
            .map(LocaleMode::valueOf)
            .orElse(LocaleMode.SYSTEM);
        component.setLocaleMode(localeMode);
    }

    private void loadFieldType(final MultilingualField component, final Element element) {
        MultilingualField.Type fieldType = loaderSupport.loadString(element, "fieldType")
            .map(MultilingualField.Type::valueOf)
            .orElse(MultilingualField.Type.SINGLE);
        component.setFieldType(fieldType);
    }

    private void loadMultilineHeight(final MultilingualField component, final Element element) {
        loaderSupport.loadString(element, "multilineHeight")
            .ifPresent(component::setMultilineHeight);
    }

    private void loadMultilineMaxHeight(final MultilingualField component, final Element element) {
        loaderSupport.loadString(element, "multilineMinHeight")
            .ifPresent(component::setMultilineMaxHeight);
    }

    private void loadMultilineMinHeight(final MultilingualField component, final Element element) {
        loaderSupport.loadString(element, "multilineMaxHeight")
            .ifPresent(component::setMultilineMinHeight);
    }

    private DataLoaderSupport getDataLoaderSupport() {
        if (dataLoaderSupport == null) {
            dataLoaderSupport = applicationContext.getBean(DataLoaderSupport.class, context);
        }
        return dataLoaderSupport;
    }
}
