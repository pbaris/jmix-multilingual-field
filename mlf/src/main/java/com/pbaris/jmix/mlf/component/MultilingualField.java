package com.pbaris.jmix.mlf.component;

import java.util.List;

import com.pbaris.jmix.mlf.LocalesProvider;
import com.pbaris.jmix.mlf.data.MultilingualString;
import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.Style;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.richtexteditor.RichTextEditor;
import io.jmix.flowui.data.SupportsValueSource;
import io.jmix.flowui.data.ValueSource;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author Panos Bariamis (pbaris)
 */
//TODO add required
@StyleSheet("mlf.css")
public class MultilingualField extends CustomField<MultilingualString> implements SupportsValueSource<MultilingualString>,
    ApplicationContextAware, InitializingBean, HasAriaLabel {

    public enum Type { SINGLE, MULTI, RTF }

    public enum Mode { SYSTEM, USER }

    private ApplicationContext applicationContext;
    private UiComponents uiComponents;
    private MultilingualFieldDelegate fieldDelegate;

    private List<String> locales;
    private MultilingualString mlstr;

    private Select<String> localeField;
    private AbstractSinglePropertyField<?, String> contentField;

    private Type fieldType = Type.SINGLE;

    @Setter
    private String multilineHeight = "var(--mlf-multiline-height, 6.5em)";

    @Setter
    private String multilineMinHeight;

    @Setter
    private String multilineMaxHeight;

    private boolean isUpdateLocale;

    @Override
    public void setApplicationContext(@NonNull final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.fieldDelegate = applicationContext.getBean(MultilingualFieldDelegate.class, this);
        this.locales = applicationContext.getBean(LocalesProvider.class).getAvailableLocales(Mode.SYSTEM); //TODO Parametrize
        this.uiComponents = applicationContext.getBean(UiComponents.class);
    }

    public void setFieldType(final Type fieldType) {
        this.fieldType = fieldType;
        initComponent();
    }

    private void initComponent() {
        addClassName("ml-field");

        initContentField();
        initLocaleSelect();

        if (fieldType == Type.SINGLE) {
            HorizontalLayout hl = uiComponents.create(HorizontalLayout.class);
            hl.setSpacing(false);
            hl.setAlignItems(Alignment.CENTER);
            hl.addAndExpand(contentField);
            hl.add(localeField);

            add(hl);

        } else {
            VerticalLayout vl = uiComponents.create(VerticalLayout.class);
            vl.setSpacing(false);
            vl.setPadding(false);
            vl.add(localeField);
            vl.addAndExpand(contentField);
            localeField.getStyle().setAlignSelf(Style.AlignSelf.END);

            add(vl);
        }
    }

    private void initLocaleSelect() {
        localeField = new Select<>();
        localeField.setWidth("100px");
        localeField.setItems(locales);

        localeField.setRenderer(new ComponentRenderer<>(locale -> {
            HorizontalLayout wrapper = new HorizontalLayout();
            wrapper.setDefaultVerticalComponentAlignment(Alignment.CENTER);
            wrapper.add(new SvgIcon("icons/%s.svg".formatted(locale)), new Span(locale));
            return wrapper;
        }));

        localeField.addValueChangeListener(e -> {
            isUpdateLocale = true;
            contentField.setValue(mlstr.getContent(e.getValue()));
        });
    }

    private void initContentField() {
        if (fieldType == Type.RTF) {
            contentField = uiComponents.create(RichTextEditor.class);
            initMultilineField(contentField);

        } else if (fieldType == Type.MULTI) {
            addClassName("ml-multi-field");
            contentField = uiComponents.create(TextArea.class);
            initMultilineField(contentField);

        } else {
            addClassName("ml-single-field");
            contentField = uiComponents.create(TextField.class);
        }

        if (contentField instanceof HasSize hasSize) {
            hasSize.setWidth("100%");
        }

        contentField.addValueChangeListener(e -> {
            if (!isUpdateLocale) {
                mlstr.addContent(localeField.getValue(), e.getValue());
            }

            isUpdateLocale = false;
        });
    }

    private void initMultilineField(final HasValueAndElement<?, String> field) {
        if (field instanceof HasSize hasSize) {
            hasSize.setHeight(multilineHeight);
            hasSize.setMinHeight(multilineMinHeight);
            hasSize.setMaxHeight(multilineMaxHeight);
        }
    }

    @Override
    protected MultilingualString generateModelValue() {
        return new MultilingualString(mlstr);
    }

    @Override
    protected void setPresentationValue(final MultilingualString mlstr) {
        this.mlstr = mlstr;
        localeField.setValue(locales.get(0));
    }

    @Nullable
    @Override
    public ValueSource<MultilingualString> getValueSource() {
        return fieldDelegate.getValueSource();
    }

    @Override
    public void setValueSource(@Nullable final ValueSource<MultilingualString> valueSource) {
        fieldDelegate.setValueSource(valueSource);
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        localeField.setReadOnly(readOnly);
        contentField.setReadOnly(readOnly);
    }

    @Override
    public void setValue(final MultilingualString value) {
        super.setValue(value == null ? new MultilingualString() : value);
    }
}
