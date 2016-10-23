'use strict';

import emergencyTpl from './emergency.html';

function routeConfig($stateProvider) {
    'ngInject';

    $stateProvider
        .state('emergency', {
            url: '/service/:uuid',
            templateUrl: emergencyTpl,
            controller: require('./emergency.controller'),
            controllerAs: 'emergency'
            //resolve: {
            //    loadedCoc: function($stateParams, $http, user) {
            //        return $http.get('/api/cocs/' + $stateParams.uuid, {timeout: 5000})
            //            .then(function(res) {
            //                return res.data.coc;
            //            }).catch(function(res) {
            //                return null;
            //            });
            //    }
            //}
        });

}

export default routeConfig;
