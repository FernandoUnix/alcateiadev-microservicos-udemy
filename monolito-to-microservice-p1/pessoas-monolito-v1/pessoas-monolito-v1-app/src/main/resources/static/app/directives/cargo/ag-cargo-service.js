
angular
	.module('diretivas')
    .service('AgCargoService', AgCargoService );


	AgCargoService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function AgCargoService(HttpService, ConfigService){
    	
    	this.getCargos = function(consulta, ignorar, funcSucesso, funcError){
    		
    		if( consulta == "" ){
    			funcSucesso([]);
    			return;
    		}
    		
            HttpService.get("/cargo/consulta/componente?nome="+consulta+"&ignorar="+ignorar, funcSucesso, funcError);
        }
    	
    }