'use strict';

var express = require('express');
var fs = require('fs');

var app = module.exports = express();
var favicon = require('serve-favicon');

app.use(express.static(__dirname));
app.use(favicon(__dirname + '/favicon.ico'));

