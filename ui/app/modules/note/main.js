/**
 * @author keke
 */
(function (define) {
  'use strict';

  define(['angular', 'ltShared'], function (angular) {
    return angular.module('ltNote', ['ltShared', 'ngRoute']);
  });
})(define);