angular
    .module('communs')
    .service('MessageService', MessageService);


    MessageService.$inject = [
        '$mdDialog',
        'blockUI'
    ];

    function MessageService(
		$mdDialog,
		blockUI
    ){

        this.showAguarde = function (){
            blockUI.start({message: 'Aguarde...'});
        };

        this.stopAguarde = function (){
            blockUI.stop();
        };

        this.showAlertaError = function (ev, objeto){
        	
        	if( objeto.toString() == "[object ArrayBuffer]"){
        		this.showAlerta(ev, "Nenhum registro encontrado.");
        		return;
        	}
        	
        	var erros = objeto["errors"];
        	var mensagem = objeto["message"];
        	
        	if( erros == null || erros == undefined ){
        		this.showAlerta(ev, mensagem);
        		return;
        	}
        	
        	
        	mensagem = erros[0].defaultMessage;
        	
        	this.showAlerta(ev, mensagem);
        };
        
        this.showAlerta = function (ev, mensagem){

        	$mdDialog.show(
        			
	        	 $mdDialog.alert()
		             .parent(angular.element(document.querySelector('#popupContainer')))
		             .clickOutsideToClose(true)
		             .title('Alerta')
		             .textContent(mensagem)
		             .ok('OK')
		             .targetEvent(ev)
        	 );
             
        };

        this.showDuplicidade = function(mensagens, ev,  executarConfirmar ){

        	var msg = "";
        	var loop=0;
			for(loop=0; loop < mensagens.length; loop++){
				var item = mensagens[loop];
				msg = msg + item + "\n'";
			}

            var confirm = $mdDialog.confirm()
                .title('Nomes duplicados na mesma cidade. Deseja continuar?')
                .textContent(msg)
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Sim')
                .cancel('Não');

            $mdDialog.show(confirm).then(function() {
                executarConfirmar();
            }, function() {

            });

        };

        this.showConfirmar = function(ev,  executarConfirmar ){

        	var confirm = $mdDialog.confirm()
	            .title('Confirmação')
	            .textContent('Deseja continuar?')
	            .ariaLabel('Lucky day')
	            .targetEvent(ev)
	            .ok('Sim')
	            .cancel('Não');
        	
		      $mdDialog.show(confirm).then(function() {
		    	  executarConfirmar();
		      }, function() {
		        
		      });
      
        };

        this.showInformacao = function( mensagem, executarConfirmar ){

        	$mdDialog.show(
        			
   	        	 $mdDialog.alert()
   		             .parent(angular.element(document.querySelector('#popupContainer')))
   		             .clickOutsideToClose(true)
   		             .title('Informação')
   		             .textContent(mensagem)
   		             .ok('OK')
   		             .targetEvent(null)
   		             
           	 );
        	
        	/*
            sweetAlert.swal({
                    title: "Informação",
                    text: mensagem},

                function (isConfirm) {

                    if (isConfirm) {
                        executarConfirmar();
                    }

                });
*/
        }

    }