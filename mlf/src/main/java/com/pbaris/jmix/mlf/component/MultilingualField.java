package com.pbaris.jmix.mlf.component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import com.pbaris.jmix.mlf.data.MultilingualString;
import com.pbaris.jmix.mlf.locales.LocaleMode;
import com.pbaris.jmix.mlf.locales.LocalesProvider;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasAriaLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author Panos Bariamis (pbaris)
 */
@StyleSheet("com/pbaris/jmix/mlf/mlf.css")
public class MultilingualField extends CustomField<MultilingualString> implements SupportsValueSource<MultilingualString>,
    ApplicationContextAware, InitializingBean, HasAriaLabel {

    public enum Type { SINGLE, MULTI, RTF }

    private ApplicationContext applicationContext;
    private UiComponents uiComponents;
    private MultilingualFieldDelegate fieldDelegate;
    private Supplier<AbstractField<?, String>> fieldProvider;

    private List<String> locales;
    private MultilingualString mlstr;

    private Select<String> localeField;
    private AbstractField<?, String> contentField;
    private FlexLayout mainLayout;

    private Type fieldType = Type.SINGLE;

    @Setter
    private String multilineHeight = "var(--mlf-multiline-height, 6.5em)";

    @Setter
    private String multilineMinHeight;

    @Setter
    private String multilineMaxHeight;

    private final AtomicBoolean isUpdateLocale = new AtomicBoolean(false);

    @Override
    public void setApplicationContext(@NonNull final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.fieldDelegate = applicationContext.getBean(MultilingualFieldDelegate.class, this);
        this.uiComponents = applicationContext.getBean(UiComponents.class);
    }

    public void setLocaleMode(final LocaleMode localeMode) {
        this.locales = applicationContext.getBean(LocalesProvider.class).getAvailableLocales(localeMode);
    }

    public void setFieldType(final Type fieldType) {
        this.fieldType = fieldType;
        initComponent();
    }

    public void setFieldProvider(final Supplier<AbstractField<?, String>> fieldProvider) {
        this.fieldProvider = fieldProvider;
        initComponent();
    }

    private void initComponent() {
        if (mainLayout != null) {
            remove(mainLayout);
            isUpdateLocale.set(false);
        }

        addClassName("ml-field");

        initContentField();
        initLocaleSelect();

        mainLayout = new FlexLayout();
        add(mainLayout);

        if (fieldType == Type.SINGLE) {
            mainLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
            mainLayout.setFlexWrap(FlexLayout.FlexWrap.NOWRAP);
            mainLayout.setAlignItems(Alignment.BASELINE);
            mainLayout.add(contentField);
            mainLayout.add(localeField);

        } else {
            mainLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
            mainLayout.add(localeField);
            mainLayout.add(contentField);
            if (fieldProvider != null) {
                mainLayout.getStyle().set("gap", "var(--lumo-space-xs)");
            }
            localeField.getStyle().setAlignSelf(Style.AlignSelf.END);
        }
    }

    private void initLocaleSelect() {
        localeField = new Select<>();
        localeField.setWidth("8em");
        localeField.getStyle().setFlexGrow("0").setFlexShrink("1");
        localeField.setItems(locales);

        localeField.setRenderer(new ComponentRenderer<>(locale -> {
            HorizontalLayout wrapper = new HorizontalLayout();
            wrapper.setDefaultVerticalComponentAlignment(Alignment.CENTER);
            wrapper.add(new SvgIcon("icons/%s.svg".formatted(locale)), new Span(locale));
            return wrapper;
        }));

        localeField.addValueChangeListener(e -> {
            String localizedValue = mlstr.getContent(e.getValue());
            isUpdateLocale.set(StringUtils.isNotBlank(localizedValue));
            contentField.setValue(localizedValue);
        });
    }

    private void initContentField() {
        if (fieldProvider != null) {
            contentField = fieldProvider.get();

        } else if (fieldType == Type.RTF) {
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

        contentField.getStyle().setFlexGrow("1");

        if (contentField instanceof HasSize hasSize) {
            hasSize.setWidth("100%");
        }

        contentField.addValueChangeListener(e -> {
            if (!isUpdateLocale.getAndSet(false)) {
                mlstr.addContent(localeField.getValue(), e.getValue());
                updateValue();
            }
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
        super.setReadOnly(readOnly);
        localeField.setReadOnly(readOnly);
        contentField.setReadOnly(readOnly);
    }

    @Override
    public void setValue(final MultilingualString value) {
        super.setValue(value == null ? new MultilingualString() : value);
    }
}
