angular
    .module('pessoaFisica')
    .config(configState);

	function configState($stateProvider, $urlRouterProvider, $compileProvider) {
	  
		$stateProvider.state('app_views.pessoafisica', {
			url: "/pessoafisica",
	        templateUrl: "app/modulos/pessoafisica/pessoafisica-list.html",
	        data: {
	            pageTitle: 'PESSOA FÍSICA'
	        },
	        controller: 'PessoaFisicaListController',
            controllerAs: 'pessoaFisicaListController'
	    });
	
	    $stateProvider.state('app_views.pessoafisica_new', {
	    	url: "/pessoafisica/new",
	        templateUrl: "app/modulos/pessoafisica/pessoafisica-crud.html",
	        data: {
	            pageTitle: 'PESSOA FÍSICA - ADICIONAR '
	        },
	        controller: 'PessoaFisicaCrudController',
            controllerAs: 'pessoaFisicaCrudController'
	    });		
	    
	    $stateProvider.state('app_views.pessoafisica_edit', {
	    	url: "/pessoafisica/edit/:id",
	        templateUrl: "app/modulos/pessoafisica/pessoafisica-crud.html",
	        data: {
	            pageTitle: 'PESSOA FÍSICA - ALTERAR'
	        },
	        controller: 'PessoaFisicaCrudController',
            controllerAs: 'pessoaFisicaCrudController'
	    });		
	
	    $stateProvider.state('app_views.pessoafisica_report', {
			url: "/pessoafisica/report",
	        templateUrl: "app/modulos/pessoafisica/pessoafisica-report.html",
	        data: {
	            pageTitle: 'PESSOA FÍSICA - RELATÓRIO'
	        },
	        controller: 'PessoaFisicaReportController',
            controllerAs: 'pessoaFisicaReportController'
	    });
	    
	}
    