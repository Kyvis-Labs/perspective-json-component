import * as React from 'react';
import {
    Component,
    ComponentMeta,
    ComponentProps,
    PComponent,
    PropertyTree,
    SizeObject
} from '@inductiveautomation/perspective-client';
import ReactJson from '@microlink/react-json-view';

export const COMPONENT_TYPE = "kyvislabs.display.jsonview";

export interface JsonViewProps {
    value: object;
    name: string;
    theme: string;
    collapsed: boolean;
    collapseDepth: number;
    collapseStringsAfterLength: number;
    groupArraysAfterLength: number;
    displayObjectSize: boolean;
    displayDataTypes: boolean;
    enableClipboard: boolean;
    sortKeys: boolean;
    indentWidth: number;
    iconStyle: "circle" | "triangle" | "square";
    quotesOnKeys: boolean;
    enableEdit: boolean;
    enableAdd: boolean;
    enableDelete: boolean;
}

export class JsonView extends Component<ComponentProps<JsonViewProps>, any> {
    onUpdate = (data: any) => {
        this.props.store.props.write("value", data.updated_src);
        return true;
    }

    render() {
        const { props, emit } = this.props;

        const collapseValue = props.collapsed ? (props.collapseDepth < 99 ? props.collapseDepth : true) : false;

        return (
            <div {...emit()}>
                <ReactJson
                    src={props.value || {}}
                    name={props.name || "root"}
                    theme={props.theme as any || "rjv-default"}
                    collapsed={collapseValue}
                    collapseStringsAfterLength={props.collapseStringsAfterLength || false}
                    groupArraysAfterLength={props.groupArraysAfterLength || 100}
                    displayObjectSize={props.displayObjectSize !== false}
                    displayDataTypes={props.displayDataTypes !== false}
                    enableClipboard={props.enableClipboard !== false}
                    sortKeys={props.sortKeys || false}
                    indentWidth={props.indentWidth || 4}
                    iconStyle={props.iconStyle || "triangle"}
                    quotesOnKeys={props.quotesOnKeys !== false}
                    onEdit={props.enableEdit ? this.onUpdate : false}
                    onAdd={props.enableAdd ? this.onUpdate : false}
                    onDelete={props.enableDelete ? this.onUpdate : false}
                />
            </div>
        );
    }
}

export class JsonViewMeta implements ComponentMeta {
    getComponentType(): string {
        return COMPONENT_TYPE;
    }

    getViewComponent(): PComponent {
        return JsonView;
    }

    getDefaultSize(): SizeObject {
        return ({
            width: 400,
            height: 300
        });
    }

    getPropsReducer(tree: PropertyTree): JsonViewProps {
        return {
            value: tree.read("value", {}),
            name: tree.readString("name", "root"),
            theme: tree.readString("theme", "rjv-default"),
            collapsed: tree.readBoolean("collapsed", false),
            collapseDepth: tree.readNumber("collapseDepth", 99),
            collapseStringsAfterLength: tree.readNumber("collapseStringsAfterLength", 0),
            groupArraysAfterLength: tree.readNumber("groupArraysAfterLength", 100),
            displayObjectSize: tree.readBoolean("displayObjectSize", true),
            displayDataTypes: tree.readBoolean("displayDataTypes", true),
            enableClipboard: tree.readBoolean("enableClipboard", true),
            sortKeys: tree.readBoolean("sortKeys", false),
            indentWidth: tree.readNumber("indentWidth", 4),
            iconStyle: tree.readString("iconStyle", "triangle") as "circle" | "triangle" | "square",
            quotesOnKeys: tree.readBoolean("quotesOnKeys", true),
            enableEdit: tree.readBoolean("enableEdit", false),
            enableAdd: tree.readBoolean("enableAdd", false),
            enableDelete: tree.readBoolean("enableDelete", false)
        };
    }
}
