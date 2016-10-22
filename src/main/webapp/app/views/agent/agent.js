'use strict';

angular.module('myApp.agent', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/agent', {
    templateUrl: '/views/agent/agent.html',
    controller: 'agentCtrl'
  });
}])

.controller('View2Ctrl', [function() {

}]);