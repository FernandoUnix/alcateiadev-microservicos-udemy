angular.module('diretivas')
    .controller('AgGrauAproximacaoController', AgGrauAproximacaoController);

AgGrauAproximacaoController.$inject = ['$scope', '$log', '$q', '$timeout'];

function AgGrauAproximacaoController($scope, $log, $q, $timeout) {

    $scope.items = [
        {id:'B', nome: "BOA"},
        {id:'R', nome: "RAZOÁVEL"},
        {id:'U', nome: "RUIM"},
        {id:'I', nome: "INEXISTENTE"}
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