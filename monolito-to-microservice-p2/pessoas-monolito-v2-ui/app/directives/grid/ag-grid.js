angular
	.module('diretivas')
	.directive('agGrid', function() {
	  return {
		restrict     : 'E',
	    templateUrl  : 'app/directives/grid/ag-grid.html',
	    controller   : 'AgGridController',
	    controllerAs : 'agGridController',
	    scope: {
	    	ngData: '=',
	    	ngFuncaoPesquisa: '=',
	    	ngGridEditar: '=',
	    	ngGridExcluir: '=',
	    	ngSemPaginacao: '=',
	    	ngGridSelecionar: '=',
			ngGridObjInteiro: '='
	      }
	  };
	});