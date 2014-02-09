/**
 * @author keke
 */ (function(define) {
  'use strict';

  define(['angular', './shared'], function(angular, ltShared) {

    return ltShared.provider('ConfigService', [
      function() {
        var me = this;
        var cfgStore = null;

        me.$get = ['baseCfg', '$q', 'EventBus',
          function(baseCfg, $q, EventBus) {
            var initDefer = $q.defer();
            EventBus.init().then(function() {
              EventBus.getEventBus().registerHandler('luerything.config', function(msg) {
                initDefer.resolve(msg.data);
              });
              EventBus.getEventBus().send('luerything.config', {
                action: 'load'
              });
            });
            cfgStore = angular.extend({}, baseCfg);
            return {
              init: function() {
                return initDefer.promise;
              },
              get: function(key) {}
            };
      }];
    }]);
  });
})(define);