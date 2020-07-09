angular
	.module('diretivas')
	.controller('AgGridController', AgGridController);

	AgGridController.$inject = [ '$scope', '$log', 'AgEstadoCidadeService' ];

function AgGridController($scope, $log, AgEstadoCidadeService) {

	$scope.NRO_TOTAL_REGISTROS = 50;

    $scope.getValorCampoColor = function(item, itemColunas){

    };

	$scope.getValorCampo = function(item, itemColunas){

        if( itemColunas['color'] !=  undefined && itemColunas['color'] ){
            itemColunas.style = {'background-color': item[itemColunas.campo]};
            return "";
        }

		if( !itemColunas['subCampo'] ){
			return item[itemColunas.campo];	
		}
		
		var valor = item[itemColunas.campo];
		if( !valor ){
			return "";
		}
		
		return valor[itemColunas.subCampo];
	};
	
	
	$scope.getResumo = function(){
		var resumo = "";
		
		var total = $scope.ngData.paginacao !=  undefined && $scope.ngData.paginacao != null && $scope.ngData.paginacao['totalPaginas'] ? $scope.ngData.paginacao.totalPaginas : 0;
		
		if( total <= 0 ){
			resumo = "-";
			return resumo;
		}
		
		var pos = ($scope.ngData.paginacao.pos+1);
		
		resumo = resumo + $scope.ngData.paginacao.pos == 0 ? 1 : $scope.ngData.paginacao.pos * $scope.NRO_TOTAL_REGISTROS;
		resumo = resumo + " a ";
		resumo = resumo + ( pos * $scope.NRO_TOTAL_REGISTROS > $scope.ngData.paginacao.totalRegistros ? $scope.ngData.paginacao.totalRegistros : pos * $scope.NRO_TOTAL_REGISTROS);
		resumo = resumo + " no total de ";
		resumo = resumo + $scope.ngData.paginacao.totalRegistros;
		
		return resumo;
	}
	
	$scope.getNroPaginas = function(){
		
		var lista = new Array();
		var loop=0;
		//for(loop=0; loop<$scope.ngData.paginacao.totalPaginas; loop++){
		
		var inicio = $scope.ngData.paginacao.pos < 4 ? 0 : $scope.ngData.paginacao.pos - 4;
		var ultimo = inicio+8 > $scope.ngData.paginacao.totalPaginas ? $scope.ngData.paginacao.totalPaginas: inicio+8; 
		
		/*if( (ultimo - inicio)  < 8 ){
			inicio = inicio - (inicio - (ultimo - inicio) );
		}*/
		
		for(loop=inicio; loop<ultimo; loop++){
			lista.push(loop);
		}

		if( lista.length <= 0 &&
			$scope.ngData.paginacao.registros != null &&
			$scope.ngData.paginacao.registros.length > 0 ){
			lista.push(0);
		}

		return lista;
	}
	
	

	$scope.$watch('ngData', function(newVal, oldVal){
		$scope.getResumo();    
    });
	
}