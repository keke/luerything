/**
 * @author keke
 */
(function(define) {
  'use strict';
  define(['app', 'masthead', 'app-nav', 'ltShared/i18next', 'ltShared/cfg'], function(ltApp) {
    return ltApp.constant('templateRoot', 'scripts').constant('baseCfg', window.__cfg).
      config(['$locationProvider', 'i18nextServiceProvider', '$routeProvider',
        function($locationProvider, i18nextServiceProvider, $routeProvider) {
          $locationProvider.html5Mode(true).hashPrefix('!');
          i18nextServiceProvider.addNamespace('lt');
          $routeProvider.when('/', {
            resolve:{
              delay: ['$q','i18nextService','ConfigService',function($q, i18nextService, ConfigService){
                return $q.all([i18nextService.init(), ConfigService.init()]);
              }]
            }
          }).otherwise({
            redirectTo: '/'
          });
        }]).run(['i18nextService',
          function(i18nextService) {
        i18nextService.init();
          }]);
  });
})(define);