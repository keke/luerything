/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({
    baseUrl: '../modules',
    paths: {
      bootstrapAll: '../bower_components/bootstrap/dist/js/bootstrap'
    },
    shims: {
      bootstrapAll: {
        exports: 'bootstrapAll',
        deps: ['jquery']
      }
    }
  });
  requirejs(['angular', 'bootstrapAll', './shared/shared'], function () {
  });
})(requirejs);