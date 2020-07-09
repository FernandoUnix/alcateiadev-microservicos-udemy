angular.module('pessoas').controller('DashboardController',
    DashboardController);

DashboardController.$inject = [ '$stateParams', '$scope', '$http', 'MessageService' ];

function DashboardController($stateParams, $scope, $http,
                             MessageService
) {

    $scope.dados = null;
    $scope.ismenuMunicipio = false;

    $scope.dashboard = function(){
        $http.get('dashboard/').success(function(data) {
            $scope.dados = data;
        }).error(function() {

        });
    };

    $scope.dashboard();

}