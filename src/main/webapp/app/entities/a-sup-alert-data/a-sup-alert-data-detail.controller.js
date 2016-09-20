(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertDataDetailController', ASUPAlertDataDetailController);

    ASUPAlertDataDetailController.$inject = ['$sce', '$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'ASUPAlertData'];

    function ASUPAlertDataDetailController($sce, $scope, $rootScope, $stateParams, DataUtils, entity, ASUPAlertData) {
        var vm = this;
        vm.aSUPAlertData = entity;
        vm.asup = vm.aSUPAlertData.asup_alert_file_data;
        vm.asup_html = $sce.trustAsHtml(vm.asup);
        
        var unsubscribe = $rootScope.$on('hedwigApp:aSUPAlertDataUpdate', function(event, result) {
            vm.aSUPAlertData = result;
        });
        $scope.$on('$destroy', unsubscribe);

        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
    }
})();
