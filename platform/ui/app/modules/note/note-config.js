/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['./note', 'ltShared/i18next'], function (ltNote) {
    return ltNote.config(['AppConfigProvider', 'i18nextServiceProvider', function (AppConfigProvider, i18nextServiceProvider) {
      AppConfigProvider.addAppConfig({
        entryLink: '/note',
        name: 'note',
        nameKey: 'note:appName',
        iconCls: 'fa fa-file'
      });
      i18nextServiceProvider.addNamespace('note');
    }]);
  });
})(define);