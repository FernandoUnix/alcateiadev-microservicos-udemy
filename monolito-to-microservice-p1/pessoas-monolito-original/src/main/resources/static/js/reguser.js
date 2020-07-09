var compareTo = function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
};

angular
	.module('homer')
	.directive("compareTo", compareTo);

angular
    .module('homer')
	.controller('reguser', 

	function($scope, $http, sweetAlert, $location, $stateParams) {
		
		$scope.cadastroPermitido = false;
		$scope.user = {};
		$scope.user.chave = $location.search()['chave'];
		
		$scope.chave = $location.search()['chave'];
		
		$http.post('/reguser/validar', $scope.user).success(function(data) {
			
			$scope.userCad = data;
			$scope.userCad.chave = $scope.user.chave;
			$scope.cadastroPermitido = true;
			
		}).error(function(data) {
			console.log("failed");
			
			$scope.cadastroPermitido = false;
			
			sweetAlert.swal({
	            title: "Alerta",
	            text: data.message
			});
			
			
		});
		
		$scope.gravar = function(){
			
			$scope.signup_form.submitted = true;
			
			if ( !$scope.signup_form.$valid  ) {
				
				return null;
	        }
			
	        sweetAlert.swal({
	            title: "Confirmação",
	            text: "Deseja confirmar?",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "Sim",
	            cancelButtonText: "Não",
	            closeOnConfirm: true,
	            closeOnCancel: true },
	        function (isConfirm) {
	            
	            if (isConfirm) {
	            	$scope.gravarOK();
	            } 
	            
	        });
		}
		
		$scope.gravarOK = function(){
			$http.post("/reguser/cadastrar", $scope.userCad).success(function(data) {
				
				console.log("gravacao ok");
				
				sweetAlert.swal({
		            title: "Informação",
		            text: "Cadastro realizado com sucesso. Faça login para entrar."
				},function (isConfirm) {
		            
		            if (isConfirm) {
		            	document.location.href = "/";
		            } 
		            
		        });
				
				$scope.inclusao = false;
				
				
			}).error(function(data) {
				console.log("failed");
				
				sweetAlert.swal({
		            title: "Alerta",
		            text: data.message
				});
				
				
				
			});
		}
	}
);