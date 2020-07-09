angular
    .module('communs')
    .service('ConfigService', ConfigService);




ConfigService.$inject = [
                         '$location'
];

function ConfigService(
		$location
) {

    this.getServidorPuro = function(){
        return $location.protocol()+"://"+$location.host()+":"+$location.port();
    };

    this.getServidor = function(){
    	return $location.protocol()+"://"+$location.host()+":"+$location.port();
    };

    this.getServidorRelatorio = function(){
    	return $location.protocol()+"://"+$location.host()+":"+$location.port()+"/relatorio";
    }
}