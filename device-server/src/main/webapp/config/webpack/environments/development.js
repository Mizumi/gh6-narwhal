'use strict';
var webpack = require('webpack');

module.exports = function(_path) {
  return {
    context: _path,
    debug: true,
    devtool: 'cheap-source-map',
    // devServer: {
    //   contentBase: './dist',
    //   info: true,
    //   hot: true,
    //   inline: true,
    //   proxy: {
    //     '/api': {
    //       target: '192.168.1.93:9789',
    //       secure: false
    //     }
    //   },
    // },
    plugins: [
      new webpack.HotModuleReplacementPlugin()
    ]
  };
};
