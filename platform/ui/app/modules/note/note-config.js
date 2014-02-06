/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['./note'], function (ltNote) {
    return ltNote.config(['AppConfigProvider', function (AppConfigProvider) {
      AppConfigProvider.addAppConfig({
        entryLink: '/note',
        name: 'note',
        iconCls:'fa fa-file'
      });
    }]);
  });
})(define);