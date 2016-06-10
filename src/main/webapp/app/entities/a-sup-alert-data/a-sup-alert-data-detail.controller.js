(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertDataDetailController', ASUPAlertDataDetailController);

    ASUPAlertDataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'ASUPAlertData'];

    function ASUPAlertDataDetailController($scope, $rootScope, $stateParams, DataUtils, entity, ASUPAlertData) {
        var vm = this;
        vm.aSUPAlertData = entity;
        
        var unsubscribe = $rootScope.$on('hedwigApp:aSUPAlertDataUpdate', function(event, result) {
            vm.aSUPAlertData = result;
        });
        $scope.$on('$destroy', unsubscribe);

        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
    }
})();
