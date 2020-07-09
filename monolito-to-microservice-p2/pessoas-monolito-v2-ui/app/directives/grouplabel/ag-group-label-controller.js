angular.module('diretivas').controller('AgGroupLabelController',
		AgGroupLabelController);

AgGroupLabelController.$inject = [ '$scope', '$log' ];

function AgGroupLabelController($scope, $log) {

	/*
	$scope.passouOnChange = false;
	$scope.classPrincipal = '';
	$scope.valid = function(){
		
		$scope.passouOnChange = true;
		
		if( !$scope.ngObrigatorio ){
			$scope.classPrincipal = '';
			return;
		}
		
		var form = $scope.$parent[$scope.ngForm];
		var campo = form[$scope.ngName];
		
		var verf = angular.toJson(campo.$error) != '{}';
		$scope.classPrincipal = verf ? 'has-error' : 'has-success';
		
    };
    */
	
}