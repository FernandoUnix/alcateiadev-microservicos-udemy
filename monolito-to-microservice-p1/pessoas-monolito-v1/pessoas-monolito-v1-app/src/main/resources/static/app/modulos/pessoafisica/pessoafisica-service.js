
angular
	.module('pessoaFisica')
    .service('PessoaFisicaService', PessoaFisicaService );


	PessoaFisicaService.$inject = [
		'HttpService',
		'ConfigService'
    ];

    function PessoaFisicaService(HttpService, ConfigService){

        this.nomeExistente = function(id, nome, cidade, funcSucesso, funcError){

        	var args = "nome="+nome+"&";
        	if( id != null ){
                args = args + "id="+id;
			}
			if(cidade != null){
        		args = args + "&cidade="+cidade;
			}

            HttpService.get("/pessoa/fisica/consulta/nome?"+args, funcSucesso, funcError);
        };

    	this.proximoCodigo = function(funcSucesso, funcError){
    		HttpService.get("/pessoa/fisica/consulta/proximoCodigo", funcSucesso, funcError);
    	};
    	
    	this.incluir = function(bean, funcSucesso, funcError){
    		HttpService.post("/pessoa/fisica/incluir", bean, funcSucesso, funcError);
    	};
    	
    	this.alterar = function(bean, funcSucesso, funcError){
    		HttpService.put("/pessoa/fisica/alterar", bean, funcSucesso, funcError);
    	};
    	
    	this.excluir = function(id, funcSucesso, funcError){
    		HttpService.delete("/pessoa/fisica/excluir/"+id, funcSucesso, funcError);
    	};
    
    	this.relatorio = function(bean, funcSucesso, funcError){
    		HttpService.getReport("/pessoa/fisica/", bean, funcSucesso, funcError);
    	};

		this.relatorioFicha = function(bean, funcSucesso, funcError){
			HttpService.getReportExec("/pessoa/fisica/ficha/"+bean, funcSucesso, funcError);
		};
		
		this.relatorioTxt = function(bean, funcSucesso, funcError){
			HttpService.getReportPoint("emails", "/pessoa/fisica/", bean, funcSucesso, funcError);
		};

		this.relatorioConsulta = function(bean, funcSucesso, funcError){
			HttpService.getReportConsulta("/pessoa/fisica/", bean, funcSucesso, funcError);
		};

		this.backup = function(funcSucesso, funcError){
            HttpService.getReportConsultaGet("/backup/excel/PessoaFisica", funcSucesso, funcError);
        };

		this.consultarCidadePorNome = function(uf, cidade, funcSucesso, funcError){

			cidade = cidade.replace(" ", "_");

			var mapaAcentosHex = {
				a : /[\xE0-\xE6]/g,
				A : /[\xC0-\xC6]/g,
				e : /[\xE8-\xEB]/g,
				E : /[\xC8-\xCB]/g,
				i : /[\xEC-\xEF]/g,
				I : /[\xCC-\xCF]/g,
				o : /[\xF2-\xF6]/g,
				O : /[\xD2-\xD6]/g,
				u : /[\xF9-\xFC]/g,
				U : /[\xD9-\xDC]/g,
				c : /\xE7/g,
				C : /\xC7/g,
				n : /\xF1/g,
				N : /\xD1/g
			};

			for ( var letra in mapaAcentosHex ) {
				var expressaoRegular = mapaAcentosHex[letra];
				cidade = cidade.replace( expressaoRegular, letra );
			}

			HttpService.get("/localizacao/cidade/"+uf+"/"+cidade, funcSucesso, funcError);
		};
    	
    	this.consultar = function(bean, pos, ordenacao, funcSucesso, funcError){
    		
    		pos = pos == undefined || pos == null ? 0 : pos;
    		
    		var args = "pos="+pos;
    	
    		if( bean.codigo != null && bean.codigo != "" ){
    			args = args+ "&codigo="+bean.codigo;
    		}
    		
    		if( bean.nome != null && bean.nome != "" ){
    			args = args+ "&nome="+bean.nome;
    		}
    		
    		if( ordenacao != null && ordenacao != "" ){
    			args = args+ "&ordenacao="+ordenacao;
    		}
    		
    		HttpService.get("/pessoa/fisica/consulta?"+args, funcSucesso, funcError);
    	};
    	
    	this.getById = function(id, funcSucesso, funcError){
    		HttpService.get("/pessoa/fisica/"+id, funcSucesso, funcError);
    	}
    }