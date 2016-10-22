'use strict';

import cocTpl from './coc.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('coc', {
      url: '/coc/:id',
      templateUrl: cocTpl,
      controller: require('./coc.controller'),
      controllerAs: 'coc',
      resolve: {
        loadedCoc: function($stateParams, $http, user) {
          return $http.get('/api/coc/' + $stateParams.id)
          .then(function(res) {
            return res.data;
          });
        }
      }
    });

}

export default routeConfig;
