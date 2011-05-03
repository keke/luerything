(function() {
  if (!!luerything && !!luerything.logger) {
    var logImpl = {
      log:function() {
        if (!!window.console) {
          if (!!window.console.log.apply) {
            window.console.log.apply(window.console, arguments);
          }
          else {
            window.console.log(arguments[0]);
          }
        }
      }
    };
    luerything.logger.init(logImpl)
  }
})();