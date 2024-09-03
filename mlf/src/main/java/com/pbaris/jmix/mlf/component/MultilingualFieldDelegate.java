package com.pbaris.jmix.mlf.component;

import com.pbaris.jmix.mlf.data.MultilingualString;
import io.jmix.flowui.component.delegate.AbstractFieldDelegate;
import io.jmix.flowui.data.ValueSource;
import io.jmix.flowui.data.binding.impl.AbstractValueBinding;
import io.jmix.flowui.data.binding.impl.FieldValueBinding;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Panos Bariamis (pbaris)
 */
@Component("mlf_MultilingualStringFieldDelegate")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MultilingualFieldDelegate extends AbstractFieldDelegate<MultilingualField, MultilingualString, MultilingualString> {

    public MultilingualFieldDelegate(final MultilingualField component) {
        super(component);
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    protected AbstractValueBinding<MultilingualString> createValueBinding(@NonNull final ValueSource<MultilingualString> valueSource) {
        return applicationContext.getBean(FieldValueBinding.class, valueSource, component);
    }
}
