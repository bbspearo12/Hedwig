(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('ASUPAlertDataDialogController', ASUPAlertDataDialogController);

    ASUPAlertDataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'ASUPAlertData'];

    function ASUPAlertDataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, ASUPAlertData) {
        var vm = this;
        vm.aSUPAlertData = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('hedwigApp:aSUPAlertDataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.aSUPAlertData.id !== null) {
                ASUPAlertData.update(vm.aSUPAlertData, onSaveSuccess, onSaveError);
            } else {
                ASUPAlertData.save(vm.aSUPAlertData, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;
    }
})();
