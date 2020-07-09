angular
    .module('partido')
    .config(configState);

	function configState($stateProvider, $urlRouterProvider, $compileProvider) {
	  
		$stateProvider.state('app_views.partido', {
			url: "/partido",
	        templateUrl: "app/modulos/partido/partido-list.html",
	        data: {
	            pageTitle: 'PARTIDO'
	        },
	        controller: 'PartidoListController',
            controllerAs: 'partidoListController'
	    });
	
	    $stateProvider.state('app_views.partido_new', {
	    	url: "/partido/new",
	        templateUrl: "app/modulos/partido/partido-crud.html",
	        data: {
	            pageTitle: 'Partidos - ADICIONAR'
	        },
            controller: 'PartidoCrudController',
            controllerAs: 'partidoCrudController'
	    });		
	    
	    $stateProvider.state('app_views.partido_edit', {
	    	url: "/partido/edit/:id",
	        templateUrl: "app/modulos/partido/partido-crud.html",
	        data: {
	            pageTitle: 'PARTIDO - ALTERAR'
	        },
            controller: 'PartidoCrudController',
            controllerAs: 'partidoCrudController'
	    });		
	    
	    
        
	}
    