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
              EventBus.getEventBus().send('luerything.config.load', {
                action: 'load'
              }, function(msg) {
                console.log('Got config', msg);
                initDefer.resolve();
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