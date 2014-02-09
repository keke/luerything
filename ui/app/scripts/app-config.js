/**
 * @author keke
 */
(function (define) {
  'use strict';
  define(['app', 'masthead', 'app-nav', 'ltShared/i18next', 'ltShared/cfg'], function (ltApp) {
    return ltApp.constant('templateRoot', 'scripts').config(['$locationProvider', 'i18nextServiceProvider',
          function ($locationProvider, i18nextServiceProvider) {
            $locationProvider.html5Mode(true).hashPrefix('!');
            i18nextServiceProvider.addNamespace('lt');
          }]).run(['i18nextService',
          function (i18nextService) {
            i18nextService.init();
          }]);
  });
})(define);