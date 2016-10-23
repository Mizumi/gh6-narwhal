'use strict';

import route from './client.route';

const clientPageModule = angular.module('client-module', [
  'ui.router'
]);

clientPageModule
    .config(route);

export default clientPageModule;
