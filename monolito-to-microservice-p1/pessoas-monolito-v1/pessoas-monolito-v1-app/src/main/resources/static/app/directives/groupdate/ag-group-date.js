angular
	.module('diretivas')
	.directive('agGroupDate', function($compile) {
	  return {
		replace: false,
		restrict     : 'EA',
	    templateUrl  : 'app/directives/groupdate/ag-group-date.html',
	    controller   : 'AgGroupDateController',
	    controllerAs : 'agGroupDateController',
	    require: ['^form', 'ngModel'],
	    scope: {
	    	ngLabel: '@',
	    	ngObrigatorio: '=',
	    	ngForm: '=',
	    	ngFor: '@',
	    	ngName: '@',
	    	ngValor: '='
	      },
	      link: function(scope, element, attrs, ctrls){
		    	
		        var campo = element.find("input")[0];
		        campo.name=scope.ngName;
		        
		        $compile(campo)(scope);
		        
		        scope.form = ctrls[0];
		        scope.campo = scope.form[scope.ngName];
		        
		        var ngModel = ctrls[1];
		        
		        scope.$watch('ngValor', function() {
		        	scope.nome = scope.ngValor;
		        });
		        
		        scope.$watch('nome', function() {
		            ngModel.$setViewValue(scope.nome);
		        });
		        
		    	scope.passouOnChange = false;
		    	scope.classPrincipal = '';
		    	scope.validarClass = function(){
		    		
		    		if( !scope.ngObrigatorio ){
		    			return 'form-group';
		    		}
		    		
		    		var valor = scope.campo.$viewValue;
		    		if( valor == null || valor == undefined){
		    			valor = "";
		    		}else if( valor.toString() == "NaN"){
		    			valor = "";
		    		}
		    		
		    		if( !scope.passouOnChange && (valor == "" && !scope.campo.$error.date) ) 
		    			return 'form-group';
	    			else if( scope.campo.$invalid ) {
	    				return 'form-group has-error'
	    			}
					else 
						return ' form-group ';//has-success
		    	};
		    	
		    	scope.validarClassBasic = function(){
		    		
		    		if( !scope.ngObrigatorio ){
		    			return 'form-group';
		    		}
		    		
		    		var valor = scope.campo.$viewValue;
		    		if( valor == null || valor == undefined){
		    			valor = "";
		    		}else if( valor.toString() == "NaN"){
		    			valor = "";
		    		}
		    		
	    			if( scope.campo.$invalid ){
	    				scope.$parent.setErroForm();
	    				scope.passouOnChange = true;
	    				return 'form-group has-error' 
	    			}else 
						return ' form-group ';//has-success
		    	};
		    	
		    	 scope.$on('formClickValidation', function(event, data) { 
			    	  scope.validarClassBasic(); 
		    	  });
		    	 
		    	scope.valid = function(){
		    		
		    		scope.passouOnChange = true;
		    		
		        };
		        
		        scope.today = function() {
				    scope.dt = new Date();
				};
				  
				scope.today();
				
				scope.clear = function() {
				    scope.dt = null;
				};
				
				scope.inlineOptions = {
				    customClass: getDayClass,
				    minDate: new Date(),
				    showWeeks: true
				};
				
				var popup1 = {
				    opened: false
				};
				  
				scope.popup1 = popup1;
				
				scope.popup2 = {
				    opened: false
				};
				
						  
				scope.dateOptions = { popup1 };
				
				  // Disable weekend selection
				  function disabled(data) {
				    var date = data.date,
				      mode = data.mode;
				    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
				  }
				
				scope.toggleMin = function() {
				    scope.inlineOptions.minDate = scope.inlineOptions.minDate ? null : new Date();
				    scope.dateOptions.minDate = scope.inlineOptions.minDate;
				};
				
				scope.toggleMin();
				
				scope.open1 = function() {
				    scope.popup1.opened = true;
				};
				
				  scope.open2 = function() {
				    scope.popup2.opened = true;
				  };
				
				  scope.setDate = function(year, month, day) {
				    scope.dt = new Date(year, month, day);
				  };
				
				  scope.formats = ['dd/MM/yyyy', 'shortDate'];
				  scope.format = scope.formats[0];
				  
				
				
				  var tomorrow = new Date();
				  tomorrow.setDate(tomorrow.getDate() + 1);
				
				  var afterTomorrow = new Date();
				  afterTomorrow.setDate(tomorrow.getDate() + 1);
				  
				  scope.events = [
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
				
						  for (var i = 0; i < scope.events.length; i++) {
						    var currentDay = new Date(scope.events[i].date).setHours(0,0,0,0);
						
						    if (dayToCheck === currentDay) {
						      return scope.events[i].status;
						    }
						  }
				}
				
				return '';
				  }
				  
		    }
	  };
	});