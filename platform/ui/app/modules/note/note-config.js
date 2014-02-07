/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['./note', 'ltShared/i18next'], function (ltNote) {
    return ltNote.config(['AppConfigProvider', 'i18nextServiceProvider', '$routeProvider',
      function (AppConfigProvider, i18nextServiceProvider, $routeProvider) {
        AppConfigProvider.addAppConfig({
          entryLink: '#!/note',
          name: 'note',
          nameKey: 'note:appName',
          iconCls: 'fa fa-file'
        });
        i18nextServiceProvider.addNamespace('note');
        $routeProvider.when('/note', {
          templateUrl: __modules.ltNote.location + '/views/note.html'
        });
      }]);
  });
})(define);