(function(window) {
  var LU = window.luerything;
  if (typeof LU === 'undefined') {
    throw new Error("Luerything is not booted");
  }
  var console = window.console;
  if (typeof console === 'undefined') {
    return;
  }

  LU.log.impl = {
    log:function() {
      console.log.apply(console, arguments);
    },
    debug:function() {
      console.debug.apply(console, arguments);
    }
  }
  LU.log.log('log initialized');
})(window);