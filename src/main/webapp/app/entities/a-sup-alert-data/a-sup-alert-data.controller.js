(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertDataController', ASUPAlertDataController);

    ASUPAlertDataController.$inject = ['$location', '$scope', '$state', 'DataUtils', 'ASUPAlertData'];

    function ASUPAlertDataController ($location, $scope, $state, DataUtils, ASUPAlertData) {
        var vm = this;
        vm.aSUPAlertData = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;
        vm.hostname = $location.search()['hostname'];
        vm.loadAll = function() {
            ASUPAlertData.query(function(result) {
                vm.aSUPAlertData = result;
            });
        };

        vm.loadAll();
        
    }
})();
