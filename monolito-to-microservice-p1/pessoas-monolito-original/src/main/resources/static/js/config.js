
function configState($stateProvider, $urlRouterProvider, $compileProvider) {

    $compileProvider.debugInfoEnabled(true);

    // Set default state
    $urlRouterProvider.otherwise("logar");
    $stateProvider

		.state('dashboard', {
            url: "/dashboard",
            templateUrl: "views/dashboard.html",
            data: {
                pageTitle: 'Dashboard'
            }
        })

        .state('erro', {
            url: "/erro",
            templateUrl: "error.html",
            data: {
                pageTitle: 'Erro inexperado'
            }
        })
        
		.state('commonlogin', {
            url: "/logar",
            templateUrl: "logar.html",
            data: {
                pageTitle: 'Entre com os dados de acesso',
                specialClass: 'blank'
            }
	    })
	    
	    .state('reguser', {
            url: "/reguser",
            templateUrl: "reguser.html",
            params: {chave:null},
            data: {
                pageTitle: 'Entre com os dados de acesso',
                specialClass: 'blank'
            }
	    })
	    
}

angular
    .module('opscadastro')
    .config(configState)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });