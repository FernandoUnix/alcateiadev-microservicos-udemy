
angular
	.module('partido')
    .service('PartidoService', PartidoService );


	PartidoService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function PartidoService(HttpService, ConfigService){

    	this.backup = function(funcSucesso, funcError){
            HttpService.getReportConsultaGet("/backup/excel/Partido", funcSucesso, funcError);
        };

    	this.incluir = function(bean, funcSucesso, funcError){
    		HttpService.post("/partido/incluir", bean, funcSucesso, funcError);
    	}
    	
    	this.alterar = function(bean, funcSucesso, funcError){
    		HttpService.put("/partido/alterar", bean, funcSucesso, funcError);
    	}
    	
    	this.excluir = function(id, funcSucesso, funcError){
    		HttpService.delete("/partido/excluir/"+id, funcSucesso, funcError);
    	}
    
    	this.consultar = function(bean, pos, ordenacao, funcSucesso, funcError){
    		
    		pos = pos == undefined || pos == null ? 0 : pos;
    		
    		var args = "pos="+pos;
    		if( bean.sigla != null && bean.sigla != "" ){
    			args = args+"&sigla="+bean.sigla;
    		}
    		
    		if( ordenacao != null && ordenacao != "" ){
    			args = args+ "&ordenacao="+ordenacao;
    		}
    		
    		HttpService.get("/partido/consulta?"+args, funcSucesso, funcError);
    	}
    	
    	this.getById = function(id, funcSucesso, funcError){
    		HttpService.get("/partido/"+id, funcSucesso, funcError);
    	}
    }