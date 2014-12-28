'use strict';

cvappApp.factory('Technology', function ($resource) {
        return $resource('app/rest/technologys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

