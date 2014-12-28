'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/project', {
                    templateUrl: 'views/projects.html',
                    controller: 'ProjectController',
                    resolve:{
                        resolvedProject: ['Project', function (Project) {
                            return Project.query().$promise;
                        }],
                        resolvedCompany: ['Company', function (Company) {
                            return Company.query().$promise;
                        }],
                        resolvedTechnology: ['Technology', function (Technology) {
                            return Technology.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
