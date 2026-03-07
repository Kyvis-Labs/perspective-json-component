package com.kyvislabs.jsonview.common.component.display;

import com.inductiveautomation.ignition.common.jsonschema.JsonSchema;
import com.inductiveautomation.perspective.common.api.ComponentDescriptor;
import com.inductiveautomation.perspective.common.api.ComponentDescriptorImpl;
import com.kyvislabs.jsonview.common.JsonViewComponents;

public class JsonView {

    public static String COMPONENT_ID = "kyvislabs.display.jsonview";

    public static JsonSchema SCHEMA =
        JsonSchema.parse(JsonViewComponents.class.getResourceAsStream("/jsonview.props.json"));

    public static ComponentDescriptor DESCRIPTOR = ComponentDescriptorImpl.ComponentBuilder.newBuilder()
        .setPaletteCategory(JsonViewComponents.COMPONENT_CATEGORY)
        .setId(COMPONENT_ID)
        .setModuleId(JsonViewComponents.MODULE_ID)
        .setSchema(SCHEMA)
        .setName("JSON View")
        .addPaletteEntry("", "JSON View", "An interactive JSON viewer component.", null, null)
        .setDefaultMetaName("jsonView")
        .setResources(JsonViewComponents.BROWSER_RESOURCES)
        .build();
}
