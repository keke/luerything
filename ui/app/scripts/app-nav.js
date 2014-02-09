/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['app', 'ltShared/app-config', 'jqueryUi'], function (ltApp) {
    return ltApp.directive('ltAppNav', ['templateRoot', function (tRoot) {
      return {
        replace: true,
        controller: ['$scope', 'AppConfig', function ($scope, AppConfig) {
          $scope.apps = AppConfig.getApps();
        }],
        templateUrl: tRoot + '/app-nav.html',
        link: function postLink($scope, elem) {
          elem.find('.panel-shortcuts').droppable({

          });
        }
      }
    }]);
  });
})(define);