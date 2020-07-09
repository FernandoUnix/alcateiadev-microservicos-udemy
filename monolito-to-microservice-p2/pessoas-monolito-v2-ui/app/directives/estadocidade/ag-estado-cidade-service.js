
angular
	.module('diretivas')
    .service('AgEstadoCidadeService', AgEstadoCidadeService );


	AgEstadoCidadeService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function AgEstadoCidadeService(HttpService, ConfigService){
    	
    	this.getEstados = function(funcSucesso, funcError){
            HttpService.get("/localizacao/estados", funcSucesso, funcError);
        };
    
    	this.getCidades = function(estado, funcSucesso, funcError){
            HttpService.get("/localizacao/cidades/"+estado, funcSucesso, funcError);
        };
    	
    	this.getRegional = function(estado, funcSucesso, funcError){
            HttpService.get("/localizacao/cidades/regionais/"+estado, funcSucesso, funcError);
        };

        this.getNomeRegional = function(cidade, funcSucesso, funcError){
            HttpService.get("/localizacao/cidades/regional/nome/"+cidade, funcSucesso, funcError);
        };

    }