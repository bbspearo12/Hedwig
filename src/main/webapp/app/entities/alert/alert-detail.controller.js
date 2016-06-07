(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertDetailController', AlertDetailController);

    AlertDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'DataUtils', 'entity', 'Alert'];

    function AlertDetailController($scope, $rootScope, $stateParams, DataUtils, entity, Alert) {
        var vm = this;
        vm.alert = entity;
        
        var unsubscribe = $rootScope.$on('hedwigApp:alertUpdate', function(event, result) {
            vm.alert = result;
        });
        $scope.$on('$destroy', unsubscribe);

        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
    }
})();
