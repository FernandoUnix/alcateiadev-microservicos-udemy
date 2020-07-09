angular.module('diretivas').controller('AgGroupDateIdadeController',
		AgGroupDateIdadeController);

AgGroupDateIdadeController.$inject = [ '$scope', '$log' ];

function AgGroupDateIdadeController($scope, $log) {

	$scope.altInputFormats = ['M!/d!/yyyy'];

}