'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/company', {
                    templateUrl: 'views/companys.html',
                    controller: 'CompanyController',
                    resolve:{
                        resolvedCompany: ['Company', function (Company) {
                            return Company.query().$promise;
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
