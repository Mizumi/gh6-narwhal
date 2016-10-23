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
      resolve: {
        loadedClient: function($stateParams, $http, user) {
          if (user.type === 'agent') {
            return $http.get('/api/user/client/' + $stateParams.id);
          }
          return user.data;
        }
      }
    });

}

export default routeConfig;
