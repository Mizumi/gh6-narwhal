'use strict';

import mainTpl from './main.html';
import mainCtrl from './main.controller.js';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('main', {
      url: '/',
      templateUrl: mainTpl,
      controller: mainCtrl,
      controllerAs: 'main'
    });

}

export default routeConfig;
