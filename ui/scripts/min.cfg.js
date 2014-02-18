/**
 * The require config
 * @author keke
 */

(function(requirejs) {
  'use strict';
  requirejs.config({
    paths: {
      jquery: '../bower_components/jquery/dist/jquery',
      jqueryUi: '../bower_components/jquery-ui/ui/jquery-ui',
      angular: '../bower_components/angular/angular',
      ngRoute: '../bower_components/angular-route/angular-route',
      bootstrapAll: '../bower_components/bootstrap/dist/js/bootstrap',
      i18next: '../bower_components/i18next/i18next.amd',
      ngCookies: '../bower_components/angular-route/angular-cookies',
      ngSanitize: '../bower_components/angular-route/angular-sanitize',
      sockjs: '../bower_components/sockjs/sockjs',
      vertxbus: '../libs/vertx/vertxbus-2.1'
    },
    shim: {
      angular: {
        exports: 'angular',
        deps: ['jquery']
      },
      ngRoute: ['angular'],
      ngCookies: ['angular'],
      ngSanitize: ['angular'],
      bootstrapAll: {
        exports: 'bootstrapAll',
        deps: ['jquery']
      },
      jqueryUi: ['jquery']
    }
  });
  //boot luerything.app
  // debug('To load deps', deps);
  requirejs(['angular', 'app', 'app-config', 'ltShared','ltNote'], function(angular) {
    angular.element(document).ready(function() {
      // debug('To bootstrap', modules);
      angular.bootstrap(document, ['app', 'ltShared', 'ltNote']);
    });
  });
})(requirejs);