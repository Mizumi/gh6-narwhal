'use strict';

import route from './main.route';

const mainPageModule = angular.module('main-module', [
  'ui.router'
])
.value('api', 'http://localhost:9789/api')
.value('userToken', '')
.value('userType', '');

mainPageModule
    .config(route);

export default mainPageModule;
