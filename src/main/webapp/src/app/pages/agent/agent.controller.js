'use strict';

function AgentController($log, $stateParams, $http, user, $scope) {
  'ngInject';

  var vm = this;

  vm.login = function() {
    var payload = {email: vm.email, key: vm.password};
    payload = {email: 'brandon@alicorn.io', key: 'icy'};

    console.log('logging in');
    
    $http.post('/api/user/agent/login', payload).then(function(res) {

      console.log(user);
      
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
