'use strict';

function PreventionController($log, $stateParams, $http, user, $scope) {
    'ngInject';

    var vm = this;
    vm.loadedCoc = null;

    vm.listOfCOC = [];

    //Call backend to get the list of cock, and we are going to ng-repeat off of this.
    var sampleCocService = {
        category: 'emergency',
        name: 'St. Patrick Center',
        address: '800 N Tucker Blvd, St. Louis, MO 63101',
        phoneNumber: '(314) 802-0700',
        hours: 'Mon-Sun 6am - 10pm',
        services: ['food', 'shelter', 'limited health']
    };


    // TODO: this is for testing purposes only ! REMOVE BEFORE FLIGHT!!!!!!!!!
    for(var x=0; x < 10; x++){
        vm.listOfCOC.push(sampleCocService);
    }

    //vm.user = user;
    //vm.loadedCoc = loadedCoc;
    //console.log(loadedCoc);
    //vm.selectedClient = null;
    //vm.showClientAdder = null;
    //vm.ssnArea = '';
    //vm.active = 'overview';

    vm.logger = function(a) {
        console.log(a);
    };

    //Functions here
}

export default PreventionController;
