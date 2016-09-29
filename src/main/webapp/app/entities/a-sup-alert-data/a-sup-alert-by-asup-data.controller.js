
(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertByASUPDataController', ASUPAlertByASUPDataController);

    ASUPAlertByASUPDataController.$inject = ['$http', '$stateParams','$location', '$scope', '$state', 'DataUtils' ];

    function ASUPAlertByASUPDataController ($http, $stateParams, $location, $scope, $state, DataUtils) {
    	var vm = this;
        vm.hostname = $location.search()['hostname'];
        $http.get('api/a-sup-alert-data/asup/' + $stateParams.id)
        .then(function (response){
        	vm.aSUPAlertData = angular.fromJson(response.data);
        });
    }
})();
