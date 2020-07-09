angular
	.module('diretivas')
	.directive('agGroupDateIdade', function($compile) {
	  return {
		replace: false,
		restrict     : 'EA',
	    templateUrl  : 'app/directives/groupdate-idade/ag-group-date-idade.html',
	    controller   : 'AgGroupDateIdadeController',
	    controllerAs : 'agGroupDateIdadeController',
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
		    			return 'form-group' 
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
		        

				  
		    }
	  };
	});