'use strict';

import continuousTpl from './continuous.html';

function routeConfig($stateProvider) {
    'ngInject';

    $stateProvider
        .state('continuous', {
            url: '/service/:uuid',
            templateUrl: continuousTpl,
            controller: require('./continuous.controller'),
            controllerAs: 'continuous'
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
