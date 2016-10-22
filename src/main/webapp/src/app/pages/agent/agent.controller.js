'use strict';

function AgentController($log, $state, $stateParams, $http, user, $scope) {
  'ngInject';

  var vm = this;

  vm.user = user;

  vm.cocs = [
    {name: 'coc 1'},
    {name: 'coc 2'}
  ];

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
  };

  // Redirect home if user is not an agent
  $scope.$watch('agent.user.type', function(val) {
    if (!user.token) return;
    if (val !== 'agent') {
      $state.go('main');
    }
  })

  vm.login();

}

export default AgentController;
