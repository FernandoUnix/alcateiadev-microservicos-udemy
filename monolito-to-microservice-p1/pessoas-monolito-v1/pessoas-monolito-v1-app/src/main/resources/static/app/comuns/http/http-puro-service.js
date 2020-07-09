angular
    .module('communs')
    .service('HttpPuroService', HttpPuroService);


HttpPuroService.$inject = [
    '$http',
    'ConfigService'
];

function HttpPuroService(
    $http,
    ConfigService
) {

    this.post = function( link, dados, funcSucesso, funcError){
        $http.post(ConfigService.getServidorPuro() + link, dados)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });
    };
    
    this.put = function( link, dados, funcSucesso, funcError){
        $http.put(ConfigService.getServidorPuro() + link, dados)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });
    };
    
    this.delete = function( link, funcSucesso, funcError){
        $http.delete(ConfigService.getServidorPuro() + link)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });
    };

    this.get = function( link, funcSucesso, funcError){
        $http.get(ConfigService.getServidorPuro() + link)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });
    };
    
    this.getExterno = function( link, funcSucesso, funcError){
        $http.get(link)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });
    };

    this.getReportConsulta = function( link, dados, funcSucesso, funcError){
        var linkPdf = link + "consultar";

        $http.post(ConfigService.getServidorPuroRelatorio() + linkPdf, dados)
            .success(funcSucesso)
            .error(function(data, cod){
                if( cod >= 401 && cod <= 499){
                    MessageService.stopAguarde();
                    document.location.href = "/?msgdeslog";
                }else{
                    funcError(data);
                }
            });

    };

    this.getReport = function( link, dados, funcSucesso, funcError){
        
    	var linkPdf = link + "pdf";
    	var linkValidar = link + "validar";

    	
    	function errorRelInterno(data){
    		funcError(data);
        }

        function sucessoRelInterno(data){
        	
        	$http.post(ConfigService.getServidorPuroRelatorio() + linkPdf, dados, {responseType: 'arraybuffer'})
            .success(funcSucesso)
            .error(funcError);
        	
        }
        
        $http({
    	    url: ConfigService.getServidorPuroRelatorio() + linkValidar,
    	    method: "POST",
    	    data: dados, 
    	    headers: {
    	       'Content-type': 'application/json'
    	    }
    	})
    	.success(sucessoRelInterno)
        .error(errorRelInterno);
        
    	
    	
    	/*
    	
    	*/
    };

    this.getReportExec = function( link, funcSucesso, funcError){

        $http.post(ConfigService.getServidorPuroRelatorio() + link, {responseType: 'arraybuffer'})
            .success(funcSucesso)
            .error(funcError);

    };

}