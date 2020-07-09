var gulp = require('gulp');
var inject = require('gulp-inject');
var concat = require('gulp-concat');

//var bowerFiles = require('mai?n-bower-files');

gulp.task('inject', function () {
    var target = gulp.src('./index.html');
    // It's not necessary to read the files (will speed up things), we're only after their paths:

    var sources = gulp.src(
        [

         	'./app/comuns/a_basico/**/*.js',
            './app/comuns/1/**/*.js',
            './app/comuns/config/**/*.js',
            './app/comuns/message/**/*.js',
            './app/comuns/permissao/**/*.js',
            './app/comuns/http/**/*.js',
            './app/comuns/cep/**/*.js',
            './app/directives/**/*.js',
            './app/modulos/**/*.js'

        ], {read: false});

    //var sources_bower = gulp.src(
    //    bowerFiles(),
    //    { read: false },
    //    { name: 'bower' });

    return target
        //.pipe(inject(sources_bower))
        .pipe(inject(sources))
        .pipe(gulp.dest('./'));
});

var replace = require('gulp-replace');

gulp.task('corrigir', function(){
  gulp.src(['index.html'])
    .pipe(replace('/app/', 'app/'))
    .pipe(gulp.dest('./'));
});

gulp.task('scripts', function() {

    var sources = gulp.src(
        [

            './app/comuns/a_basico/**/*.js',
            './app/comuns/1/**/*.js',
            './app/comuns/config/**/*.js',
            './app/comuns/message/**/*.js',
            './app/comuns/permissao/**/*.js',
            './app/comuns/http/**/*.js',
            './app/comuns/cep/**/*.js',
            './app/directives/**/*.js',
            './app/modulos/**/*.js'

        ]);

    return sources
        .pipe(concat('all.js'))
        .pipe(gulp.dest('dist/'));
});





