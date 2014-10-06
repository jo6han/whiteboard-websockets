'use strict';

var count = 0;
/**
 * @ngdoc service
 * @name projectxApp.websocket
 * @description
 * # websocket
 * Factory in the projectxApp.
 */
angular.module('projectxApp')
  .factory('websocket', function ($rootScope) {


    var url = 'ws://localhost:8080/whiteboard-websockets/websocket';
    var ws = new WebSocket(url);

    ws.onopen = function (e) {
      console.log('Connected');
    };

    ws.onerror = function (error) {
      console.log('Websocket error: ' + error);
    };

    ws.onclose = function (event) {
      console.log('Remote host closed of refused websocket connection.');
      console.log(event);
    };

    ws.onmessage = function (message) {

      var json = message.data;
      console.log('WEBSOCKET MESSAGE RAW:' + message.data);

      if ('remove'.indexOf(JSON.parse(json).action) !== -1) {
        console.log('FINNS INTE');
        $rootScope.$broadcast('tabortJson', json);

      } else {

        console.log('FINNS FORTFARANDE KVAR');
        $rootScope.$broadcast('nyJson', json);
        //contains
      }



      // console.log('JSON från eclipse: ');
      // console.log(json);



    };

    var Service = {};
    Service.send = function (event) {
      ws.send(JSON.stringify(event));
      console.log(event);
    };

    return Service;
  });