package com.kyvislabs.jsonview.designer;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.ignition.designer.model.AbstractDesignerModuleHook;
import com.inductiveautomation.ignition.designer.model.DesignerContext;
import com.inductiveautomation.perspective.designer.DesignerComponentRegistry;
import com.inductiveautomation.perspective.designer.api.PerspectiveDesignerInterface;
import com.kyvislabs.jsonview.common.component.display.JsonView;

public class JsonViewDesignerHook extends AbstractDesignerModuleHook {
    private static final LoggerEx logger = LoggerEx.newBuilder().build("JsonViewComponents");

    private DesignerContext context;
    private DesignerComponentRegistry registry;

    public JsonViewDesignerHook() {
        logger.info("Registering JsonView component in Designer!");
    }

    @Override
    public void startup(DesignerContext context, LicenseState activationState) {
        this.context = context;
        init();
    }

    private void init() {
        logger.debug("Initializing registry entrants...");

        PerspectiveDesignerInterface pdi = PerspectiveDesignerInterface.get(context);
        registry = pdi.getDesignerComponentRegistry();

        registry.registerComponent(JsonView.DESCRIPTOR);
    }

    @Override
    public void shutdown() {
        registry.removeComponent(JsonView.COMPONENT_ID);
    }
}
