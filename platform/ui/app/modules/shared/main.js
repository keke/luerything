/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({
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
  requirejs(['ltShared/shared']);
})(requirejs);

