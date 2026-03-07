import { ComponentMeta, ComponentRegistry } from '@inductiveautomation/perspective-client';
import { JsonView, JsonViewMeta } from './components/JsonView';

export { JsonView };

const components: Array<ComponentMeta> = [
    new JsonViewMeta()
];

components.forEach((c: ComponentMeta) => ComponentRegistry.register(c));
