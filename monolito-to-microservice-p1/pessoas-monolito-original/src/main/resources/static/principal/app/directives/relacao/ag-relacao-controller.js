angular.module('diretivas')
	.controller('AgRelacaoController', AgRelacaoController);

AgRelacaoController.$inject = [ '$scope', '$log', '$q', '$timeout'];

function AgRelacaoController($scope, $log, $q, $timeout) {

	var selecionarEstadoOnload = function() {
        if( $scope.ngModelInicio != null ){
        	  jQuery("select[name='" + $scope.ngName+"'] option[value='" + $scope.ngModelInicio+"']").attr("selected", true)
          }
      };

    $scope.relacao = [
        {id:'BOA'},
        {id:'RAZOÁVEL'},
        {id:'RUIM'},
        {id:'INEXISTENTE'}
    ];

	$scope.selectedItemChange = function(item) {
		$scope.ngModelSelecionado = item;
	}

}