'use strict';

function AgentController($log, $stateParams, $http, user) {
  'ngInject';

  var vm = this;

  vm.login = function() {
    var payload = {email: vm.email, key: vm.password};
    payload = {email: 'brandon@alicorn.io', key: 'icy'};
    
    $http.post('/api/user/agent/login', payload).then(function(res) {
      $log.log('logged in:', res);
      user.token = res.data.token;
      user.type  = 'agent';
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
