'use strict';

import route from './continuous.route';

const continuousPageModule = angular.module('continuous-module', [
    'ui.router'
]);

continuousPageModule
    .config(route);

export default continuousPageModule;
