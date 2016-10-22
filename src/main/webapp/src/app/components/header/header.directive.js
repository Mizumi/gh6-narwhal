'use strict';

import headerTpl from './header.html';

function headerComponent($log, user) {
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
  }

}

export default headerComponent;
