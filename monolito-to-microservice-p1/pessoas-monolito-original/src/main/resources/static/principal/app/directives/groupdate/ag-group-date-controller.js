angular.module('diretivas').controller('AgGroupDateController',
		AgGroupDateController);

AgGroupDateController.$inject = [ '$scope', '$log' ];

function AgGroupDateController($scope, $log) {

	$scope.altInputFormats = ['M!/d!/yyyy'];
	
	/*
	$scope.passouOnChange = false;
	$scope.classPrincipal = '';
	$scope.valid = function(){
		
		$scope.passouOnChange = true;
		
		if( !$scope.ngObrigatorio ){
			$scope.classPrincipal = '';
			return;
		}
		
		var item = $scope.ngForm[$scope.ngName];
		if( item == null || item == undefined){
			 $scope.ngForm.$setValidity($scope.ngName, true);
		}
		
		var verf = angular.toJson($scope.ngForm[$scope.ngName].$error) != '{}';
		$scope.classPrincipal = verf ? 'has-error' : 'has-success';
		
    };
	
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  
		  $scope.today();
		
		  $scope.clear = function() {
		    $scope.dt = null;
		  };
		
		  $scope.inlineOptions = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: true
		  };
		
		  var popup1 = {
		    opened: false
		  };
		  
		  $scope.popup1 = popup1;
		
		  $scope.popup2 = {
		    opened: false
		  };
		
				  
		  $scope.dateOptions = { popup1 };
		
		  // Disable weekend selection
		  function disabled(data) {
		    var date = data.date,
		      mode = data.mode;
		    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
		  }
		
		  $scope.toggleMin = function() {
		    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
		    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
		  };
		
		  $scope.toggleMin();
		
		  $scope.open1 = function() {
		    $scope.popup1.opened = true;
		  };
		
		  $scope.open2 = function() {
		    $scope.popup2.opened = true;
		  };
		
		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };
		
		  $scope.formats = ['dd/MM/yyyy', 'shortDate'];
		  $scope.format = $scope.formats[0];
		  $scope.altInputFormats = ['M!/d!/yyyy'];
		
		
		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		  afterTomorrow.setDate(tomorrow.getDate() + 1);
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		},
		{
		  date: afterTomorrow,
		  status: 'partially'
		    }
		  ];
		
		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		  var dayToCheck = new Date(date).setHours(0,0,0,0);
		
		  for (var i = 0; i < $scope.events.length; i++) {
		    var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);
		
		    if (dayToCheck === currentDay) {
		      return $scope.events[i].status;
		    }
		  }
		}
		
		return '';
		  }
		  */
}