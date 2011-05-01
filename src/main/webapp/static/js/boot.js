(function() {
  var luerything = window.luerything = {};
  luerything.logger = (function() {
    var logImpl = null;
    return {
      init:function(impl) {
        logImpl = impl;
      },
      log:function(msg) {
        if (!!logImpl) {
          logImpl.log(msg);
        }
      }
    }
  })();
})();