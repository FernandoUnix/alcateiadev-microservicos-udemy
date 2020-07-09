angular
	.module('diretivas')
	.directive('agFiltroOrdenacao', function() {
	  return {
		restrict     : 'E',
		transclude   : true,
	    templateUrl  : 'app/directives/filtro-ordenacao/ag-filtro-ordenacao.html',
	    controller   : 'AgFiltroOrdenacaoController',
	    controllerAs : 'agFiltroOrdenacaoController',
	    scope: {
	    	ngListaCampos: '='
	      },
	  };
	});