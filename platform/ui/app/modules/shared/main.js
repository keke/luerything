/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({
    paths: {
      bootstrapAll: '../bower_components/bootstrap/dist/js/bootstrap',
      i18next: '../bower_components/i18next/release/i18next.amd-1.7.1'
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

