(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .controller('AlertDialogController', AlertDialogController);

    AlertDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Alert'];

    function AlertDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Alert) {
        var vm = this;
        vm.alert = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('hedwigApp:alertUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.alert.id !== null) {
                Alert.update(vm.alert, onSaveSuccess, onSaveError);
            } else {
                Alert.save(vm.alert, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;
    }
})();
