/**
 * @author keke
 */
(function (define) {
  'use strict';
  define(['./shared', 'i18next'], function (ltShared, i18n) {
    ltShared.provider('i18nextService', [function () {
          var me = this;
          var nss = [];
          me.addNamespace = function (ns) {
            nss.push(ns);
          };


          me.$get = [function () {
            return{
              addNamespace: function (ns) {
              },
              init: function () {
                i18n.init({
                  lng: 'en-US',
                  load: 'current',
                  cookieName: 'lang',
                  fallbackLng: false,
                  resGetPath: 'resources/locales/__ns__/__lng__.json',
                  ns: {
                    namespaces: nss
                  }
                });
              }
            }
          }]
        }]).filter('i18n', [function () {
          return function () {
            return i18n.t.apply(i18n, arguments);
          }
        }]);
  });
})(define);