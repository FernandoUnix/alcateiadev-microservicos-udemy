
angular
    .module('pessoaFisica')
    .controller('PessoaFisicaListController', PessoaFisicaListController );


	PessoaFisicaListController.$inject = [
	    '$location',
        '$scope',
        '$state',
        'MessageService',
        'PessoaFisicaService',
        'FileSaver'
    ];

    function PessoaFisicaListController(
    								$location,
                                    $scope,
                                    $state,
                                    MessageService,
                                    PessoaFisicaService,
                                    FileSaver
    ){

    	$scope.titulo = $state.current.data.pageTitle;
    	
    	$scope.filtro = {
    			nome: '',
    			codigo: ''
    	};
    	
    	$scope.editar = function(idSel){
    		$location.path('/app/pessoafisica/edit/'+idSel);
    	}
    	
    	$scope.excluir = function(idSel){
    		$scope.selecionado = idSel;
    		MessageService.showConfirmar(null, $scope.excluirOK);
    	};
    	
    	$scope.excluirOK = function(){
    		MessageService.showAguarde();
            
            function error(data){
                MessageService.stopAguarde();
                MessageService.showAlerta(null, data.message);
            }

            function sucesso(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Pessoa Física excluído com sucesso.");
                $scope.consultar();        
            }
            
        	PessoaFisicaService.excluir($scope.selecionado, sucesso, error);
            
    	};
    	
    	$scope.listaCampos = [
									{label:'Código',    campo:'CODIGO',   checked: false},
									{label:'Nome',      campo:'NOME',     checked: false},
									{label:'Telefone',  campo:'TELEFONE', checked: false},
									{label:'Bairro',  campo:'BAIRRO', checked: false}
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
    	
    	
    	$scope.grid = {
    			colunas:[
    			         {label:'Código', campo:'codigo', width: '10%'},
    			         {label:'Nome',campo:'dpNome', width: '35%'},
    			         {label:'Telefone',campo:'enderTelefonePrincipal', width: '10%'},
						 {label:'Endereço',campo:'enderLogradouro', width: '15%'},
						 {label:'Bairro',campo:'enderBairro', width: '15%'},
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

            PessoaFisicaService.consultar($scope.filtro, pos, getCamposOrdenacao(), sucesso, error);
    	};
    	
    	$scope.consultar(0);


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

            PessoaFisicaService.backup(sucesso, error);
        };

    }