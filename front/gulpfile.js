'use strict';

// Include Gulp & Tools We'll Use
var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var del = require('del');
var runSequence = require('run-sequence');
var pagespeed = require('psi');
var app = require('./server');
var vinylfs = require('vinyl-fs');

var AUTOPREFIXER_BROWSERS = [
	'ie >= 10',
	'ie_mob >= 10',
	'ff >= 30',
	'chrome >= 34',
	'safari >= 7',
	'opera >= 23',
	'ios >= 7',
	'android >= 4.4',
	'bb >= 10'
];

// Scan Your HTML For Assets & Optimize Them
gulp.task('html', function () {
	var assets = $.useref.assets({searchPath: '{.tmp,.}'});

	return gulp.src('index.html')
		.pipe(assets)
		.pipe(assets.restore())
		.pipe($.useref())
		// Output Files
		.pipe(gulp.dest('dist'))
		// Running vulcanize over the written output
		// because it requires access to the written
		// CSS and JS.
		.pipe($.vulcanize({ dest: 'dist', strip: true }))
		.pipe($.size({title: 'html'}));
});

// Clean Output Directory
gulp.task('clean', del.bind(null, ['.tmp', 'dist']));

// Build Production Files, the Default Task
gulp.task('default', ['clean'], function (cb) {
	runSequence(['html'], cb);
});

gulp.task('serve', function (cb) {
	app.listen(8081, cb);
});

