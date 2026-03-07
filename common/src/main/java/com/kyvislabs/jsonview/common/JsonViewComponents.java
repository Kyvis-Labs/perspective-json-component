package com.kyvislabs.jsonview.common;

import java.util.Set;

import com.inductiveautomation.perspective.common.api.BrowserResource;

public class JsonViewComponents {

    public static final String MODULE_ID = "com.kyvislabs.jsonview";
    public static final String URL_ALIAS = "jsonview";
    public static final String COMPONENT_CATEGORY = "Display";
    public static final Set<BrowserResource> BROWSER_RESOURCES =
        Set.of(
            new BrowserResource(
                "jsonview-components-js",
                String.format("/res/%s/JsonViewComponents.js", URL_ALIAS),
                BrowserResource.ResourceType.JS
            )
        );
}
