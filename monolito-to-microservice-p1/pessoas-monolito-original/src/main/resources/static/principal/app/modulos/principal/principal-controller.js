angular.module('pessoas').controller('PrincipalController',
		PrincipalController);

PrincipalController.$inject = [ '$stateParams', '$scope', '$http', 'MessageService' ];

function PrincipalController($stateParams, $scope, $http,
		MessageService
) {

	$scope.dados = null;

}