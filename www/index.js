module.exports = function (path, cb) {
  exec('install', [path], cb)
}

function exec (method, args, cb) {
  if (cb == null) cb = noop
  cordova.exec(onsuccess, onerror, 'SelfUpdate', method, args || [])

  function onsuccess () {
    var args = Array.prototype.slice.call(arguments)
    args.unshift(null)
    cb.apply(null, args)
  }

  function onerror (err) {
    err = (err instanceof Error) ? err : new Error(err)
    cb(err)
  }
}
function noop () {}
