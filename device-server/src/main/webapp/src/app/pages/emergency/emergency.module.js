'use strict';

import route from './emergency.route';

const emergencyPageModule = angular.module('emergency-module', [
    'ui.router'
]);

emergencyPageModule
    .config(route);

export default emergencyPageModule;
