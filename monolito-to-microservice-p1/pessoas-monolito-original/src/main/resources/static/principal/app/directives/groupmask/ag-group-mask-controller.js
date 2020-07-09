angular.module('diretivas').controller('AgGroupMaskController',
		AgGroupMaskController);

AgGroupMaskController.$inject = [ '$scope', '$log' ];

function AgGroupMaskController($scope, $log) {

	/*
	$scope.passouOnChange = false;
	$scope.classPrincipal = '';
	$scope.valid = function(){
		AgGroupMaskController
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