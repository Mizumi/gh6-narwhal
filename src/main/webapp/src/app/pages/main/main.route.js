'use strict';

import mainTpl from './main.html';
import mainCtrl from './main.controller.js';

console.log(mainCtrl);

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('main', {
      url: '/',
      templateUrl: mainTpl,
      controller: mainCtrl,
      controllerAs: 'ctrl'
    });

}

export default routeConfig;
