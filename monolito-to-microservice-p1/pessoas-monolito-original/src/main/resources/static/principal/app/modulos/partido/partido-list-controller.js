
angular
    .module('partido')
    .controller('PartidoListController', PartidoListController );


	PartidoListController.$inject = [
	    '$location',
        '$scope',
        '$state',
        'MessageService',
        'PartidoService',
        'FileSaver'
    ];

    function PartidoListController(
    								$location,
                                    $scope,
                                    $state,
                                    MessageService,
                                    PartidoService,
                                    FileSaver
    ){

    	$scope.titulo = $state.current.data.pageTitle;
    	
    	$scope.filtro = {
    			nome: '',
    			codigo: ''
    	};
    	$scope.backup = function(pos){
                    MessageService.showAguarde();

                    function error(data){
                        MessageService.stopAguarde();
                        MessageService.showAlerta(null, data.message);
                    }

                    function sucesso(data){
                        MessageService.stopAguarde();
                        var typeStr = "application/vnd.ms-excel;charset=charset=utf-8";
                        var file = new Blob([data], {type: typeStr});
                        FileSaver.saveAs(file, 'Backup.xls');
                    }

                    PartidoService.backup(sucesso, error);
                };

    	
    	$scope.editar = function(idSel){
    		$location.path('/app/partido/edit/'+idSel);
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
                MessageService.showInformacao("Partido excluído com sucesso.");
                $scope.consultar();        
            }
            
        	PartidoService.excluir($scope.selecionado, sucesso, error);
            
    	}
    	
    	$scope.listaCampos = [
								{label:'Sigla',     campo:'SIGLA',   checked: true},
								{label:'Descrição', campo:'DESCRICAO',     checked: true}
	                      ];
    	
    	$scope.grid = {
    			colunas:[
    			         {label:'Sigla', campo:'sigla', width: '10%'},
    			         {label:'Descrição',campo:'descricao', width: '65%'}
    			],
    			paginacao:[]
    	};
    	
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

            PartidoService.consultar($scope.filtro, pos, getCamposOrdenacao(), sucesso, error);
    	};
    	
    	$scope.consultar(0);
    	
    	
    }