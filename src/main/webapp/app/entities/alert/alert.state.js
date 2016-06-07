(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('alert', {
            parent: 'entity',
            url: '/alert',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Alerts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alert/alerts.html',
                    controller: 'AlertController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('alert-detail', {
            parent: 'entity',
            url: '/alert/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Alert'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alert/alert-detail.html',
                    controller: 'AlertDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Alert', function($stateParams, Alert) {
                    return Alert.get({id : $stateParams.id});
                }]
            }
        })
        .state('alert.new', {
            parent: 'alert',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert/alert-dialog.html',
                    controller: 'AlertDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                generated_on: null,
                                version: null,
                                system_id: null,
                                serial_num: null,
                                hostname: null,
                                sequence: null,
                                snmp_location: null,
                                partner_system_id: null,
                                partner_serial_num: null,
                                partner_hostname: null,
                                boot_clustered: null,
                                alerts: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('alert', null, { reload: true });
                }, function() {
                    $state.go('alert');
                });
            }]
        })
        .state('alert.edit', {
            parent: 'alert',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert/alert-dialog.html',
                    controller: 'AlertDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Alert', function(Alert) {
                            return Alert.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('alert', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alert.delete', {
            parent: 'alert',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alert/alert-delete-dialog.html',
                    controller: 'AlertDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Alert', function(Alert) {
                            return Alert.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('alert', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
