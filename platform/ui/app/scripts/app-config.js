/**
 * @author keke
 */
(function (define) {
  'use strict';
  define(['app', 'masthead', 'app-nav'], function (ltApp) {
    return ltApp.constant('templateRoot', 'scripts').config(['$locationProvider', function ($locationProvider) {
      $locationProvider.html5Mode(true).hashPrefix('!');
    }]);
  });
})(define);