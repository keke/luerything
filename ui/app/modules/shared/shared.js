/**
 * @author keke
 */
(function(define) {
  'use strict';

  define(['angular', 'vertxbus'], function(angular, EventBus) {
    return angular.module('ltShared', []).service('EventBus', ['$q', 'baseCfg',
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