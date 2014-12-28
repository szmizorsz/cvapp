'use strict';

cvappApp.factory('Company', function ($resource) {
        return $resource('app/rest/companys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

cvappApp.factory('PublicCompanyWithProjects', function ($resource) {
        return $resource('public/rest/companys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
