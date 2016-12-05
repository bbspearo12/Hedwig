
(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertByASUPDataController', ASUPAlertByASUPDataController);

    ASUPAlertByASUPDataController.$inject = ['$http', '$stateParams','$location', '$scope', '$state', 'DataUtils' ];

    function ASUPAlertByASUPDataController ($http, $stateParams, $location, $scope, $state, DataUtils) {
    	var vm = this;
        vm.hostname = $location.search()['hostname'];
        vm.id = $stateParams.id;
        $http.get('api/a-sup-alert-data/asup/' + $stateParams.id)
        .then(function (response){
        	vm.aSUPAlertData = angular.fromJson(response.data);
        });
        $scope.inset = function (id, hostname, filename) {
        	var url = '/api/a-sup-alert-data/asupfile/' + id +'?hostname='+hostname+'&filename='+filename
        	$http.get(url)
        	.then(function (response) {
        		vm.asupAlertData = angular.fromJson(response.data);
        		vm.asupAlertFileData = vm.asupAlertData.asup_alert_file_data;
        	});
        }
    }
})();
