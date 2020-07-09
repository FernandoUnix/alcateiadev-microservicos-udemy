angular
	.module('diretivas')
	.directive('agCargo', function() {
	  return {
		restrict     : 'E',
	    templateUrl  : 'app/directives/cargo/ag-cargo.html',
	    controller   : 'AgCargoController',
	    controllerAs : 'agCargoController',
	    scope: {
	    	ngForm: '=',
	        ngModelSelecionado: '=',
	        ngRequired: '=',
	        ngLabel: '@',
	        ngIgnorar: '='
	      },
	  };
	});