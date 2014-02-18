/**
 * The require config
 * @author keke
 */ (function(requirejs) {
  'use strict';
  var debug = function() {
    if (console && console.debug) {
      console.debug.apply(console, arguments);
    }
  };
  var packages = [];
  var deps = ['angular', 'app', 'app-config'];
  var modules = ['ltApp'];
  for (var key in __modules) {
    var module = __modules[key];
    if (typeof module === 'string') {
      packages.push({
        name: module,
        location: '../modules/' + module
      });
      deps.push(module);
      modules.push(module);
    } else if (typeof module === 'object') {
      if (!module.location) {
        module.location = '../modules/' + module;
      }
      packages.push(module);
      deps.push(module.name);
      if(module.config){
        deps.push(module.name+'/'+module.config);
      }
      modules.push(module.name);
    }
  }
  debug('To load packages', packages);
  requirejs.config({
    packages: packages,
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
  debug('To load deps', deps);
  requirejs(deps, function(angular) {
    angular.element(document).ready(function() {
      debug('To bootstrap', modules);
      angular.bootstrap(document, modules);
    });
  });
})(requirejs);