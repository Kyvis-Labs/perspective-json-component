package com.kyvislabs.jsonview.common.component.display;

import com.inductiveautomation.ignition.common.gson.JsonObject;
import com.inductiveautomation.ignition.common.jsonschema.JsonSchema;
import com.inductiveautomation.perspective.common.api.ComponentDescriptor;
import com.inductiveautomation.perspective.common.api.ComponentDescriptorImpl;
import com.kyvislabs.jsonview.common.JsonViewComponents;

public class JsonView {

    public static String COMPONENT_ID = "kyvislabs.display.jsonview";

    public static JsonSchema SCHEMA =
        JsonSchema.parse(JsonViewComponents.class.getResourceAsStream("/jsonview.props.json"));

    private static JsonObject createIcon() {
        JsonObject icon = new JsonObject();
        icon.addProperty("path", String.format("/res/%s/img/jsonview-icon.svg", JsonViewComponents.URL_ALIAS));
        return icon;
    }

    public static ComponentDescriptor DESCRIPTOR = ComponentDescriptorImpl.ComponentBuilder.newBuilder()
        .setPaletteCategory(JsonViewComponents.COMPONENT_CATEGORY)
        .setId(COMPONENT_ID)
        .setModuleId(JsonViewComponents.MODULE_ID)
        .setSchema(SCHEMA)
        .setName("JSON View")
        .addPaletteEntry("", "JSON View", "An interactive JSON viewer component.", null, createIcon())
        .setDefaultMetaName("jsonView")
        .setResources(JsonViewComponents.BROWSER_RESOURCES)
        .build();
}
