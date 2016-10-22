'use strict';

function MainController($log) {
  'ngInject';
  
  this.logger = function() {
    $log.log('heyyy');
  };

}

export default MainController;
