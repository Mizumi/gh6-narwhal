'use strict';

function ClientController($log, $stateParams, $http) {
  'ngInject';

  this.login = function() {
    var payload = {email: vm.email, key: vm.password};
    
    $http.post('/api/user/client/login', payload).then(function(res) {
      
      user.token = res.data.token;
      user.type  = 'client';

    });
  }

  this.register = function() {
    $http.post('/api/user/client/login').then(function(res) {

    });
  }

}

export default ClientController;
