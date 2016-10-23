'use strict';

import agentTpl from './agent.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('agent', {
      url: '/agent/:id',
      templateUrl: agentTpl,
      controller: require('./agent.controller'),
      controllerAs: 'agent',
      resolve: {
        loadedCocs: function($stateParams, $http, user) {
          return $http.get('/api/cocs', {timeout: 5000})
          .then(function(res) {
            return res.data.cocs;
          }).catch(function(res) {
            return null;
          });
        }
      }
    });

}

export default routeConfig;
