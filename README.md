# Perspective JSON Component

An Ignition Perspective module that adds an interactive JSON viewer component powered by [@microlink/react-json-view](https://github.com/nicedoc/react-json-view).

![Ignition](https://img.shields.io/badge/Ignition-8.3.0%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## Features

- Interactive, collapsible JSON tree view
- 30+ built-in themes
- Copy-to-clipboard support
- Configurable collapse depth
- `onUpdate`, `onAdd`, and `onDelete` event callbacks
- Customizable display options (data types, object sizes, sort keys, etc.)

## Installation

1. Download the latest `.modl` file from the [Releases](../../releases) page (or build from source).
2. In the Ignition Gateway, go to **Config > Modules** and install the module.
3. The `jsonview` component will be available in the Perspective component palette under **Display**.

## Component Properties

| Property | Type | Default | Description |
|---|---|---|---|
| `value` | object | `{}` | The JSON data to display |
| `theme` | enum | `monokai` | Color theme (30+ options) |
| `name` | string | `root` | Root node label |
| `collapsed` | boolean | `false` | Collapse all nodes by default |
| `collapseDepth` | integer | `1` | Default expand depth |
| `displayObjectSize` | boolean | `true` | Show object/array sizes |
| `displayDataTypes` | boolean | `true` | Show data type labels |
| `enableClipboard` | boolean | `true` | Enable copy-to-clipboard |
| `sortKeys` | boolean | `false` | Sort object keys alphabetically |
| `indentWidth` | integer | `4` | Indentation width in pixels |
| `iconStyle` | enum | `triangle` | Expand/collapse icon style (`circle`, `triangle`, `square`) |
| `quotesOnKeys` | boolean | `true` | Show quotes around keys |
| `style` | object | `{}` | Custom CSS styles for the root container |

## Events

| Event | Description |
|---|---|
| `onUpdate` | Fired when a value is edited |
| `onAdd` | Fired when a new key/value is added |
| `onDelete` | Fired when a key/value is deleted |

## Building from Source

### Prerequisites

- Java 17+
- Node.js 20+
- Yarn 1.x

### Build

```bash
./gradlew build
```

The unsigned module file will be at `build/JsonViewComponent.unsigned.modl`.

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
