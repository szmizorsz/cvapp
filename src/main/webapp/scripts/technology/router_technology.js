'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/technology', {
                    templateUrl: 'views/technologys.html',
                    controller: 'TechnologyController',
                    resolve:{
                        resolvedTechnology: ['Technology', function (Technology) {
                            return Technology.query().$promise;
                        }],
                        resolvedProject: ['Project', function (Project) {
                            return Project.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
