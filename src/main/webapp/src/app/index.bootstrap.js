'use strict';

// index.html page to dist folder
import '!!file-loader?name=[name].[ext]!../favicon.ico';

// main App module
import "./index.module";

import "../assets/styles/sass/index.scss";
import "../assets/styles/sass/coc.scss";
import "../assets/styles/sass/client.scss";
import "../assets/styles/sass/main.scss";
import "../assets/styles/sass/header.scss";

angular.element(document).ready(function () {
  angular.bootstrap(document, ['app'], {
    strictDi: true
  });
});
