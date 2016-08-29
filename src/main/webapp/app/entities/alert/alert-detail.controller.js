(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertDetailController', AlertDetailController);

    AlertDetailController.$inject = ['$sce' ,'$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Alert'];

    function AlertDetailController($sce, $scope, $rootScope, $stateParams, DataUtils, entity, Alert) {
        var vm = this;
        vm.alert = entity;
        vm.asup = vm.alert.alerts.replace(/'/g, "\"");
        vm.asup_html = $sce.trustAsHtml(vm.asup);

    }
})();
