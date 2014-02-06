/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['./shared'], function (ltShared) {
    ltShared.provider('AppConfig', [function () {
      var me = this;
      var apps = [];
      me.addAppConfig = function (cfg) {
        apps.push(cfg);
      };
      me.$get = [function () {
        return {
          getApps: function () {
            return apps;
          }
        };
      }];
    }]);
  });
})(define);