'use strict';

import agentTpl from './agent.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('agent', {
      url: '/agent/:id',
      templateUrl: agentTpl,
      controller: require('./agent.controller'),
      controllerAs: 'ctrl'
    });

}

export default routeConfig;
