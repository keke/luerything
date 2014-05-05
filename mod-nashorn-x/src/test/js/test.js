/**
 * @author keke
 */
var console = require('vertx/console');
var container = require('vertx/container');
console.log('run test.js in ' + module.setVertxStop);
//console.log('test')
//load('./src/test/js/test_fx.js')
//test(console.log);
//console.log('global ' + abc);
container.exit();

function vertxStop(){
  print('Stop vertx');
}