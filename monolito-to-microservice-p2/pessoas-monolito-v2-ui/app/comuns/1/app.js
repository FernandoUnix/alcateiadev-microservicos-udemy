(function () {


    angular.module('pessoas', [
        'ui.router',
        'blockUI',
        'communs',
        'diretivas',
        'grupo',
        'partido',
        'ui.mask',
        'pessoaFisica',
        'LocalStorageModule'
    ])
        .config(function ($httpProvider) {

            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        }).controller('navigation', NavigationController);

    NavigationController.$inject = [
        '$scope', '$http', 'MessageService', 'ConfigService', '$sce', '$compile'
    ];

    function NavigationController($scope, $http, MessageService, ConfigService, $sce, $compile) {


    }

})();