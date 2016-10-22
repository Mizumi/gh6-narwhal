'use strict';

angular.module('myApp.client', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/client', {
    templateUrl: '/views/client/client.html',
    controller: 'clientCtrl'
  });
}])

.controller('clientCtrl', [function() {

}]);