angular
    .module('communs')
    .service('HttpService', HttpService);


HttpService.$inject = [
    '$http',
    'ConfigService',
    'MessageService'
];

function HttpService(
    $http,
    ConfigService,
    MessageService
) {

    this.post = function( link, dados, funcSucesso, funcError){
        $http.post(ConfigService.getServidor() + link, dados)
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
        $http.put(ConfigService.getServidor() + link, dados)
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
        $http.delete(ConfigService.getServidor() + link)
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

    this.funcErrorDeslog = function(data, cod){

    };


    this.get = function( link, funcSucesso, funcError){
        $http.get(ConfigService.getServidor() + link)
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

    this.getReportConsultaGet = function( link, funcSucesso, funcError){
        var linkPdf = link ;

        $http.get(ConfigService.getServidorRelatorio() + linkPdf, {responseType: 'arraybuffer'})
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

        $http.post(ConfigService.getServidorRelatorio() + linkPdf, dados)
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

    this.getReportPoint = function(point,  link, dados, funcSucesso, funcError){

        var linkPdf = link + point;
        var linkValidar = link + "validar";


        function errorRelInterno(data){
            funcError(data);
        }

        function sucessoRelInterno(data){

            $http.post(ConfigService.getServidorRelatorio() + linkPdf, dados, {responseType: 'arraybuffer'})
                .success(funcSucesso)
                .error(funcError);

        }

        $http({
            url: ConfigService.getServidorRelatorio() + linkValidar,
            method: "POST",
            data: dados,
            headers: {
                'Content-type': 'application/json'
            }
        })
            .success(sucessoRelInterno)
            .error(errorRelInterno);
    };
    
    this.getReport = function( link, dados, funcSucesso, funcError){
        // var tipoRel = dados.tipoRelatorio;
        var linkPdf = "";

        linkPdf = link + "pdf";

        // if(tipoRel == "ETIQUETA"){
        //     linkPdf = link + "etiqueta";
        // } else {
        // }
        
    	var linkValidar = link + "validar";

    	
    	function errorRelInterno(data){
    		funcError(data);
        }

        function sucessoRelInterno(data){
        	
        	$http.post(ConfigService.getServidorRelatorio() + linkPdf, dados, {responseType: 'arraybuffer'})
            .success(funcSucesso)
            .error(funcError);
        	
        }
        
        $http({
    	    url: ConfigService.getServidorRelatorio() + linkValidar,
    	    method: "POST",
    	    data: dados, 
    	    headers: {
    	       'Content-type': 'application/json'
    	    }
    	})
    	.success(sucessoRelInterno)
        .error(errorRelInterno);
    };

    this.getReportExec = function( link, funcSucesso, funcError){

        $http.get(ConfigService.getServidorRelatorio() + link, {responseType: 'arraybuffer'})
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

}