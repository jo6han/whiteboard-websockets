'use strict';

/**
 * @ngdoc function
 * @name projectxApp.controller:TodoCtrl
 * @description
 * # TodoCtrl
 * Controller of the projectxApp
 */
angular.module('projectxApp')
	.controller('TodoCtrl', function ($rootScope, $scope, websocket) {
		$scope.items = [];


		$rootScope.$on('nyJson', function (event, pushData) {
			$rootScope.$apply(function () {
				var check = false;
				var json = JSON.parse(pushData);
				console.log('Json ID ' + json.id);

				for (var i = 0; i < $scope.items.length; i++) {
					if (json.id === $scope.items[i].id) {
						var newItem = {
							id: json.id,
							desc: json.desc,
							list: json.list,
							color: json.color,
							done: false
						};

						$scope.items.splice(i, 1, newItem);
						check = true;
					}
				}

				if (!check) {
					$scope.items.push(json);
				}
			});
		});

		$rootScope.$on('tabortJson', function (event, pushData) {
			$rootScope.$apply(function () {
				// console.log('EVENT i TODO.JS: ' + pushData);
				var json = JSON.parse(pushData);
				console.log('Rootscope recieved: ' + json.id);
				for (var i = 0; i < $scope.items.length; i++) {
					if (json.id === $scope.items[i].id) {
						$scope.items.splice(i, 1);
					}
				}
			});
		});


		$scope.removeItem = function (itemId) {
			var newItem = {
				id: itemId,
				desc: 'dummyData',
				list: ['dummyData', 'dummyData2'],
				color: 'dummyData',
				done: true,
				action: 'remove'
			};

			websocket.send(newItem);
		};

		$scope.addItem = function (item, listInfo, colorInfo) {
			var newItem = {
				id: 'nyId',
				desc: item,
				list: listInfo,
				color: colorInfo,
				done: false,
				action: 'add'
			};
			websocket.send(newItem);
		};
		$scope.updateItem = function (itemId, description, list, color) {
			var newItem = {
				id: itemId,
				desc: description,
				list: list,
				color: color,
				done: false,
				action: 'update'
			};
			console.log(newItem);
			websocket.send(newItem);
		};

		$scope.incompleteCount = function () {
			var i, count = 0;
			for (i = 0; i < $scope.items.length; i = i + 1) {
				if (!$scope.items[i].done) {
					count = count + 1;
				}
			}
			return count;
		};

		$scope.showIncompleteCount = function () {
			return $scope.incompleteCount() !== 0;
		};
	});