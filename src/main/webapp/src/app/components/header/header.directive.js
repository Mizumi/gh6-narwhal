'use strict';

import headerTpl from './header.html';

function headerComponent($log, $state) {
	'ngInject';

  var directive = {
    restrict: 'E',
    templateUrl: headerTpl,
    controller: ctrl,
    controllerAs: 'vm',
    bindToController: true
  };

  return directive;

  function ctrl () {

    var vm = this;

    //When the user clicks the logo, we are going home.
    vm.logoClick = function(){
      $state.go('main');
    }

  }

}

export default headerComponent;
