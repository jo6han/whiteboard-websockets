'use strict';

describe('Directive: myDraggable', function () {

  // load the directive's module
  beforeEach(module('projectxApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<my-draggable></my-draggable>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the myDraggable directive');
  }));
});
