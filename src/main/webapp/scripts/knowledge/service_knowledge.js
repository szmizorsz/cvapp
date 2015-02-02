'use strict';

cvappApp.factory('Knowledge', function ($resource) {
        return $resource('app/rest/knowledges/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });

cvappApp.factory('PublicKnowledges', function ($resource) {
        return $resource('public/rest/knowledges/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
