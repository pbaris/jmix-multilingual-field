package com.pbaris.jmix.mlf.kit;

import com.pbaris.jmix.mlf.component.MultilingualField;
import io.jmix.flowui.kit.meta.StudioComponent;
import io.jmix.flowui.kit.meta.StudioProperty;
import io.jmix.flowui.kit.meta.StudioPropertyType;
import io.jmix.flowui.kit.meta.StudioUiKit;

/**
 * @author Panos Bariamis (pbaris)
 */
@StudioUiKit
public interface StudioComponents {

    @StudioComponent(
        name = "MultilingualField",
        classFqn = "com.pbaris.jmix.mlf.component.MultilingualField",
        category = "Components",
        xmlElement = "multilingualField",
        xmlns = "http://schemas.pbaris.com/jmix/ui",
        xmlnsAlias = "pb",
        icon = "com/pbaris/jmix/mlf/kit/meta/icon/component/mlstr.svg",
        properties = {
            @StudioProperty(xmlAttribute = "id", type = StudioPropertyType.COMPONENT_ID),
            @StudioProperty(xmlAttribute = "visible", type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "colspan", type = StudioPropertyType.INTEGER),
            @StudioProperty(xmlAttribute = "alignSelf", type = StudioPropertyType.ENUMERATION,
                classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$Alignment",
                defaultValue = "AUTO",
                options = {"START", "END", "CENTER", "STRETCH", "BASELINE", "AUTO"}),
            @StudioProperty(xmlAttribute = "css", type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "enabled", type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "readOnly", type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "width", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "height", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "maxHeight", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "maxWidth", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "minHeight", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "minWidth", type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "ariaLabel", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "ariaLabelledBy", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "classNames", type = StudioPropertyType.VALUES_LIST),
            @StudioProperty(xmlAttribute = "label", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "helperText", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "dataContainer", type = StudioPropertyType.DATA_CONTAINER_REF),
            @StudioProperty(xmlAttribute = "property", type = StudioPropertyType.PROPERTY_REF),
            @StudioProperty(xmlAttribute = "fieldType", type = StudioPropertyType.ENUMERATION,
                classFqn = "com.pbaris.jmix.mlf.component.MultilingualField$Type",
                defaultValue = "SINGLE",
                options = {"SINGLE", "MULTI", "RTF"}),
            @StudioProperty(xmlAttribute = "localeMode", type = StudioPropertyType.ENUMERATION,
                classFqn = "com.pbaris.jmix.mlf.locales.LocaleMode",
                defaultValue = "SYSTEM",
                options = {"SYSTEM", "USER"}),
            @StudioProperty(xmlAttribute = "multilineHeight", type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "multilineMinHeight", type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "multilineMaxHeight", type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
        }
    )
    MultilingualField multilingualField();
}
