'use strict';

import route from './coc.route';

const cocPageModule = angular.module('coc-module', [
  'ui.router'
]);

cocPageModule
    .config(route);

export default cocPageModule;
