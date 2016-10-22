'use strict';

import clientTpl from './client.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('client', {
      url: '/client/:id',
      templateUrl: clientTpl,
      controller: require('./client.controller'),
      controllerAs: 'client',
    });

}

export default routeConfig;
