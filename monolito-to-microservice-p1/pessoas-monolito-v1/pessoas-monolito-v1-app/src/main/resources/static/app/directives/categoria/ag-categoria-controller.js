angular.module('diretivas')
	.controller('AgCategoriaController', AgCategoriaController);

AgCategoriaController.$inject = [ '$scope', '$log', '$q', '$timeout', 'AgCategoriaService' ];

function AgCategoriaController($scope, $log, $q, $timeout, AgCategoriaService) {

	$scope.categorias = [];
	
	var selecionarEstadoOnload = function() {
        if( $scope.ngModelInicio != null ){
        	  jQuery("select[name='" + $scope.ngName+"'] option[value='" + $scope.ngModelInicio+"']").attr("selected", true)
          }
      };
	
      $scope.$watch('ngModelInicio', function() {
    	  selecionarEstadoOnload();
      });
      
	this.searchTextChange = function(text) {
		$log.debug("consultando="+text);
		
        function error(data){
        	$scope.states = [];
        }

        function sucesso(data){
        	$scope.states = [];
        	$scope.states = data;     
        	selecionarEstadoOnload();
        }
        
		AgCategoriaService.getCategorias(text, sucesso, error);
	}
	
	this.searchTextChange("");
	
	

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