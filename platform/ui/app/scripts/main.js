(function(requirejs) {
  'use strict';

  requirejs.config({
    paths: {
      jquery: '../bower_components/jquery/jquery',
      angular: '../bower_components/angular/angular',
      ngRoute: '../bower_components/angular-route/angular-route'
    },
    shim: {
      angular: {
        exports: 'angular',
        deps: ['jquery']
      },
      ngRoute: ['angular']
    }
  });

  requirejs(['jquery', 'angular', 'app'], function($, angular) {
    $(function() {
      angular.bootstrap(document, ['uiApp']);
    });
  });
})(requirejs);