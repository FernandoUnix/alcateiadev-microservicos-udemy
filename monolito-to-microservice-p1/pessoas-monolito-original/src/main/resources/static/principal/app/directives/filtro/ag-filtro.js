angular
	.module('diretivas')
	.directive('agFiltro', function() {
	  return {
		restrict     : 'E',
		transclude   : true,
	    templateUrl  : 'app/directives/filtro/ag-filtro.html',
	    controller   : 'AgFiltroController',
	    controllerAs : 'agFiltroController',
	    scope: {
	    	ngFuncaoPesquisa: '=',
	    	ngLabel: '@'
	      },
	  };
	});