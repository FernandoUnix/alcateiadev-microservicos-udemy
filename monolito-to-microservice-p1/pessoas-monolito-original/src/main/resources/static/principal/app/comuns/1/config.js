
function configState($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/app/dashboard");
    
    $stateProvider

	    .state('app_views', {
            abstract: true,
	        url: "/app",
	        templateUrl: "app/modulos/principal/principal.html",
	        controller: 'PrincipalController',
            controllerAs: 'principalController'
	    })
    
		.state('app_views.dashboard', {
            url: "/dashboard",
            templateUrl: "app/modulos/dashboard/dashboard.html",
			controller: 'DashboardController',
			controllerAs: 'dashboardController'
        })
        
        .state('app_views.bairro', {
	        url: "/bairro",
	        templateUrl: "app/modulos/bairro/bairro-list.html",
	        data: {
	            pageTitle: 'Bairro'
	        }
	    })
	
	    .state('app_views.bairro_new', {
	        url: "/new",
	        templateUrl: "app/modulos/bairro/bairro-crud.html",
	        data: {
	            pageTitle: 'Adicionar Bairro'
	        }
	    });
    
	    
    
	    

}

angular
    .module('pessoas')
    .config(configState)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
        $rootScope.loading = false;

    });