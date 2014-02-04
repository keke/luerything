(function(define) {
  'use strict';

  define(['angular','ngRoute'], function(angular) {
    return angular.module('uiApp', ['ngRoute'])
      .config(function($routeProvider) {
      $routeProvider
        .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
        .otherwise({
        redirectTo: '/'
      });
    });
  });
})(define);