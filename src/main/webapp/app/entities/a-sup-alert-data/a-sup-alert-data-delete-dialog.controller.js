(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertDataDeleteController',ASUPAlertDataDeleteController);

    ASUPAlertDataDeleteController.$inject = ['$uibModalInstance', 'entity', 'ASUPAlertData'];

    function ASUPAlertDataDeleteController($uibModalInstance, entity, ASUPAlertData) {
        var vm = this;
        vm.aSUPAlertData = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            ASUPAlertData.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
