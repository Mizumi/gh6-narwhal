'use strict';

function ClientController($log, $stateParams, $http) {
  'ngInject';

  this.login = function() {
    $http.post('/api/user/client/login').then(function(res) {

    });
  }

  this.register = function() {
    $http.post('/api/user/client/login').then(function(res) {

    });
  }

}

export default ClientController;
