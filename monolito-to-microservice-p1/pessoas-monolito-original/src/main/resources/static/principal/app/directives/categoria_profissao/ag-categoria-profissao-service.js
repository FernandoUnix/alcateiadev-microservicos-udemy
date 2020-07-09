
angular
	.module('diretivas')
    .service('AgCategoriaProfissaoService', AgCategoriaProfissaoService );


	AgCategoriaProfissaoService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function AgCategoriaProfissaoService(HttpService, ConfigService){
    	
    	this.getCategorias = function(consulta, funcSucesso, funcError){
    		
            HttpService.get("/profissao/categoria/consulta/componente?nome="+consulta, funcSucesso, funcError);
        }
    	
    }