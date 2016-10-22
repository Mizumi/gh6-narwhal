'use strict';

function AgentController($log, $stateParams, $http, api) {
  'ngInject';

  var vm = this;
  console.log('ctrl');

  vm.login = function() {
    var payload = {email: vm.email, password: vm.password};
    console.log(payload);
    
    $http.post(api + '/user/agent/login', payload).then(function(res) {
      $log('logged in:', res);
    });
  };

  vm.register = function() {
    var payload = {email: vm.email, password: vm.password};
    console.log(payload);
    $http.post('/api/user/agent/login', payload).then(function(res) {
      $log('registered:', res);
    });
  };1

}

export default AgentController;
