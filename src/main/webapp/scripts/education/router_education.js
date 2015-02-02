'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/education', {
                    templateUrl: 'views/educations.html',
                    controller: 'EducationController',
                    resolve:{
                        resolvedEducation: ['Education', function (Education) {
                            return Education.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
