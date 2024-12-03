package gr.netmechanics.jmix.mlf.kit;

import static io.jmix.flowui.kit.meta.StudioProperty.Category.DATA_BINDING;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.GENERAL;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.LOOK_AND_FEEL;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.POSITION;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.SIZE;

import gr.netmechanics.jmix.mlf.component.MultilingualField;
import io.jmix.flowui.kit.meta.StudioComponent;
import io.jmix.flowui.kit.meta.StudioElement;
import io.jmix.flowui.kit.meta.StudioPropertiesBinding;
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
        classFqn = "gr.netmechanics.jmix.mlf.component.MultilingualField",
        category = "Components",
        xmlElement = "multilingualField",
        xmlns = "http://schemas.netmechanics.gr/jmix/ui",
        xmlnsAlias = "nm",
        icon = "gr/netmechanics/jmix/mlf/kit/meta/icon/component/mlstr.svg",
        properties = {
            @StudioProperty(xmlAttribute = "id", category = GENERAL, type = StudioPropertyType.COMPONENT_ID),
            @StudioProperty(xmlAttribute = "visible", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "colspan", category = POSITION, type = StudioPropertyType.INTEGER),
            @StudioProperty(xmlAttribute = "alignSelf", category = POSITION, type = StudioPropertyType.ENUMERATION,
                classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$Alignment",
                defaultValue = "AUTO",
                options = {"START", "END", "CENTER", "STRETCH", "BASELINE", "AUTO"}),
            @StudioProperty(xmlAttribute = "css", category = LOOK_AND_FEEL, type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "enabled", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "readOnly", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "width", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "height", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "maxHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "maxWidth", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "minHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "minWidth", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "ariaLabel", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "ariaLabelledBy", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "classNames", category = LOOK_AND_FEEL, type = StudioPropertyType.VALUES_LIST),
            @StudioProperty(xmlAttribute = "label", category = GENERAL, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "helperText", category = GENERAL, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "dataContainer", category = DATA_BINDING,
                type = StudioPropertyType.COLLECTION_OR_INSTANCE_DATA_CONTAINER_REF),
            @StudioProperty(xmlAttribute = "property", category = DATA_BINDING, type = StudioPropertyType.PROPERTY_REF),
            @StudioProperty(xmlAttribute = "fieldType", category = GENERAL, type = StudioPropertyType.ENUMERATION,
                classFqn = "gr.netmechanics.jmix.mlf.component.MultilingualField$Type",
                defaultValue = "SINGLE",
                options = {"SINGLE", "MULTI", "RTF"}),
            @StudioProperty(xmlAttribute = "multilineHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "multilineMinHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "multilineMaxHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
        },
        propertiesBindings = {
            @StudioPropertiesBinding(source = "dataContainer", item = "property")
        }
    )
    MultilingualField multilingualField();

    @StudioElement(
        name = "MultilingualStringRenderer",
        classFqn = "gr.netmechanics.jmix.mlf.component.MultilingualStringRenderer",
        xmlElement = "multilingualStringRenderer",
        target = {"com.vaadin.flow.component.grid.Grid.Column"},
        unsupportedTarget = {"io.jmix.flowui.kit.component.grid.EditorActionsColumn"},
        xmlns = "http://schemas.netmechanics.gr/jmix/ui",
        xmlnsAlias = "nm"
    )
    void multilingualStringRenderer();
}
