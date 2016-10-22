'use strict';

function CocController($log, $stateParams, $http, loadedCoc, user, $scope) {
  'ngInject';

  var vm = this;
  vm.user = user;
  vm.loadedCoc = loadedCoc;
  vm.selectedClient = null;
  vm.showClientAdder = true;
  vm.ssnArea = '';
  
  vm.logger = function(a) {
    console.log(a);
  }

  $scope.$watch('coc.ssnArea', function(val, old) {
    if (!val || !old) return;
    if (old.length === 2 && val.length === 3) {
      $('#SSNGroup').focus();
    }
  });
  $scope.$watch('coc.ssnGroup', function(val, old) {
    if (!val || !old) return;
    if (old.length === 1 && val.length === 2) {
      $('#SSNSerial').focus();
    }
  });
}

export default CocController;
