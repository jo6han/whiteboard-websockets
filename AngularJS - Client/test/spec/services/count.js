'use strict';

describe('Service: count', function () {

  // load the service's module
  beforeEach(module('projectxApp'));

  // instantiate service
  var count;
  beforeEach(inject(function (_count_) {
    count = _count_;
  }));

  it('should do something', function () {
    expect(!!count).toBe(true);
  });

});
