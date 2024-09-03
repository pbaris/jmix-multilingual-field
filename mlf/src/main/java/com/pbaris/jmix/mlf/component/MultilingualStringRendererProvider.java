package com.pbaris.jmix.mlf.component;

import com.pbaris.jmix.mlf.LocalesProvider;
import io.jmix.core.entity.EntityValues;
import io.jmix.core.metamodel.model.MetaPropertyPath;
import io.jmix.flowui.xml.layout.ComponentLoader;
import io.jmix.flowui.xml.layout.loader.component.datagrid.RendererProvider;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Panos Bariamis (pbaris)
 */
@Component("mlf_MultilingualStringRendererProvider")
public class MultilingualStringRendererProvider implements RendererProvider<MultilingualStringRenderer<?>> {
    public static final String NAME = "multilingualStringRenderer";

    private LocalesProvider localesProvider;

    @Override
    public boolean supports(final String rendererName) {
        return NAME.equals(rendererName);
    }

    @Override
    public MultilingualStringRenderer<?> createRenderer(final Element element, 
                                                        final MetaPropertyPath metaPropertyPath, final ComponentLoader.Context context) {

        return new MultilingualStringRenderer<>(item -> EntityValues.getValueEx(item, metaPropertyPath),
            localesProvider.getDefaultLocale(MultilingualField.Mode.SYSTEM)); //TODO Parametrize
    }

    @Autowired(required = false)
    public void setLocaleProvider(final LocalesProvider localesProvider) {
        this.localesProvider = localesProvider;
    }
}
