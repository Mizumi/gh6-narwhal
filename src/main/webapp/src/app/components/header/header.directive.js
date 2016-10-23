'use strict';

import headerTpl from './header.html';

function headerComponent($log, $state, user) {
	'ngInject';

  var directive = {
    restrict: 'E',
    templateUrl: headerTpl,
    controller: ctrl,
    controllerAs: 'ctrl',
    bindToController: true
  };

  return directive;

  function ctrl () {
    var vm = this;

    vm.user = user;

      //When the user clicks the logo, we are going home.
      vm.logoClick = function(){
          $state.go('main');
      }
  }

}

export default headerComponent;
