'use strict';

cvappApp.factory('Other', function ($resource) {
        return $resource('app/rest/others/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

cvappApp.factory('PublicOthers', function ($resource) {
        return $resource('public/rest/others/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
