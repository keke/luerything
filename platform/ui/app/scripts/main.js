/**
 * The require config
 * @author keke
 */
(function (requirejs) {
  'use strict';
  var debug = function () {
    if (console && console.debug) {
      console.debug.apply(console, arguments);
    }
  };
  var packages = [];
  var deps = ['angular', 'app', 'app-config'];
  var modules = ['ltApp'];
  for (var i = 0, m = __modules.length; i < m; i++) {
    var module = __modules[i];
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
      modules.push(module.name);
    }
  }
  debug('To load packages', packages);
  requirejs.config({
    packages: packages,
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
  //boot luerything.app
  debug('To load deps', deps);
  requirejs(deps, function (angular) {
    angular.element(document).ready(function () {
      debug('To bootstrap', modules);
      angular.bootstrap(document, modules);
    });
  });
})(requirejs);