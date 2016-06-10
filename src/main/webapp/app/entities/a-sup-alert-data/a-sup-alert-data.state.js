(function() {
    'use strict';

    angular
        .module('hedwigApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('a-sup-alert-data', {
            parent: 'entity',
            url: '/a-sup-alert-data',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ASUPAlertData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/a-sup-alert-data/a-sup-alert-data.html',
                    controller: 'ASUPAlertDataController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('a-sup-alert-data-detail', {
            parent: 'entity',
            url: '/a-sup-alert-data/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ASUPAlertData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/a-sup-alert-data/a-sup-alert-data-detail.html',
                    controller: 'ASUPAlertDataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ASUPAlertData', function($stateParams, ASUPAlertData) {
                    return ASUPAlertData.get({id : $stateParams.id});
                }]
            }
        })
        .state('a-sup-alert-data.new', {
            parent: 'a-sup-alert-data',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/a-sup-alert-data/a-sup-alert-data-dialog.html',
                    controller: 'ASUPAlertDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                asup_alert_id: null,
                                asup_alert_file_name: null,
                                asup_alert_file_data: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('a-sup-alert-data', null, { reload: true });
                }, function() {
                    $state.go('a-sup-alert-data');
                });
            }]
        })
        .state('a-sup-alert-data.edit', {
            parent: 'a-sup-alert-data',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/a-sup-alert-data/a-sup-alert-data-dialog.html',
                    controller: 'ASUPAlertDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ASUPAlertData', function(ASUPAlertData) {
                            return ASUPAlertData.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('a-sup-alert-data', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('a-sup-alert-data.delete', {
            parent: 'a-sup-alert-data',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/a-sup-alert-data/a-sup-alert-data-delete-dialog.html',
                    controller: 'ASUPAlertDataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ASUPAlertData', function(ASUPAlertData) {
                            return ASUPAlertData.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('a-sup-alert-data', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
