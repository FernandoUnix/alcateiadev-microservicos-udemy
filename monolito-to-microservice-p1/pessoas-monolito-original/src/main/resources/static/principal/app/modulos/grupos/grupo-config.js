angular
    .module('grupo')
    .config(configState);

	function configState($stateProvider, $urlRouterProvider, $compileProvider) {
	  
		$stateProvider.state('app_views.grupo', {
			url: "/grupo",
	        templateUrl: "app/modulos/grupos/grupo-list.html",
	        data: {
	            pageTitle: 'GRUPO'
	        },
	        controller: 'GrupoListController',
            controllerAs: 'grupoListController'
	    });
	
	    $stateProvider.state('app_views.grupo_new', {
	    	url: "/grupo/new",
	        templateUrl: "app/modulos/grupos/grupo-crud.html",
	        data: {
	            pageTitle: 'GRUPO - ADICIONAR GRUPO'
	        },
            controller: 'GrupoCrudController',
            controllerAs: 'grupoCrudController'
	    });		
	    
	    $stateProvider.state('app_views.grupo_edit', {
	    	url: "/grupo/edit/:id",
	        templateUrl: "app/modulos/grupos/grupo-crud.html",
	        data: {
	            pageTitle: 'GRUPO - ALTERAR GRUPO'
	        },
            controller: 'GrupoCrudController',
            controllerAs: 'grupoCrudController'
	    });		
	    
	    
        
	}
    