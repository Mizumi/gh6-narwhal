'use strict';

angular.module('myApp.coc', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/coc', {
    templateUrl: '/views/coc/coc.html',
    controller: 'cocCtrl'
  });
}])

.controller('cocCtrl', [function() {

}]);