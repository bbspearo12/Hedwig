(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertDetailController', AlertDetailController);

    AlertDetailController.$inject = ['$sce' ,'$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Alert'];

    function AlertDetailController($sce, $scope, $rootScope, $stateParams, DataUtils, entity, Alert) {
        var vm = this;
        vm.alert = entity;
        vm.asup = vm.alert.alerts;
        vm.asup_html = $sce.trustAsHtml(vm.asup);
        vm.required_files = ['DF.txt', 'SYSCONFIG-A.txt', 'SYSCONFIG-R.txt', 'OPTIONS.txt', 'IFCONFIG-A.txt', 'SAS-EXPANDER-MAP.txt', 'STORAGE-FAULT.txt', 'AGGR-STATUS-R.txt', 'AGGR-STATUS-V.txt', 'CF-MONITOR.txt', 'DF-A.txt', 'ENVIRONMENT.txt', 'STORAGE-DISK.txt', 'STORAGE-INITIATORS.txt', 'STORAGE-EXPANDER.txt', 'SYSCONFIG-AC.txt', 'SYSCONFIG-M.txt', 'SYSTEM-SERIAL-NUMBER.TXT', 'VOL-STATUS.txt', 'VOL-STATUS-V.txt', 'UNOWNED-DISKS.txt'];
        	
    }
})();
