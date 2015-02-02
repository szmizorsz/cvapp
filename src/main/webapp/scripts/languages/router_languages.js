'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/languages', {
                    templateUrl: 'views/languagess.html',
                    controller: 'LanguagesController',
                    resolve:{
                        resolvedLanguages: ['Languages', function (Languages) {
                            return Languages.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
