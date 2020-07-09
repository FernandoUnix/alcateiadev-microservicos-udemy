
angular
    .module('pessoaFisica')
    .controller('PessoaFisicaCrudController', PessoaFisicaCrudController );


	PessoaFisicaCrudController.$inject = [
        '$stateParams',
        '$scope',
        '$state',
        'MessageService',
        'PessoaFisicaService',
        'CepService',
		'FileSaver'
    ];

    function PessoaFisicaCrudController(
    								$stateParams,
                                    $scope,
                                    $state,
                                    MessageService,
                                    PessoaFisicaService,
                                    CepService,
									FileSaver
                                    
    ){

    	$scope.titulo = $state.current.data.pageTitle;

    	$scope.alteracao = false;
    	
    	$scope.radioSexo = [
    	                    {value: 'MASCULINO', isDisabled: false, label: 'Masculino', chave: 'MASCULINO'},
    	                    {value: 'FEMININO', isDisabled: false, label: 'Feminino', chave: 'FEMININO'},
    	                    {value: 'DESCONHECIDO', isDisabled: false, label: 'Desconhecido', chave: 'DESCONHECIDO'}
        ];
    	
    	$scope.radioCorrespondencia = [
    	                    {value: 'TODA', isDisabled: false, label: 'Toda', chave: 'TODA'},
    	                    {value: 'APENAS_PESSOAL', isDisabled: false, label: 'Apenas Pessoa', chave: 'APENAS_PESSOAL'}
        ];
    	
    	$scope.radioTipoEndereco = [
   	                    {value: 'RESIDENCIAL', isDisabled: false, label: 'Residencial', chave: 'RESIDENCIAL'},
   	                    {value: 'COMERCIAL', isDisabled: false, label: 'Comercial', chave: 'COMERCIAL'}
       ];
    	
    	
    	var proximoCodigo = function(){

    		function error(data){
            }

            function sucesso(data){
            	$scope.nroCodigo = data;
            }

            $scope.nroCodigo = "...";
            PessoaFisicaService.proximoCodigo(sucesso, error);
    	};
    	
    	var getRadioData = function(chave){
    		var loop=0;
    		for(loop=0; loop < $scope.radioData.length; loop++){
    			var item = $scope.radioData[loop];
    			if( item.chave == chave ){
    				return item.value;
    			}
    		}
    		
    		return null;
    	};
    	
    	var novo = function(){
    		
    		return {
    			id: null,
    			dpTratamento: 'ILMO(A) SR(A)',
    			dpNome: '',
    			dpSexo: 'MASCULINO',
    			dpDtNascimento: null,
    			dpCriterio: '',
    			enderLogradouro: '',
    			enderBairro: '',
    			enderCep: '',
    			enderTelefonePrincipal: '',
    			enderTelefoneSecundario: '',
    			enderFax: '',
    			enderTelefoneOutro: '',
    			enderEncaminCorrespondencia: '',
    			enderCorrespondencia: null,
    			enderTipoEndereco: null,
    			tituloEleitor: '',
    			zona: '',
    			sessao: '',
    			twitter: '',
    			facebook:'',
    			dpApelido: '',
    			outrasInformacoes: '',
    			email: '',
    			emailOutros: '',
    			youtube: ''
    				
    		};
    	};
    	
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
                
                $scope.itemEstado = $scope.bean.enderEstadoId; //{id: $scope.bean.enderEstadoId};
                $scope.itemCidade = $scope.bean.enderCidadeId; //{id: $scope.bean.enderCidadeId};// $scope.bean.enderCidadeId; //

                $scope.nroCodigo = $scope.bean.codigo;
                
                if( $scope.bean.dpDtNascimento != null ){
                	$scope.dpDtNascimentoTmp = $scope.bean.dpDtNascimento.data;
                }
                
            	$scope.itensGrupo = $scope.bean.grupos;
				$scope.itensEvento = $scope.bean.eventos;

            	$scope.itensCargoPJ = $scope.bean.cargosPj;
            	$scope.itemProfissao = {id:$scope.bean.profissaoId, descricao: $scope.bean.profissaoNome, codigo:$scope.bean.profissaoCodigo};
            	$scope.itemPartido = {id:$scope.bean.partidoId, descricao: $scope.bean.partidoNome, codigo:$scope.bean.partidoCodigo};
            	
            }

            PessoaFisicaService.getById($stateParams.id, sucesso, error);
    	};
    	
    	var criarVars = function(){
	    	$scope.dpDtNascimentoTmp = null;
	    	$scope.itemEstado = null;
	    	$scope.itemCidade = null;
	    	$scope.itemGrupo = null;
			$scope.itensGrupo = [];

			$scope.itemEvento = null;
	    	$scope.itensEvento = [];
	    	
	    	$scope.itemCargo = null;
	    	$scope.itemPJ = null;
	    	$scope.itensCargoPJ = [];
	    	
	    	$scope.itemProfissao = null;
	    	$scope.itemPartido = null;
	    	
	    	$scope.selected = [];
			$scope.selectedEvento = [];
	    	$scope.selectedCargoPJ = [];    	
	    	
	    	// antigo
	    	$scope.itemAreaAtuacao = null;
	    	$scope.respDtPosseTmp = null;
	    	
	    	$scope.bean = novo();
	    	$scope.item = {};
    	};
    	
    	$scope.addCargoPJ = function(ev){
    		
    		if( isItemExisteCargoPJ($scope.itemCargo, $scope.itemPJ, $scope.itensCargoPJ) ){
    			MessageService.showAlerta(ev, 'Cargo e PJ já selecionado.');
    			$scope.itemCargo = {};
    			$scope.itemPJ = {};
    			return;
    		}
    		
    		$scope.itemCargo['nome'] = $scope.itemCargo.descricao;
    		$scope.itemPJ['nome'] = $scope.itemPJ.descricao;
    		
    		$scope.itensCargoPJ.push({'cargo': $scope.itemCargo, 'juridicaJson': $scope.itemPJ});
    		
    		$scope.itemCargo = {};
			$scope.itemPJ = {};
    	};

    	$scope.removerCargoPJ = function(){
    		var loop=0;
    		for( loop=0; loop < $scope.selectedCargoPJ.length; loop++){
    			$scope.itensCargoPJ.splice(loop, 1);
    		}
    		$scope.selectedCargoPJ = [];
    	};
    	
    	var isItemExisteCargoPJ = function(itemCargo, itemPJ, itens){
    		var loop=0;
    		for( loop=0; loop < itens.length; loop++){
    			var item = itens[loop];
    			
    			if( (item.cargo.id == itemCargo.id) && (item.juridicaJson.id == itemPJ.id) ){
    				return true;
    			}
    		}
    		return false;
    	};

		$scope.addEvento = function(ev){

			if( isItemExisteEvento($scope.itemEvento, $scope.itensEvento) ){
				MessageService.showAlerta(ev, 'Evento já selecionado.');
				$scope.itemEvento = {};
				return;
			}

			$scope.itemEvento['nome'] = $scope.itemEvento.descricao;

			$scope.itensEvento.push({'evento': $scope.itemEvento});
			$scope.itemEvento = {};
		};

    	$scope.addGrupo = function(ev){
    		
    		if( isItemExiste($scope.itemGrupo, $scope.itensGrupo) ){
    			MessageService.showAlerta(ev, 'Grupo já selecionado.');
    			$scope.itemGrupo = {};
    			return;
    		}
    		
    		$scope.itemGrupo['nome'] = $scope.itemGrupo.descricao;
    		
    		$scope.itensGrupo.push({'grupo': $scope.itemGrupo});
    		$scope.itemGrupo = {};
    	};

		var isItemExisteEvento = function(itemSel, itens){
			var loop=0;
			for( loop=0; loop < itens.length; loop++){
				var item = itens[loop];
				if( item.evento.id == itemSel.id ){
					return true;
				}
			}
			return false;
		};

		var isItemExiste = function(itemSel, itens){
    		var loop=0;
    		for( loop=0; loop < itens.length; loop++){
    			var item = itens[loop];
    			if( item.grupo.id == itemSel.id ){
    				return true;
    			}
    		}
    		return false;
    	};

		$scope.removerEvento = function(){
			var loop=0;
			for( loop=0; loop < $scope.selectedEvento.length; loop++){
				$scope.itensEvento.splice(loop, 1);
			}
			$scope.selectedEvento = [];
		};

    	$scope.removerGrupo = function(){
    		var loop=0;
    		for( loop=0; loop < $scope.selected.length; loop++){
    			$scope.itensGrupo.splice(loop, 1);
    		}
    		$scope.selected = [];
    	};
    	
    	$scope.toggle = function (item, list) {
          var idx = list.indexOf(item);
          if (idx > -1) list.splice(idx, 1);
          else list.push(item);
        };
        
        $scope.exists = function (item, list) {
          return list.indexOf(item) > -1;
        };
        
        $scope.setErroForm = function(){
        	$scope.erroForm = true;
        };
        
        $scope.zerarErroForm = function(){
        	$scope.erroForm = false;
        };


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
                MessageService.showInformacao("Pessoa Física incluído com sucesso.");
                
                criarVars();
                proximoCodigo();
            }
            
            function sucessoAlterar(data){
                MessageService.stopAguarde();
                MessageService.showInformacao("Pessoa Física alterado com sucesso.");
            }

            $scope.bean.enderCidadeId = $scope.itemCidade;
			
			if( $scope.itemProfissao != null ) {
				$scope.bean.profissaoId = $scope.itemProfissao.id;
			}

			if( $scope.itemPartido != null ) {
				$scope.bean.partidoId = $scope.itemPartido.id;
			}
			
            $scope.bean.grupos = $scope.itensGrupo;
			$scope.bean.eventos = $scope.itensEvento;

            $scope.bean.cargosPj = $scope.itensCargoPJ;
            
            if( $scope.dpDtNascimentoTmp != null ){
            	$scope.bean.dpDtNascimento = {data: $scope.dpDtNascimentoTmp};
            }
            
            if( $scope.alteracao ){
            	PessoaFisicaService.alterar($scope.bean, sucessoAlterar, error);
            }else{
            	$scope.bean.codigo = $scope.nroCodigo;
            	PessoaFisicaService.incluir($scope.bean, sucesso, error);
            }
    	};
    
    	var limpar = function(){
    		criarVars();
    	}
    	
    	$scope.init = function(){
    		criarVars();
    		if( $stateParams.id != null ){
        		initEditar($stateParams.id);
        	}else{
        		proximoCodigo();
        	}
    		
		};

		$scope.pdf = function(pos){

			MessageService.showAguarde();

			function error(data){
				MessageService.stopAguarde();
				MessageService.showAlertaError(null, data);
			}

			function sucesso(data){
				MessageService.stopAguarde();

				var file = new Blob([data], {type: 'application/pdf'});
				FileSaver.saveAs(file, 'RelatórioPessoaFísicaFicha.pdf');
			}

			PessoaFisicaService.relatorioFicha($scope.bean.id,  sucesso, error);
		};
    	
    }