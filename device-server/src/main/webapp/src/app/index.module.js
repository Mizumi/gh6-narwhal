'use strict';

import * as components from './index.components';
import config from './index.config';
import run from './index.run';


const App = angular.module(
  "app", [
    // plugins
    require('angular-ui-router'),
    "ngAnimate", 
	"ngCookies", 
	"ngTouch", 
	"ngSanitize", 
	"ngMessages", 
	"ngAria", 
	"oc.lazyLoad",

    // core
    require("./core/core.module").name,

    // components
    require("./index.components").name,

    // routes
    require("./index.routes").name,

    // pages
    require("./pages/main/main.module").name,
    require("./pages/client/client.module").name,
    require("./pages/agent/agent.module").name,
    require("./pages/coc/coc.module").name,
    require("./pages/emergency/emergency.module").name,
    require("./pages/prevention/prevention.module").name,
    require("./pages/continuous/continuous.module").name

  ]
)
.value('user', {})

App
  .config(config)
  .run(run);



export default App;
