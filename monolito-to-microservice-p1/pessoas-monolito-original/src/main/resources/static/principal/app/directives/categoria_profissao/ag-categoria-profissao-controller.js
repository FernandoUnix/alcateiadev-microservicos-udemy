angular.module('diretivas')
	.controller('AgCategoriaProfissaoController', AgCategoriaProfissaoController);

AgCategoriaProfissaoController.$inject = [ '$scope', '$log', '$q', '$timeout', 'AgCategoriaProfissaoService' ];

function AgCategoriaProfissaoController($scope, $log, $q, $timeout, AgCategoriaProfissaoService) {

	this.searchTextChange = function(text) {
		$log.debug("consultando="+text);
		
        function error(data){
        	$scope.states = [];
        }

        function sucesso(data){
        	$scope.states = [];
        	$scope.states = data;        
        }
        
		AgCategoriaProfissaoService.getCategorias(text, sucesso, error);
	}
	this.searchTextChange("");
	
	$scope.categorias = [];
	
	$scope.simulateQuery = true;
	$scope.isDisabled = false;
	$scope.states = [];
	$scope.querySearch = querySearch;
	// $scope.selectedItemChange = selectedItemChange;
	$scope.searchTextChange = this.searchTextChange;
	// $scope.newState = newState;

//	$scope.searchText = "sd---";
	
	function querySearch(query) {
		var results = query ? $scope.states.filter(createFilterFor(query))
				: $scope.states, deferred;
		
		deferred = $q.defer();
        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
        return deferred.promise;
	}

	function createFilterFor(query) {
		var lowercaseQuery = angular.lowercase(query);
		return function filterFn(state) {
			return (state.value.indexOf(lowercaseQuery) === 0);
		};
	}

		

	$scope.selectedItemChange = function(item) {
		$scope.ngModelSelecionado = item;
	}

}