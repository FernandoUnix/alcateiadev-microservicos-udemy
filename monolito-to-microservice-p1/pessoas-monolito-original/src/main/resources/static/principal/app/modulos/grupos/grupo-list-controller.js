
angular
    .module('grupo')
    .controller('GrupoListController', GrupoListController );


	GrupoListController.$inject = [
	    '$location',
        '$scope',
        '$state',
        'MessageService',
        'GrupoService'
    ];

    function GrupoListController(
    								$location,
                                    $scope,
                                    $state,
                                    MessageService,
                                    GrupoService
    ){

    	$scope.titulo = $state.current.data.pageTitle;

    	$scope.listaCampos = [
								{label:'Código',    campo:'CODIGO',   checked: true},
								{label:'Nome',      campo:'NOME',     checked: true}
	                      ];
    	
    	var getCamposOrdenacao = function(){
    		var itens = "";
    		var loop = 0;
    		for(loop=0; loop < $scope.listaCampos.length; loop++){
    			var item = $scope.listaCampos[loop];
    			if( item.checked ){
    				itens = itens + item.campo + "_";
    			}
    		}
    		return itens;
    	}
    	
    	$scope.filtro = {
    			nome: '',
    			codigo: ''
    	};
    	
    	$scope.editar = function(idSel){
    		$location.path('/app/grupo/edit/'+idSel);
    	}
    	
    	$scope.excluir = function(idSel){
    		$scope.selecionado = idSel;
    		MessageService.showConfirmar(null, $scope.excluirOK);
    	}
    	
    	$scope.excluirOK = function(){
    		MessageService.showAguarde();
            
            function error(data){
                MessageService.stopAguarde();
                MessageService.showAlerta(null, data.message);
            }

            function sucesso(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Grupo excluído com sucesso.");
                $scope.consultar();        
            }
            
        	GrupoService.excluir($scope.selecionado, sucesso, error);
            
    	}
    	
    	$scope.grid = {
    			colunas:[
    			         {label:'Código', campo:'codigo', width: '10%'},
    			         {label:'Nome',campo:'nome', width: '65%'}
    			],
    			paginacao:[]
    	};
    	
    	$scope.consultar = function(pos){
            
    		MessageService.showAguarde();
            
            function error(data){
                MessageService.stopAguarde();
                MessageService.showAlerta(null, data.message);
            }

            function sucesso(data){
                $scope.grid.paginacao = data;
                MessageService.stopAguarde();
            }

            GrupoService.consultar($scope.filtro, pos, getCamposOrdenacao(), sucesso, error);
    	};
    	
    	$scope.consultar(0);
    	
    	
    }