
angular
    .module('grupo')
    .controller('GrupoCrudController', GrupoCrudController );


	GrupoCrudController.$inject = [
        '$stateParams',
        '$scope',
        '$state',
        'MessageService',
        'GrupoService'
    ];

    function GrupoCrudController(
    								$stateParams,
                                    $scope,
                                    $state,
                                    MessageService,
                                    GrupoService
    ){

    	$scope.titulo = $state.current.data.pageTitle;

    	$scope.alteracao = false;
    	
    	var novo = function(){
    		
    		return {
    			nome: '',
    			codigo: ''
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
                $scope.grupo = data;
                $scope.alteracao = true;
            }

            GrupoService.getById($stateParams.id, sucesso, error);
    	}
    	
    	$scope.grupo = novo();
    
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
                MessageService.showInformacao("Grupo incluído com sucesso.");
                $scope.grupo = novo();
            }
            
            function sucessoAlterar(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Grupo alterado com sucesso.");
            }

            if( $scope.alteracao ){
            	GrupoService.alterar($scope.grupo, sucessoAlterar, error);
            }else{
            	GrupoService.incluir($scope.grupo, sucesso, error);
            }
    	};
    	
    	if( $stateParams.id != null ){
    		initEditar($stateParams.id);
    	}
    	
    	
    }