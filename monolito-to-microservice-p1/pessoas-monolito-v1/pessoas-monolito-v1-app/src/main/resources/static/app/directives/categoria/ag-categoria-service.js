
angular
	.module('diretivas')
    .service('AgCategoriaService', AgCategoriaService );


	AgCategoriaService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function AgCategoriaService(HttpService, ConfigService){
    	
    	this.getCategorias = function(consulta, funcSucesso, funcError){
    		
            HttpService.get("/cargo/categoria/consulta/componente?nome="+consulta, funcSucesso, funcError);
        }
    	
    }