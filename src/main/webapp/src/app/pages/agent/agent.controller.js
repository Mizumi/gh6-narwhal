'use strict';

function AgentController($log, $stateParams, $http) {
  'ngInject';

  var vm = this;

  vm.login = function() {
    var payload = {email: vm.email, key: vm.password};
    payload = {email: 'brandon@alicorn.io', key: 'icy'};
    console.log(payload);
    
    $http.post('/api/user/agent/login', payload).then(function(res) {
      $log('logged in:', res);
    });
  };

  vm.register = function() {
    var payload = {email: vm.email, key: vm.password};
    console.log(payload);
    $http.post('/api/user/agent/login', payload).then(function(res) {
      $log('registered:', res);
    });
  };1

}

export default AgentController;
