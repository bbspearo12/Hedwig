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
        console.log("Get by file");
    	$http.get('api/a-sup-alert-data/asupfile/' + $stateParams.id +'/?filename='+vm.filename)
        .then(function (response){
        	vm.aSUPAlertData = angular.fromJson(response.data);
        	console.dir(JSON.stringify(vm.aSUPAlertData));
        	//$state.go("a-sup-alert-data-detail",{"id": vm.aSUPAlertData.id});
        	vm.asup = vm.aSUPAlertData.asup_alert_file_data;
            vm.asup_html = $sce.trustAsHtml(vm.asup);
            vm.byteSize = DataUtils.byteSize;
            vm.openFile = DataUtils.openFile;
        	
        });
    	
    }
})();