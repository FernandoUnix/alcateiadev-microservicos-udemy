
angular
	.module('grupo')
    .service('GrupoService', GrupoService );


	GrupoService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function GrupoService(HttpService, ConfigService){
    	
    	this.incluir = function(grupo, funcSucesso, funcError){
    		HttpService.post("/grupo/incluir", grupo, funcSucesso, funcError);
    	}
    	
    	this.alterar = function(grupo, funcSucesso, funcError){
    		HttpService.put("/grupo/alterar", grupo, funcSucesso, funcError);
    	}
    	
    	this.excluir = function(id, funcSucesso, funcError){
    		HttpService.delete("/grupo/excluir/"+id, funcSucesso, funcError);
    	}
    
    	this.consultar = function(grupo, pos, ordenacao, funcSucesso, funcError){
    		
    		pos = pos == undefined || pos == null ? 0 : pos;
    		
    		var args = "pos="+pos;
    		if( grupo.codigo != null && grupo.codigo != "" ){
    			args = "&codigo="+grupo.codigo;
    		}
    		
    		if( grupo.nome != null && grupo.nome != "" ){
    			args = "&nome="+grupo.nome;
    		}
    		
    		if( ordenacao != null && ordenacao != "" ){
    			args = args+ "&ordenacao="+ordenacao;
    		}
    		
    		HttpService.get("/grupo/consulta?"+args, funcSucesso, funcError);
    	}
    	
    	this.getById = function(id, funcSucesso, funcError){
    		HttpService.get("/grupo/"+id, funcSucesso, funcError);
    	}
    }