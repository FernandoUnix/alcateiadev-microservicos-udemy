
angular
    .module('pessoaFisica')
    .controller('PessoaFisicaReportController', PessoaFisicaReportController );


	PessoaFisicaReportController.$inject = [
	    '$location',
        '$scope',
        '$state',
        'MessageService',
        'PessoaFisicaService',
        'FileSaver'
    ];

    function PessoaFisicaReportController(
    								$location,
                                    $scope,
                                    $state,
                                    MessageService,
                                    PessoaFisicaService,
                                    FileSaver
    ){

    	$scope.titulo = $state.current.data.pageTitle;

		$scope.btBloq = false;
		$scope.dadosGerais = {};
		$scope.dadosConsulta = [];

		$scope.tpFiltro = "T";

    	$scope.optRelatorios = [
    	                        {id:'RELATORIO', label: 'RELATÓRIO'},
    	                        {id:'ETIQUETA', label: 'ETIQUETA'},
    	                        {id:'DUPLICIDADE', label: 'DUPLICIDADE'}
    	                       ];
    	
    	$scope.optOrdenadoPor = [
    	                        {id:'ID', label: 'ID'},
    	                        {id:'NOME', label: 'NOME'},
    	                        {id:'ENDERECO', label: 'ENDEREÇO'},
    	                        {id:'CIDADE', label: 'CIDADE'}
    	                       ];	
    	
    	$scope.optOrdenadoEm = [
     	                        {id:'CRESCENTE', label: 'CRESCENTE'},
     	                        {id:'DECRESCENTE', label: 'DECRESCENTE'}
     	                       ];
    	
    	$scope.optSexo = [
     	                        {id:'MASCULINO', label: 'MASCULINO'},
     	                        {id:'FEMININO', label: 'FEMININO'},
     	                       {id:'DESCONHECIDO', label: 'DESCONHECIDO'},
     	                       ];
    	
    	$scope.filtro = {
    			tipoRelatorio: '',
    			ordenadoPor: '',
    			ordenadoEm: '',

    			nome: '',
    			sexo: '',
    			estado: null,
    			regional: null,

    			grupo: null,
    			cargo: null,
    			partido: null
    	};

    	$scope.itemTipoRelatorio = null;
    	$scope.itemOrdenadorPor = null;
    	$scope.itemOrdenadorEm = null;
    	$scope.itemSexo = null;

		$scope.itemRegionalCidade = null;
		$scope.itemRegionalEstado = null;

    	$scope.itemCidade = null;
    	$scope.itemEstado = null;
    	$scope.itemGrupo = null;
    	$scope.itemCargo = null;
    	$scope.itemPartido = null;
		$scope.itemProfissao = null;

		$scope.btBloqVisu = false;

		$scope.tpFiltroController = function(){
			$scope.btBloqVisu = $scope.tpFiltro == "T";
		};


		$scope.emails = function(pos){

			MessageService.showAguarde();

			function error(data){
				MessageService.stopAguarde();
				MessageService.showAlertaError(null, data);
			}

			function sucesso(data){
				MessageService.stopAguarde();

				var file = new Blob([data], {type: 'application/text'});
				FileSaver.saveAs(file, 'Emails.txt');
			}

			this.montarFiltro();

			PessoaFisicaService.relatorioTxt($scope.filtro,  sucesso, error);
		};

		$scope.tipChange = function() {
			$scope.dadosConsulta = [];
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
            	 FileSaver.saveAs(file, 'RelatórioPessoaFísica.pdf');
            }

			this.montarFiltro();

            PessoaFisicaService.relatorio($scope.filtro,  sucesso, error);
    	};

		$scope.consultar = function(pos){
			MessageService.showAguarde();

			function error(data){
				MessageService.stopAguarde();
				MessageService.showAlertaError(null, data);
				$scope.dadosConsulta = [];
			}

			function sucesso(data){
				MessageService.stopAguarde();
				$scope.dadosGerais = data;
				$scope.dadosConsulta = data.lista;
			}

			var verf = $scope.montarFiltro();
			if( !verf ){
				return;
			}

			PessoaFisicaService.relatorioConsulta($scope.filtro,  sucesso, error);
		};

		$scope.bloquearBotoes = function(){

			$scope.btBloq = $scope.itemTipoRelatorio.id == 'DUPLICIDADE';
		};

		$scope.montarFiltro = function(){
			$scope.filtro.tipoRelatorio = $scope.itemTipoRelatorio == null ? null : $scope.itemTipoRelatorio.id;
			$scope.filtro.ordenadoPor   = $scope.itemOrdenadorPor == null ? null : $scope.itemOrdenadorPor.id;
			$scope.filtro.ordenadoEm    = $scope.itemOrdenadorEm == null ? null : $scope.itemOrdenadorEm.id;
			$scope.filtro.sexo     = $scope.itemSexo == null ? null : $scope.itemSexo.id;
			$scope.filtro.grupo    = $scope.itemGrupo == null ? null : $scope.itemGrupo.id;
			$scope.filtro.cargo    = $scope.itemCargo == null ? null : $scope.itemCargo.id;
			$scope.filtro.partido  = $scope.itemPartido == null ? null : $scope.itemPartido.id;
			$scope.filtro.estado   = $scope.itemEstado == null ? null : $scope.itemEstado;
			$scope.filtro.cidade    = $scope.itemCidade == null ? null : $scope.itemCidade;
			$scope.filtro.profissao = $scope.itemProfissao == null ? null : $scope.itemProfissao.id;

			$scope.filtro.regionalEstado = $scope.itemRegionalEstado == null ? null : $scope.itemRegionalEstado;
			$scope.filtro.regional       = $scope.itemRegionalCidade == null ? null : $scope.itemRegionalCidade;
            $scope.filtro.semGrupos = $scope.semGrupos == null ? null : $scope.semGrupos;

			var verf = 	($scope.filtro.nome == null ||$scope.filtro.nome=="") &&
				$scope.filtro.sexo == null &&
				$scope.filtro.grupo == null &&
				$scope.filtro.cargo     == null &&
				$scope.filtro.partido   == null &&
				$scope.filtro.estado    == null &&
				$scope.filtro.cidade    == null &&
				$scope.filtro.profissao == null &&
				$scope.filtro.regionalEstado  == null &&
				$scope.filtro.regional  == null;

			if($scope.tpFiltro == "F" && verf ){
				MessageService.stopAguarde();
				MessageService.showAlerta(null, "É obrigatório o preenchimento de pelo menos 1 campo no filtro.");
				return false;
			}
			return true;
		};

		$scope.tpFiltroController();

    	
    }