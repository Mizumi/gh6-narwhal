'use strict';

import cocTpl from './coc.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('coc', {
      url: '/coc/:id',
      templateUrl: cocTpl,
      controller: require('./coc.controller'),
      controllerAs: 'ctrl',
    });

}

export default routeConfig;
