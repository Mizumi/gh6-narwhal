'use strict';

import cocTpl from './coc.html';

function routeConfig($stateProvider) {
  'ngInject';

  $stateProvider
    .state('coc', {
      url: '/coc/:uuid',
      templateUrl: cocTpl,
      controller: require('./coc.controller'),
      controllerAs: 'coc',
      resolve: {
        loadedCoc: function($stateParams, $http, user) {
          return $http.get('/api/cocs/' + $stateParams.uuid, {timeout: 5000})
          .then(function(res) {
            return res.data.coc;
          }).catch(function(res) {
            return null;
          });
        }
      }
    });

}

export default routeConfig;
