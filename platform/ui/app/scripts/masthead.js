/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['app'], function (ltApp) {
    ltApp.directive('ltMasthead', ['templateRoot', function (tRoot) {
      return {
        replace: true,
        templateUrl: tRoot + '/masthead.html'
      }
    }]);
  });
})(define);