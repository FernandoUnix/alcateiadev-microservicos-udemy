angular.module('diretivas')
    .controller('AgStatusCoresController', AgStatusCoresController);

AgStatusCoresController.$inject = ['$scope', '$log', '$q', '$timeout'];

function AgStatusCoresController($scope, $log, $q, $timeout) {

    $scope.items = [
        {id:'#F44336', nome: "VERMELHO"},
        {id:'#000000', nome: "PRETO"},
        {id:'#FFC107', nome: "AMARELO"},
        {id:'#2196F3', nome: "AZUL"},
        {id:'#4CAF50', nome: "VERDE"},
        {id:'#9E9E9E', nome: "CINZA"}
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