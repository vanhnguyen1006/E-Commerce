var express = require('express');
var router = express.Router();


/* GET home page. */
router.get('/', function(req, res) {
    res.render('/home/index', { title: 'E-Commerce' });
});

module.exports = router;