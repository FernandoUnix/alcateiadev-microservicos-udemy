angular.module('diretivas')
    .controller('AgReferenciasolController', AgReferenciasolController);

AgReferenciasolController.$inject = ['$scope', '$log', '$q', '$timeout'];

function AgReferenciasolController($scope, $log, $q, $timeout) {

    $scope.items = [
        {id:'I', nome: "INDIVIDUAL"},
        {id:'C', nome: "COLETIVO"},
        {id:'N', nome: "NAO DEFINIDO"}
    ];

    var selecionarEstadoOnload = function () {
        if ($scope.ngModelInicio != null) {
            jQuery("select[name='" + $scope.ngName + "'] option[value='" + $scope.ngModelInicio + "']").attr("selected", true)
        }
    };

    $scope.$watch('ngModelInicio', function () {
        selecionarEstadoOnload();
    });

    $scope.selectedItemChange = function (item) {
        $scope.ngModelSelecionado = item;
    }

}