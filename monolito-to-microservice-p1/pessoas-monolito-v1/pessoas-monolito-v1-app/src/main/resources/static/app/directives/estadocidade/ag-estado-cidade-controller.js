angular.module('diretivas').controller('AgEstadoCidadeController',
		AgEstadoCidadeController);

AgEstadoCidadeController.$inject = [ '$scope', '$log', 'AgEstadoCidadeService' ];

function AgEstadoCidadeController($scope, $log, AgEstadoCidadeService) {

	$scope.regionalName = "";

//	$scope.form = {
//		estado : '',
//		cidade : ''
//	};

	$scope.estados = [];
	$scope.labelSelecioneCidade ='---Selecione a cidade ---';

	$scope.simulateQuery = true;
	$scope.isDisabled = true;
	// $scope.states = loadAll();
	$scope.querySearch = querySearch;
	// $scope.selectedItemChange = selectedItemChange;
	// $scope.searchTextChange = searchTextChange;
	// $scope.newState = newState;

	function querySearch(query) {
		var results = query ? $scope.states.filter(createFilterFor(query))
				: $scope.states, deferred;
		
		return results;
	}

	function createFilterFor(query) {
		var lowercaseQuery = angular.lowercase(query);
		return function filterFn(state) {
			return (state.value.indexOf(lowercaseQuery) === 0);
		};
	}

	$scope.selecionarEstado = function() {
		$scope.isDisabled = $scope.ngModelEstado == null
				|| $scope.ngModelEstado == '';

		function error(data) {
			// MessageService.showAlerta(data.message);
			$scope.labelSelecioneCidade ='---Selecione a cidade ---';
		}

		function sucesso(data) {
			$scope.states = data;
			$scope.labelSelecioneCidade ='---Selecione a cidade ---';
		}

		if( !$scope.isDisabled ){
			$scope.labelSelecioneCidade ='Carregando....';
			
			if( $scope.ngRegional ){
				AgEstadoCidadeService.getRegional($scope.ngModelEstado, sucesso, error);
			}else{
				AgEstadoCidadeService.getCidades($scope.ngModelEstado, sucesso, error);
			}
		}
	};
	
	var tratarSelecionadoEstado = function(data){
		
		if( data == null ){
			return null;
		}
		
		console.log("met val est");
		
		var loop=0;
		for(loop=0; loop<data.length; loop++){
			
			data[loop].selected=false;
			
			if( $scope.ngModelEstado != null && $scope.ngModelEstado != undefined ){
				
				if( $scope.ngModelEstado == data[loop].id ){
					data[loop].selected=true;
					console.log("achou = met val est");
				}
				
			}
		}
		
		console.log(data);
		return data;
	}

	$scope.$watch('ngModelCidade', function() {
		console.log($scope.ngModelCidade);

		if( $scope.ngRegional ){
			return;
		}

		if( $scope.ngModelCidade != null && $scope.ngModelCidade != undefined ){

			function error(data) {
				$scope.regionalName = "";
			}

			function sucesso(data) {
				$scope.regionalName = data["nome"];
			}

			$scope.regionalName = "...";
			AgEstadoCidadeService.getNomeRegional($scope.ngModelCidade, sucesso, error);
		}

	});

	$scope.$watch('ngModelEstado', function() {
		 console.log($scope.ngModelEstado);
		 
		 //var data = tratarSelecionadoEstado($scope.estados);
		 
		 //$scope.states = data;
   	  	
   	  /*
         if( scope.ngModelEstado != null  && !scope.isPassou ){
       	  console.log("passou");
       	  
       	  jQuery("select[name='"+scope.nomeEstado+"'] option[value='"+scope.ngModelEstado+"']").attr("selected", true)
       	  scope.isPassou = true;
         }
         */
         
     });
	 

	$scope.searchTextChange = function(text) {
		$log.debug(text);
	}

	$scope.selectedItemChange = function(item) {
		$scope.ngModelCidade = item;
	}

	var carregarEstados = function carregarestados() {

		function error(data) {
			// MessageService.showAlerta(data.message);
		}

		function sucesso(data) {
			
			//data = tratarSelecionadoEstado(data);
			
			$scope.estados = data;
			selecionarEstadoOnload();
		}

		AgEstadoCidadeService.getEstados(sucesso, error);
	};

	var selecionarEstadoOnload = function() {
        if( $scope.ngModelEstado != null ){
      	  jQuery("select[name='" + $scope.nomeEstado+"'] option[value='" + $scope.ngModelEstado+"']").attr("selected", true)
        }
    };
	
	
	carregarEstados();
	
	$scope.$watch('ngModelEstado', function() {
		$scope.selecionarEstado();
    });
	
}