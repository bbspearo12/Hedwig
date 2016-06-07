(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertDeleteController',AlertDeleteController);

    AlertDeleteController.$inject = ['$uibModalInstance', 'entity', 'Alert'];

    function AlertDeleteController($uibModalInstance, entity, Alert) {
        var vm = this;
        vm.alert = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Alert.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
