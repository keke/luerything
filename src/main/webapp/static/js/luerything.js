$(function() {
  var config = window.config;
  var log = function() {
    if (config.debug && console.log) {
      console.log.apply(console, arguments);
    }
  }
  var adjustMainHeight = function() {
    $('#main').height($(window).height() - 100);
  };
  var fillNav = function(data) {
    log('filling navigation panel');
  }
  var loadNav = function() {
    $.getJSON(config.appRoot + '/nav/', function(data) {
      fillNav(data);
    });
  };

  $('#update-all').click(function() {
    $.post(config.appRoot + '/update-all/');
  });

  $('#searchbar > form').submit(function() {
    var query = $(this).find('input[name=q]').val();
    $.getJSON(config.appRoot + '/search/', {q:query}, function(data) {

    });
    return false;
  });
  log('loaded');
  //$(window).resize(adjustMainHeight);
  //adjustMainHeight();
  window.setTimeout(loadNav, 500);

});