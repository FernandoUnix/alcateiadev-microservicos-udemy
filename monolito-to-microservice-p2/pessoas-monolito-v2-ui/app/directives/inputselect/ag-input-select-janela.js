angular.module('diretivas').controller('AgInputSelectJanelaController',
		AgInputSelectJanelaController);

AgInputSelectJanelaController.$inject = [ '$mdDialog', '$scope', 'MessageService', 
                                          'AgInputSelectProfissao', 
                                          'AgInputSelectAreaAtuacao',
                                          'lista', 'item', 'tipo', 'ignorar',
                                          'AgInputSelectGrupo',
                                          'AgInputSelectCargo',
                                          'AgInputSelectPJ',
                                          'AgInputSelectPartido',
                                          'AgInputSelectSituacaoSolicitacao',
                                          'AgInputSelectExpediente',
                                          'AgInputSelectPF',
                                          'AgInputSelectTipoSolicitacao',
                                          'AgInputSelectResponsavel',
										  'AgInputSelectUsuario',
										  'AgInputSelectTemaSolicitacao',
										  'AgInputSelectEvento'
                                          ];

function AgInputSelectJanelaController($mdDialog, $scope, MessageService, 
		AgInputSelectProfissao, 
		AgInputSelectAreaAtuacao,
		lista, item, tipo, ignorar,
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
        AgInputSelectTemaSolicitacao,
	    AgInputSelectEvento
){

	$scope.grid = {
			colunas:[
			         {label:'Código', campo:'codigo', width: '10%'},
			         {label:'Nome',campo:'nome', width: '65%'}
			],
			paginacao:[]
	};

	$scope.filtro = item;
	$scope.ngIgnorar = ignorar;
	
	if( $scope.filtro == null ){
		$scope.filtro = {
					codigo: '',
					descricao: ''
				};
	}
	
	var definirTitulo = function(){
		if( tipo == "profissao" ){
			$scope.titulo = "Profissões";
		}
		
		if( tipo == "areaatuacao" ){
			$scope.titulo = "Área de Atuação";
		}
		
		if( tipo == "grupo" ){
			$scope.titulo = "Grupos";
		}
		
		if( tipo == "cargo" ){
			$scope.titulo = "Cargos";
		}
		
		if( tipo == "pj" ){
			$scope.titulo = "Pessoa Jurídica";
		}
		
		if( tipo == "partido" ){
			$scope.titulo = "Partido";
		}
		
		if( tipo == "situacaosolicitacao" ){
			$scope.titulo = "Situação de Solicitação";
		}
		
		if( tipo == "expediente" ){
			$scope.titulo = "Expediente";
		}

		if( tipo == "pf" || tipo == "pf_extra"|| tipo == "pf_tel_email"){
			$scope.titulo = "Pessoa Física";
		}
		
		if( tipo == "tiposolicitacao" ){
			$scope.titulo = "Tipo de Solicitação";
		}

        if( tipo == "temasolicitacao" ){
            $scope.titulo = "Tema de Solicitação";
        }
		
		if( tipo == "responsavel" ){
			$scope.titulo = "Responsável";
		}

        if( tipo == "usuario" ){
            $scope.titulo = "Usuário";
        }

		if( tipo == "evento" ){
			$scope.titulo = "Evento";
		}
	};

	var error = function(data) {
		MessageService.showAlerta(null, "Nenhum item encontrado.");
	};

	var sucesso = function(data) {
		$scope.grid.paginacao = data;
	};
	
	$scope.selecionar = function(item){
		$mdDialog.hide(item);
	};
	
	$scope.cancelar = function() {
	    $mdDialog.hide();
    };
	  
	$scope.consultar = function(item) {
		
		if( tipo == "profissao" ){
			AgInputSelectProfissao.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "areaatuacao" ){
			AgInputSelectAreaAtuacao.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "grupo" ){
			AgInputSelectGrupo.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

		if( tipo == "evento" ){
			AgInputSelectEvento.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
				$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "cargo" ){
			AgInputSelectCargo.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "pj" ){
			AgInputSelectPJ.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "partido" ){
			AgInputSelectPartido.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "situacaosolicitacao" ){
			AgInputSelectSituacaoSolicitacao.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "expediente" ){
			AgInputSelectExpediente.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "pf" || tipo == "pf_extra" || tipo == "pf_tel_email"){
			AgInputSelectPF.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}
		
		if( tipo == "tiposolicitacao" ){
			AgInputSelectTipoSolicitacao.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

        if( tipo == "temasolicitacao" ){
            AgInputSelectTemaSolicitacao.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
                $scope.ngIgnorar, sucesso, error);
        }


		if( tipo == "responsavel" ){
			AgInputSelectResponsavel.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
					$scope.ngIgnorar, sucesso, error);
		}

        if( tipo == "usuario" ){
            AgInputSelectUsuario.consultar($scope.filtro.codigo, item, $scope.filtro.descricao,
                $scope.ngIgnorar, sucesso, error);
        }

	};

	definirTitulo();
	$scope.consultar();
}