'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/other', {
                    templateUrl: 'views/others.html',
                    controller: 'OtherController',
                    resolve:{
                        resolvedOther: ['Other', function (Other) {
                            return Other.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
