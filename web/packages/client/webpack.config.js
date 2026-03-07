const webpack = require('webpack'),
    path = require('path'),
    fs = require('fs'),
    AfterBuildPlugin = require('@fiverr/afterbuild-webpack-plugin');

const LibName = "JsonViewComponents";

function copyToResources() {
    const generatedResourcesDir = path.resolve(__dirname, '../..', 'build/generated-resources/mounted/');
    const jsToCopy = path.resolve(__dirname, "dist/", `${LibName}.js`);
    const jsResourcePath = path.resolve(generatedResourcesDir, `${LibName}.js`);

    if (!fs.existsSync(generatedResourcesDir)) {
        fs.mkdirSync(generatedResourcesDir, { recursive: true });
    }

    try {
        fs.access(jsToCopy, fs.constants.R_OK, (err) => {
            if (!err) {
                fs.createReadStream(jsToCopy)
                    .pipe(fs.createWriteStream(jsResourcePath));
            } else {
                console.log(`Error when attempting to copy ${jsToCopy} into ${jsResourcePath}`);
            }
        });
    } catch (err) {
        console.error(err);
        throw err;
    }
}

const config = {
    entry: {
        JsonViewComponents: path.join(__dirname, "./typescript/jsonview-client-components.ts")
    },

    output: {
        library: [LibName],
        path: path.join(__dirname, "dist"),
        filename: `${LibName}.js`,
        libraryTarget: "umd",
        umdNamedDefine: true
    },

    devtool: "source-map",

    resolve: {
        extensions: [".jsx", ".js", ".ts", ".tsx", ".d.ts", ".css"],
        modules: [
            path.resolve(__dirname, "../../node_modules")
        ]
    },

    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: {
                    loader: 'ts-loader',
                    options: {
                        transpileOnly: false
                    }
                },
                exclude: /node_modules/,
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },

    plugins: [
        new AfterBuildPlugin(function(stats) {
            copyToResources();
        })
    ],

    externals: {
        "react": "React",
        "react-dom": "ReactDOM",
        "mobx": "mobx",
        "mobx-react": "mobxReact",
        "@inductiveautomation/perspective-client": "PerspectiveClient"
    }
};

module.exports = () => config;
