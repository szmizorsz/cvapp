'use strict';

cvappApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/knowledge', {
                    templateUrl: 'views/knowledges.html',
                    controller: 'KnowledgeController',
                    resolve:{
                        resolvedKnowledge: ['Knowledge', function (Knowledge) {
                            return Knowledge.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
