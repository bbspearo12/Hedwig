(function() {
	'use strict';
	angular
	.module('hedwigApp')
	.factory('ASUPAlertData', ASUPAlertData);

	ASUPAlertData.$inject = ['$resource'];

	function ASUPAlertData ($resource) {
		var resourceUrl =  'api/a-sup-alert-data/:id';

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

