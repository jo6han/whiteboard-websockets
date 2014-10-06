'use strict';

/**
 * @ngdoc directive
 * @name projectxApp.directive:myItems
 * @description
 * # myItems
 */
angular.module('projectxApp')
	.directive('myItems', function () {
		return {
			restrict: 'E',
			template: '<div class="custom" ng-repeat="item in items | filter:{done:false} | orderBy:$index" ng-style={"background":item.color} my-draggable> ' +
				'<button type="button" class="glyphicon glyphicon-pencil btn btn-primary" style="float: left;" data-toggle="modal" data-target="#myModalUpdate{{$index}}"></button>' +
				'<button type="button" class="glyphicon glyphicon-remove btn btn-primary" ng-click="removeItem(item.id)" style="float: right;"></button>' +
				'<p class="customP"> {{ item.desc }} </p>' +
				'</br>' +
				'<ul>' +
				' <li ng-repeat="itemss in item.list track by $index"> {{ itemss }} </li>' +
				'</ul>' +
				'</div>'
		};
	});