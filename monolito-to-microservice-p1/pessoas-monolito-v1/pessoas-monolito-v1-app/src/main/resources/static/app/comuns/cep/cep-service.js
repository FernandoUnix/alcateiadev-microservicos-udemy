angular
    .module('communs')
    .service('CepService', CepService);


CepService.$inject = [
    'HttpService'
];

function CepService(
    HttpService
) {

	
	this.getCep = function(cep, funcSucesso, funcError){
		
		cep = cep.replace("-", "");
		
		var link = "https://viacep.com.br/ws/" + cep + "/json/";
			
		HttpService.getExterno(link, funcSucesso, funcError);
		
	}
	
}