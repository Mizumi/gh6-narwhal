'use strict';

import preventionTpl from './prevention.html';

function routeConfig($stateProvider) {
    'ngInject';

    $stateProvider
        .state('prevention', {
            url: '/prevention',
            templateUrl: preventionTpl,
            controller: require('./prevention.controller'),
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
