/**
 * @author keke
 */
// (function (requirejs) {
//   'use strict';

//   // requirejs.config({
//   //   paths: {
//   //     bootstrapAll: '../bower_components/bootstrap/dist/js/bootstrap',
//   //     i18next: '../bower_components/i18next/i18next.amd',
//   //     ngRoute: '../bower_components/angular-route/angular-route',
//   //     ngCookies: '../bower_components/angular-route/angular-cookies',
//   //     ngSanitize: '../bower_components/angular-route/angular-sanitize',
//   //     sockjs: '../bower_components/sockjs/sockjs',
//   //     vertxbus: '../libs/vertx/vertxbus-2.1'
//   //   },
//   //   shims: {
//   //     ngRoute: ['angular'],
//   //     ngCookies: ['angular'],
//   //     ngSanitize: ['angular'],
//   //     bootstrapAll: {
//   //       exports: 'bootstrapAll',
//   //       deps: ['jquery']
//   //     }
//   //   }
//   // });
//   requirejs(['ltShared/shared']);
// })(requirejs);
(function(define) {
  'use strict';

  define(['angular', 'vertxbus', 'ngRoute'], function(angular, EventBus) {
    return angular.module('ltShared', ['ngRoute']).service('EventBus', ['$q', 'baseCfg',
      function($q, baseCfg) {
        var me = this;
        var defer = $q.defer();
        var eb = new EventBus(baseCfg.eventBusApi);
        eb.onopen = function() {
          defer.resolve(eb);
        }
        return {
          init: function() {
            return defer.promise;
          },
          getEventBus: function() {
            return eb;
          }
        }
    }]);
  });
})(define);