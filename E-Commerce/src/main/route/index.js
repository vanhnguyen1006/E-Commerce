var express = require('express');
var router = express.Router();


/* GET home page. */
app.get('/', function(req, res, next) => res.sendFile(path.join(__dirname, 'home/index.html')) ) ;
module.exports = router;