angular.module('diretivas')
	.controller('AgCargoController', AgCargoController);

AgCargoController.$inject = [ '$scope', '$log', '$q', '$timeout', 'AgCargoService' ];

function AgCargoController($scope, $log, $q, $timeout, AgCargoService) {

	if( $scope.ngIgnorar == undefined || $scope.ngIgnorar == null ){
		$scope.ngIgnorar = "";
	}
	
	this.searchTextChange = function(text) {
		$log.debug("consultando="+text);
		
        function error(data){
        	$scope.states = [];
        }

        function sucesso(data){
        	$scope.states = [];
        	$scope.states = data;
        }
        
		AgCargoService.getCargos(text, $scope.ngIgnorar, sucesso, error);
	}
	
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