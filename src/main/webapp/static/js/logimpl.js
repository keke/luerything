(function() {
  if (!!luerything && !!luerything.logger) {
    var logImpl = {
      log:function(msg) {
        if (!!window.console) {
          window.console.log(msg);
        }
      }
    };
    luerything.logger.init(logImpl)
  }
})();