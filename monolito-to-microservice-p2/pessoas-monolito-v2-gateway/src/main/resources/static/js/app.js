(function () {
    angular.module('pessoas', [
                                   'ui.router',
        						   'blockUI'
                                   ])
    .config(function($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    }).controller('navigation', NavigationController);

    NavigationController.$inject = [
        '$scope', '$http', 'blockUI'
    ];

    function NavigationController($scope, $http, blockUI ) {

        $scope.exibirErro = false;

        if( document.location.href.indexOf("?msgdeslog") >= 0 ){
            alert("O sistema deslogou automaticamente por inatividade.");
        }

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                    authorization: "Basic "
                    + btoa(credentials.username + ":"
                        + credentials.password)
                } : {};

            $scope.user = '';

            blockUI.start({message: 'Aguarde...'});

            $http.get('user', {
                headers: headers
            }).success(function (data) {

                blockUI.stop();

                if (data.name) {
                    $scope.authenticated = true;
                    $scope.user = data.name;
                    if (this.location.hash == "#/logar" || this.location.pathname == "/index.html" || this.location.pathname == "/") {
                        document.location.href = "/ui/";
                    }
                } else {
                    $scope.authenticated = false;
                }
                callback && callback(true);
            }).error(function () {

            	blockUI.stop();

                $scope.authenticated = false;
                callback && callback(false);
            });

        };

        authenticate();

        $scope.credentials = {};
        $scope.login = function () {
            $scope.exibirErro = false;
            authenticate($scope.credentials, function (authenticated) {
                $scope.authenticated = authenticated;
                $scope.error = !authenticated;

                if ($scope.error) {

                    //alert("Login ou senha inválida.");
                    $scope.exibirErro = true;

					/*
					 sweetAlert.swal({
					 title: "Error",
					 text: "Login ou senha inválida."
					 });
					 */

                } else {
                    document.location.href = "/ui/";
                }
            })
        };

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $scope.authenticated = false;
            }).error(function (data) {
                console.log("Logout failed");
                $scope.authenticated = false;
            });
        }
    }

})();