'use strict';

function CocController($log, $stateParams) {
  'ngInject';

  var vm = this;

  $http.get('/api/coc/' + $stateParams.id, {token: user.token || ''}).then(function(res) {
    this.coc = res.data.coc;
  })
}

export default CocController;
