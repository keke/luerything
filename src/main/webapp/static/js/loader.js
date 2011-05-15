yepnope(
    [
      {
        test:config.debug,
        yep:'http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.js',
        nope:'http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js'
      } ,
      {
        test:Modernizr.input.placeholder,
        nope:"http://html5form.googlecode.com/svn/trunk/jquery.html5form-min.js"
      },
      {
        test:config.debug,
        yep:config.ctxRoot + '_s/js/luerything.js',
        nope:config.ctxRoot + '_s/js/luerything.min.js'
      }
    ]
)