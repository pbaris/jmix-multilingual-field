package com.pbaris.jmix.mlf;

import com.pbaris.jmix.mlf.component.MultilingualField;
import com.pbaris.jmix.mlf.locales.LocaleMode;
import com.pbaris.jmix.mlf.locales.UserLocalesProperties;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowui.xml.layout.support.DataLoaderSupport;
import org.dom4j.Element;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
public class MultilingualFieldLoader extends AbstractComponentLoader<MultilingualField> {

    private DataLoaderSupport dataLoaderSupport;
    private UserLocalesProperties userLocalesProperties;

    @Override
    @NonNull
    protected MultilingualField createComponent() {
        return factory.create(MultilingualField.class);
    }

    @Override
    public void loadComponent() {
        componentLoader().loadEnabled(resultComponent, element);
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
        componentLoader().loadRequired(resultComponent, element, context);
        componentLoader().loadValidationAttributes(resultComponent, element, context);
        componentLoader().loadValueAndElementAttributes(resultComponent, element);
        componentLoader().loadFocusableAttributes(resultComponent, element);
        componentLoader().loadTooltip(resultComponent, element);
    }

    private void loadLocaleMode(final MultilingualField component, final Element element) {
        LocaleMode localeMode = loaderSupport.loadString(element, "localeMode")
            .map(LocaleMode::valueOf)
            .orElse(getUserLocalesProperties().getDefaultLocaleMode());
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

    public UserLocalesProperties getUserLocalesProperties() {
        if (userLocalesProperties == null) {
            userLocalesProperties = applicationContext.getBean(UserLocalesProperties.class);
        }
        return userLocalesProperties;
    }
}
