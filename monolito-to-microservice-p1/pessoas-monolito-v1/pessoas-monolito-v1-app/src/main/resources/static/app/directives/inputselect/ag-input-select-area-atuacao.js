
angular
	.module('diretivas')
    .service('AgInputSelectAreaAtuacao', AgInputSelectAreaAtuacao );


	AgInputSelectAreaAtuacao.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function AgInputSelectAreaAtuacao(HttpService, ConfigService){
    	
    	this.consultar = function(codigo, pos, nome, ignore, funcSucesso, funcError){

			pos = pos == undefined || pos == null ? 0 : pos;

			var args = "pos="+pos;

			if( codigo != null && codigo != "" ){
    			args = "codigo="+codigo;
    		}
    		
    		if( nome != null && nome != "" ){
    			if( args != "" ){
    				args = args + "&";
    			}
    			args = args + "nome="+nome;
    		}
    		
    		if( ignore != null && ignore != "" ){
    			if( args != "" ){
    				args = args + "&";
    			}
    			args = args + "ignorar="+ignore;
    		}
    		
    		if( args != "" ){
    			args = "?" + args; 
    		}
    		
    		HttpService.get("/areaatuacao/consulta/componente"+args, funcSucesso, funcError);
    	}
    	
    }