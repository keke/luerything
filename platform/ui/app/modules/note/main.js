/**
 * @author keke
 */
(function (requirejs) {
  'use strict';

  requirejs.config({

  });
  requirejs(['ltNote/note', 'ltNote/note-config'], function (app) {
    return app;
  });
})(requirejs);