'use strict';

import route from './prevention.route';

const preventionPageModule = angular.module('prevention-module', [
    'ui.router'
]);

preventionPageModule
    .config(route);

export default preventionPageModule;
