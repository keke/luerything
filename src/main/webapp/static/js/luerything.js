(function(window, undefined) {
  var config = window.config;
  var luerything = window.luerything;
  if (!!!luerything || !!!config) {
    return;
  }
  luerything.search = {
    search: function(query) {
      $.getJSON(config.appRoot + '/search/', {q:query}, function(data, status, xhr) {
        this.update(data);
      })
    },
    update:function(data) {
    }
  }

  var History = window.History;
  if (!History.enabled) {
    return;
  }
  History.Adapter.bind(window, 'statechange', function() {
    var state = History.getState();
    History.log(state.data, state.title, state.url);
    if (state.data.action === 'search') {
      luerything.search.search(state.data.query);
    }
  });
  luerything.logger.log('history is enabled');

  $(function() {
    luerything.logger.log('dom ready');
    $('#searchbar > form').submit(function() {
      var query = $(this).find('input[name=q]').val();
      History.pushState(
          {
            action:'search',
            query:query
          },
          'Search : ' + query,
          '?search:q=' + query);
      return false;
    });
  });
})(window);