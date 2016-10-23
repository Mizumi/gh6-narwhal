'use strict';

function CocController($log, $stateParams, $http, loadedCoc, user, $scope) {
  'ngInject';

  var vm = this;
  vm.user = user;
  vm.loadedCoc = loadedCoc;
  console.log(loadedCoc);
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
  $scope.$watch('coc.maritalStatus', function(val) {
    if (val === 'single') {
      $('.marital-status-radio[data-single]').addClass('active');
      $('.marital-status-radio[data-married]').removeClass('active');

      $('.marital-status-radio[data-single]>input').attr('checked', '');
      $('.marital-status-radio[data-married]>input').attr('checked', null);
    } else {
      $('.marital-status-radio[data-single]').removeClass('active');
      $('.marital-status-radio[data-married]').addClass('active');

      $('.marital-status-radio[data-married]>input').attr('checked', '');
      $('.marital-status-radio[data-single]>input').attr('checked', null);
    }
  });
  vm.maritalStatus = 'single';
}

export default CocController;
