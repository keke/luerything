/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({
    paths: {
      bootstrapAll: '../bower_components/bootstrap/dist/js/bootstrap',
      i18next: '../bower_components/i18next/release/i18next.amd-1.7.1',
      ngRoute: '../bower_components/angular-route/angular-route',
      ngCookies: '../bower_components/angular-route/angular-cookies',
      ngSanitize: '../bower_components/angular-route/angular-sanitize',
      sockjs: '../bower_components/sockjs/sockjs',
      vertxbus: '../libs/vertx/vertxbus-2.1'
    },
    shims: {
      ngRoute: ['angular'],
      ngCookies: ['angular'],
      ngSanitize: ['angular'],
      bootstrapAll: {
        exports: 'bootstrapAll',
        deps: ['jquery']
      }
    }
  });
  requirejs(['ltShared/shared']);
})(requirejs);

