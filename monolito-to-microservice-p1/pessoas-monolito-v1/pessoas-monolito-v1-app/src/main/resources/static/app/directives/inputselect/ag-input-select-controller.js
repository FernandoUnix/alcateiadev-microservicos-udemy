angular.module('diretivas').controller('AgInputSelectController',
		AgInputSelectController);

AgInputSelectController.$inject = [ '$mdDialog', '$scope', '$log', 'MessageService',
		'AgInputSelectProfissao', 'AgInputSelectAreaAtuacao', 'AgInputSelectGrupo', 
		'AgInputSelectCargo', 'AgInputSelectPJ', 'AgInputSelectPartido', 'AgInputSelectSituacaoSolicitacao',
		'AgInputSelectExpediente', 'AgInputSelectPF', 'AgInputSelectTipoSolicitacao', 'AgInputSelectResponsavel',
		'AgInputSelectUsuario', 'AgInputSelectTemaSolicitacao'];

function AgInputSelectController($mdDialog, $scope, $log, MessageService,
		AgInputSelectProfissao, 
		AgInputSelectAreaAtuacao,
		AgInputSelectGrupo,
		AgInputSelectCargo,
		AgInputSelectPJ,
		AgInputSelectPartido,
		AgInputSelectSituacaoSolicitacao,
		AgInputSelectExpediente,
		AgInputSelectPF,
		AgInputSelectTipoSolicitacao,
		AgInputSelectResponsavel,
	    AgInputSelectUsuario,
        AgInputSelectTemaSolicitacao
) {

	var limparCampos = function() {
		$scope.ngItem.id = null;
		$scope.ngItem.codigo = "";
		$scope.ngItem.descricao = "";
        $scope.ngItem['extra']  = "";
        $scope.ngItem['enderTelefonePrincipal']  = "";
        $scope.ngItem['email']  = "";
	};

	$scope.desabilitar = false;

	$scope.codigoChange = false;
	$scope.carregarCodigoChange = function() {
		$scope.codigoChange = true;
	};
	
	$scope.carregarCodigo = function() {

		if( !$scope.codigoChange) {
			return;
		}
		
		$scope.codigoChange = false;
		
		if ($scope.ngItem.codigo == null || $scope.ngItem.codigo == undefined
				|| $scope.ngItem.codigo == "") {
			$scope.desabilitar = false;
			limparCampos();
			return;
		}
		
		$scope.ngItem.descricao = ""; 
		
		consultar();
	};

	$scope.descricaoChange = false;
	$scope.carregarDescricaoChange = function() {
		$scope.descricaoChange = true;
	};
	
	$scope.carregarDescricao = function() {

		if( !$scope.descricaoChange) {
			return;
		}
		
		$scope.descricaoChange = false;
		
		if ($scope.ngItem.descricao == null
				|| $scope.ngItem.descricao == undefined
				|| $scope.ngItem.descricao == "") {
			$scope.desabilitar = false;
			limparCampos();
			return;
		}

		$scope.ngItem.codigo = "";
		
		consultar();
	};

	var consultar = function() {
		
		$scope.desabilitar = true;

		if( $scope.ngTipo == "profissao" ){
			AgInputSelectProfissao.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "areaatuacao" ){
			AgInputSelectAreaAtuacao.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "grupo" ){
			AgInputSelectGrupo.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "cargo" ){
			AgInputSelectCargo.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "pj" ){
			AgInputSelectPJ.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

		if( $scope.ngTipo == "partido" ){
			AgInputSelectPartido.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "situacaosolicitacao" ){
			AgInputSelectSituacaoSolicitacao.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "expediente" ){
			AgInputSelectExpediente.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "pf" || $scope.ngTipo == "pf_extra" || $scope.ngTipo == "pf_tel_email"){
			AgInputSelectPF.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

        if( $scope.ngTipo == "temasolicitacao" ){
            AgInputSelectTemaSolicitacao.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
                $scope.ngIgnorar, sucesso, error);
        }

		if( $scope.ngTipo == "tiposolicitacao" ){
			AgInputSelectTipoSolicitacao.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( $scope.ngTipo == "responsavel" ){
			AgInputSelectResponsavel.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

        if( $scope.ngTipo == "usuario" ){
            AgInputSelectUsuario.consultar($scope.ngItem.codigo, 0, $scope.ngItem.descricao,
                $scope.ngIgnorar, sucesso, error);
        }

	};

	var error = function(data) {
		MessageService.stopAguarde();
		limparCampos();
		$scope.desabilitar = false;
		MessageService.showAlerta(null, "Nenhum item encontrado.");
	};

	var sucesso = function(data) {
		MessageService.stopAguarde();

		if( data.registros.length > 1 ){
			loadJanelaSelecionar(data.registros);
			return;
		}
		
		var listaResultado = data.registros[0];
		sucessoUnico(listaResultado, false);
	};
	
	var sucessoUnico = function(listaResultado, semMensagem) {
		
		if (listaResultado != null) {
			
			if( $scope.ngItem == null ){
				$scope.ngItem = {};
			}
			$scope.ngItem.id = listaResultado.id;
			$scope.ngItem.codigo = listaResultado.codigo;
			$scope.ngItem.descricao = listaResultado.nome;
            $scope.ngItem.extra = listaResultado["extra"];
            $scope.ngItem.enderTelefonePrincipal = listaResultado["enderTelefonePrincipal"];
            $scope.ngItem.email = listaResultado["email"];

			$scope.desabilitar = false;
		} else {
			limparCampos();
			$scope.desabilitar = false;
			
			if( !semMensagem ){
				MessageService.showAlerta(null, "Nenhum item encontrado.");
			}
		}		
	};
	
	$scope.clickJanela = function(ev){
		$scope.showTabDialog(ev, null);	
	};
	
	$scope.showTabDialog = function(ev, lista) {
	    
		$mdDialog.show({
	      controller: AgInputSelectJanelaController,
	      templateUrl: 'app/directives/inputselect/ag-input-select-janela.html',
	      parent: angular.element(document.body),
	      targetEvent: ev,
	      clickOutsideToClose : true,
	      locals: {
	    	  lista: lista,
	    	  item: $scope.ngItem,
	    	  tipo: $scope.ngTipo,
	    	  ignorar: $scope.ngIgnorar
	      }
	    })
        .then(function(item) {
        	sucessoUnico(item, false);
        }, function() {
        	sucessoUnico(null, true);
        });
		
	};
	
	if( $scope.ngItem == null ){
		
		$scope.ngItem = {
				id: null,
				codigo: "",
				descricao: ""
		}
		
	}

	var loadJanelaSelecionar = function(lista){
		$scope.showTabDialog(null, lista);	
	}
}




