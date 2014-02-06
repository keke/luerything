(function (define) {
  'use strict';

  define(['angular', 'jquery', 'ngRoute','ltShared'], function (angular, $) {
    return angular.module('ltApp', ['ngRoute', 'ltShared'])
        .directive('ltHeightMon', ['$window', '$timeout', function ($window, $timeout) {
          return{
            replace: false,
            link: function postLink($scope, elem, attrs) {
              var wnd = $($window);
              var sel = attrs.ltHeightMon;
              var resize = function () {
                elem.height(wnd.height() - $(sel).height());
              };
              wnd.on('resize', resize);
              $timeout(resize, 100);
              elem.on('$destroy', function () {
                wnd.off('resize', resize);
              });
            }
          }
        }]);
  });
})(define);