/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({
    paths: {
      ngRoute: '../bower_components/angular-route/angular-route'
    },
    shims: {
      ngRoute: ['angular']
    }
  });
  requirejs(['ltNote/note', 'ltNote/note-config'], function (app) {
    return app;
  });
})(requirejs);