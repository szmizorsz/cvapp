'use strict';

cvappApp.factory('Languages', function ($resource) {
        return $resource('app/rest/languages/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

cvappApp.factory('PublicLanguages', function ($resource) {
        return $resource('public/rest/languages/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
