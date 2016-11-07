/**
 * 
 */
(function() {
    'use strict';
    angular
        .module('hedwigApp')
        .controller('ASUPAlertByASUPFileDataController', ASUPAlertByASUPFileDataController);

    ASUPAlertByASUPFileDataController.$inject = ['$sce', '$http', '$stateParams','$location', '$scope', '$state', 'DataUtils' ];

    function ASUPAlertByASUPFileDataController ($sce, $http, $stateParams, $location, $scope, $state, DataUtils) {

    	var vm = this;
        vm.hostname = $location.search()['hostname'];
        vm.filename = $location.search()['filename'];
        
    	$http.get('api/a-sup-alert-data/asupfile/' + $stateParams.id +'/?filename='+vm.filename)
        .then(function (response){
        	vm.aSUPAlertData = angular.fromJson(response.data);
        	vm.asup = vm.aSUPAlertData.asup_alert_file_data;
        	vm.id = $stateParams.id;
        	vm.asup_html = $sce.trustAsHtml(vm.asup);
            vm.byteSize = DataUtils.byteSize;
            vm.openFile = DataUtils.openFile;
        	
        }, function (response){
        	
        	if (response.status === 404) {
        		vm.asup = "This file not populated in this ASUP";
        	}
        });
    	
    }
})();