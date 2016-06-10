'use strict';

describe('Controller Tests', function() {

    describe('ASUPAlertData Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockASUPAlertData;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockASUPAlertData = jasmine.createSpy('MockASUPAlertData');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ASUPAlertData': MockASUPAlertData
            };
            createController = function() {
                $injector.get('$controller')("ASUPAlertDataDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'hedwigApp:aSUPAlertDataUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
