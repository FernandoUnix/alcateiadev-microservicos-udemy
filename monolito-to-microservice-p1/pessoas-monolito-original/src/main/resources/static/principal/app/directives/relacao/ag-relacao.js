angular
	.module('diretivas')
	.directive('agRelacao', function($compile) {
	  return {
		restrict     : 'EA',
	    templateUrl  : 'app/directives/relacao/ag-relacao.html',
	    controller   : 'AgRelacaoController',
	    controllerAs : 'agRelacaoController',
	    require: ['^form', 'ngModel'],
	    scope: {
	    	ngForm: '=',
	    	ngObrigatorio: '=',
	        ngName: '@',
	        ngError: '=',
            ngTitle: '@',
	        ngModelInicio: '='
	      },
	      
	      link: function(scope, element, attrs, ctrls){
		    	
		        var campo = element.find("select")[0];
		        campo.name=scope.ngName;
		        
		        $compile(campo)(scope);
		        
		        scope.form = ctrls[0];
		        scope.campo = scope.form[scope.ngName];
		        
		        var ngModel = ctrls[1];
		        
		        scope.$watch('nome', function() {
		            ngModel.$setViewValue(scope.nome);
		        });
		        /*
		        scope.$watch('ngModelInicio', function() {
		        	ngModel.$setViewValue(scope.ngModelInicio);
		        });
		        */
		        
		    	scope.passouOnChange = false;
		    	scope.classPrincipal = '';
		    	scope.validarClass = function(){
		    		
		    		if( !scope.ngObrigatorio ){
		    			return 'form-group';
		    		}
		    		
		    		if( !scope.passouOnChange ) 
		    			return 'form-group';
	    			else if( scope.campo.$invalid ) 
	    				return 'form-group has-error';
					else 
						return ' form-group ';//has-success
		    	};
		    	
		    	scope.validarClassBasic = function(){
		    		
		    		if( !scope.ngObrigatorio ){
		    			return 'form-group';
		    		}
		    		
	    			if( scope.campo.$invalid ){
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
		    		/*
		    		if( !scope.ngObrigatorio ){
		    			scope.classPrincipal = '';
		    			return;
		    		}
		    		
		    		var campo = scope.form[scope.ngName];
		    		
		    		var verf = angular.toJson(campo.$error) != '{}';
		    		scope.classPrincipal = verf ? 'has-error' : 'has-success';
		    		*/
		    		
		        };
		        
	      }
		        
		        
		        
		        
		        
		        
	  };
	});