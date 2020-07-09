
angular
    .module('partido')
    .controller('PartidoCrudController', PartidoCrudController );


	PartidoCrudController.$inject = [
        '$stateParams',
        '$scope',
        '$state',
        'MessageService',
        'PartidoService'
    ];

    function PartidoCrudController(
    								$stateParams,
                                    $scope,
                                    $state,
                                    MessageService,
                                    PartidoService
    ){

    	$scope.titulo = $state.current.data.pageTitle;

    	$scope.alteracao = false;
    	
    	var novo = function(){
    		
    		return {
    			descricao: '',
    			sigla: ''
    		};
    	}
    	
    	var initEditar = function(){
    		MessageService.showAguarde();
            
            function error(data){
                MessageService.stopAguarde();
                MessageService.showAlerta(null, data.message);
            }

            function sucesso(data){
                MessageService.stopAguarde();
                $scope.bean = data;
                $scope.alteracao = true;
            }

            PartidoService.getById($stateParams.id, sucesso, error);
    	}
    	
    	$scope.bean = novo();
    
    	$scope.setErroForm = function(){
        	$scope.erroForm = true;
        }
        
        $scope.zerarErroForm = function(){
        	$scope.erroForm = false;
        }
    	
    	$scope.gravar = function(ev){
    		
    		$scope.zerarErroForm();
    		
    		$scope.$broadcast('formClickValidation', [1,2,3]);
    		
    		if ( !$scope.projectForm.$valid || $scope.erroForm  ) {
                return null;
            }
    		
            MessageService.showConfirmar(ev, $scope.gravarOK)
    	};
    	
    	$scope.gravarOK = function(){
        
    		MessageService.showAguarde();
            
            function error(data){
                MessageService.stopAguarde();
                MessageService.showAlerta(null, data.message);
            }

            function sucesso(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Partido incluído com sucesso.");
                $scope.bean = novo();
            }
            
            function sucessoAlterar(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Partido alterado com sucesso.");
            }

            if( $scope.alteracao ){
            	PartidoService.alterar($scope.bean, sucessoAlterar, error);
            }else{
            	PartidoService.incluir($scope.bean, sucesso, error);
            }
    	};
    	
    	if( $stateParams.id != null ){
    		initEditar($stateParams.id);
    	}
    	
    	
    }