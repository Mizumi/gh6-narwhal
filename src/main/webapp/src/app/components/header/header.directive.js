'use strict';

import headerTpl from './header.html';

function headerComponent($log) {
	'ngInject';

  var directive = {
    restrict: 'E',
    templateUrl: headerTpl,
    controller: ctrl,
    controllerAs: 'vm',
    bindToController: true
  };

  return directive;

  function ctrl () {}

}

export default headerComponent;
