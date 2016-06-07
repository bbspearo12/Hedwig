(function() {
    'use strict';
    angular
        .module('hedwigApp')
        .factory('Alert', Alert);

    Alert.$inject = ['$resource'];

    function Alert ($resource) {
        var resourceUrl =  'api/alerts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
