'use strict';

function AgentController($log, $state, $stateParams, $http, user, $scope, loadedCocs) {
  'ngInject';

  var vm = this;

  vm.user = user;
  vm.loadedCocs = loadedCocs;
  vm.active = 'organizations';

  vm.cocs = [
    {name: 'coc 1'},
    {name: 'coc 2'}
  ];

  vm.login = function() {
    var payload = {email: vm.email, key: vm.password};
    payload = {email: 'brandon@alicorn.io', key: 'icy'};
    
    $http.post('/api/user/agent/login', payload).then(function(res) {

      user.token = res.data.token;
      user.data = res.data.user;
      user.type  = 'agent';

    });
  };

  vm.register = function() {
    var payload = {email: vm.email, key: vm.password, fir};
    console.log(payload);
    $http.post('/api/user/agent/login', payload).then(function(res) {
      $log('registered:', res);
    });
  };

  vm.addOrg = function() {
    vm.loadedCocs.push({contactInfo: {name: vm.orgName}})
  };

  vm.makeCoc = function() {
    var payload = {
      email: vm.email, 
      key: user.token
    };
    console.log(payload);
    $http.post('/api/cocs', payload).then(function(res) {
      $log('new coc:', res);
    });
  };

  vm.save = function() {
    console.log('fake save');
    // var payload = {
    //   token: user.token,
    //   uuid: user.data.uuid
    // }
    // $http.post('/api/user/agents', payload).then(function(res) {
    // });
  };

  // Redirect home if user is not an agent
  $scope.$watch('agent.user.type', function(val) {
    if (!user.token) return;
    if (val !== 'agent') {
      $state.go('main');
    }
  });

}

export default AgentController;
