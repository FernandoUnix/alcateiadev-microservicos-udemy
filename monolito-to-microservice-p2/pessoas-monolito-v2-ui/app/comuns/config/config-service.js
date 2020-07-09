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
        return $location.protocol()+"://"+$location.host()+":"+$location.port()+"/principal";
    };

    this.getServidor = function(){
    	return $location.protocol()+"://"+$location.host()+":"+$location.port()+"/principal";
    };

    this.getServidorRelatorio = function(){
    	return $location.protocol()+"://"+$location.host()+":"+$location.port()+"/relatorio";
    }
}