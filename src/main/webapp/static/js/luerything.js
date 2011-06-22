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

  var $list = $('#list');
  var listEntryTemplate = $('#list-entry-template').template();
  var updateList = function(data) {
    $list.find('*').die('click');
    $list.empty();
    $.tmpl(listEntryTemplate, data).appendTo($list);
  }

  $('#update-all').click(function() {
    $.post(config.appRoot + '/update-all/');
  });

  $('#searchbar > form').submit(function() {
    var query = $(this).find('input[name=q]').val();
    $.getJSON(config.appRoot + '/search/', {q:query}, function(data) {
      updateList(data);
    });
    return false;
  });

  $('#list .item .title').live('click', function(e) {
    var id = $(this).parents('.item').attr('id').split('-')[1];
    $.post(config.appRoot + '/doc/' + id + '/?open');
  });
  log('loaded');
  //$(window).resize(adjustMainHeight);
  //adjustMainHeight();
  window.setTimeout(loadNav, 500);

});