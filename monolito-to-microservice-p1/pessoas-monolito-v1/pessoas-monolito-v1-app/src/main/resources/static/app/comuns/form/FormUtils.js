angular
    .module('communs')
    .service('FormUtils', FormUtils);


FormUtils.$inject = [
];

function FormUtils(
) {
	
	this.validarCampo = function(scope, name, ){
		
		$scope.passouOnChange = true;
		
		if( !$scope.ngObrigatorio ){
			$scope.classPrincipal = '';
			return;
		}
		
		var verf = angular.toJson($scope.ngForm[$scope.ngName].$error) != '{}';
		$scope.classPrincipal = verf ? 'has-error' : 'has-success';
		
    };

}