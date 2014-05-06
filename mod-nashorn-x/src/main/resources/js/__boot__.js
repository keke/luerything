/**
 * @author keke
 */
(function (_global) {
  'use strict';
  _global.__require__ = function (parentModule) {
    return function (name) {
      var m = __jverticle__.require(name, parentModule);
      if (m) {
        return m.exports;
      } else {
        return null;
      }
    }
  };

  if (__exposeGlobal__) {
    _global.__global__ = _global;
  }
})(this);