/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['angular', './shared'], function (angular, ltShared) {
    return ltShared.provider('ConfigService', ['$q', function ($q) {
      var me = this;
      var cfgStore = null;
      var initDefer = $q.defer();
      me.$get = ['baseCfg', function (baseCfg) {
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