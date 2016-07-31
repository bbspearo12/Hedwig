(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertController', AlertController);

    AlertController.$inject = ['$scope', '$state', 'DataUtils', 'Alert'];
    
    function AlertController ($scope, $state, DataUtils, Alert) {
        var vm = this;
        vm.alerts = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;
        vm.loadAll = function() {
            Alert.query(function(result) {
                vm.alerts = result;
            });
        };

        vm.loadAll();
        $scope.sort = function(keyname){
    		$scope.sortKey = keyname;   //set the sortKey to the param passed
    		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
    	}
    }
   
})();
