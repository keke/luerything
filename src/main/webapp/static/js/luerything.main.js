$(function() {
  var LU = window.luerything;
  var Config = window.config;
  var adjustSize = function() {
    $('#main').height($(window).height() - 100);
  };
  var adjustPanelWidth = function() {
    var panels = $('#main .panel');
    var length = panels.length;
    if (length > 0) {
      panels.width(parseInt(100.0 / length * 100) / 100 + '%');
    }
  };
  $(window).resize(function() {
    window.setTimeout(adjustSize, 100);
  });
  $('#searchbar form').submit(function() {
    LU.search($(this).find('input[name=q]').val());
    return false;
  });
  /*
   * For navigation panel
   */
  LU.nav = (function() {
    var navPanelSelector = $('#nav-panel');
    navPanelSelector.find('.nav-item').live('click', function() {
      var item = $(this);
      if (item.hasClass('tag')) {
        LU.list.loadTag(item.text());
      } else if (item.hasClass('folder')) {
        LU.list.openFolder(item.data('query'));
      }
      navPanelSelector.find('.nav-item.active').removeClass('active');
      item.addClass('active');
    });
    var buildFolders = function(folders) {

    }
    var buildTags = function(tags) {

    }
    return {
      init:function() {
        $.getJSON(config.appRoot + '/folder/', buildFolders);
        $.getJSON(config.appRoot + '/tag/', buildTags);
      }
    }
  })();
  /*
   * For list panel
   */
  LU.list = (function() {
    var listPanelSelector = $('#list-panel');
    listPanelSelector.bind('update-all', function(data) {
      LU.log.debug('Got list data');
      listPanelSelector.find('.title').text(data.title);
    });
    return {
      openFolder:function(query) {
        LU.search(query);
      },
      loadTag:function(tag) {
        $.getJSON(config.appRoot + '/tag/' + tag, function(data) {
          listPanelSelector.trigger('update-all', data);
        });
      }
    };
  })();
  /* other */
  /**
   *
   * @param query
   * @param offset
   * @param size
   */
  LU.search = function(query, offset, size) {
    offset = offset || 0;
    size = size || config.defaultPageSize || 20;
    $.getJSON(Config.appRoot + '/search/', {q:query, o:offset,s:size}, function(data) {
      $('#list-panel').trigger('update-all', data);
    });
  }

  $('#sbar #uall').click(function() {
    $.post(config.appRoot + '/update-all');
  });

  /* initialize methods */
  adjustSize();

//adjustPanelWidth();
  LU.nav.init();
})