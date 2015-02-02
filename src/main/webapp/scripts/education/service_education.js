'use strict';

cvappApp.factory('Education', function ($resource) {
        return $resource('app/rest/educations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

cvappApp.factory('PublicEducations', function ($resource) {
        return $resource('public/rest/educations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
