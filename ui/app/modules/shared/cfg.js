/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['angular', './shared', 'vertxbus'], function (angular, ltShared, EventBus) {

    return ltShared.provider('ConfigService', [function () {
      var me = this;
      var cfgStore = null;
      me.$get = ['baseCfg', '$q', function (baseCfg, $q) {
        var initDefer = $q.defer();
        cfgStore = angular.extend({}, baseCfg);
        return {
          init: function () {
            return initDefer.promise;
          },
          get: function (key) {
          }
        };
      }];
    }]);
  });
})(define);