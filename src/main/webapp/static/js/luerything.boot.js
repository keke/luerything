(function(window) {
  if (typeof window.luerything !== 'undefined') {
    throw new Error('Luerything is already loaded, maybe a namespace conflict');
  }
  var LU = window.luerything = {};
  LU.log = {
    log:function() {
      if (!!LU.log.impl) {
        LU.log.impl.log.apply(this, arguments);
      }
    },
    debug:function() {
      if (!!LU.log.impl) {
        LU.log.impl.debug.apply(this, arguments);
      }
    }
  }
})(window);