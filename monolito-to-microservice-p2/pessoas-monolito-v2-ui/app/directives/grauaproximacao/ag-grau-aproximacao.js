angular
	.module('diretivas')
	.directive('agGrauAproximacao', function($compile) {
	  return {
		restrict     : 'EA',
	    templateUrl  : 'app/directives/grauaproximacao/ag-grau-aproximacao.html',
	    controller   : 'AgGrauAproximacaoController',
	    controllerAs : 'agGrauAproximacaoController',
	    require: ['^form', 'ngModel'],
	    scope: {
	    	ngForm: '=',
	    	ngObrigatorio: '=',
	        ngName: '@',
	        ngError: '=',
	        ngModelInicio: '=',
			ngOcultarLabel: '='
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

		        scope.ngOcultarLabel = scope.ngOcultarLabel == null ? false : scope.ngOcultarLabel;
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
						return ' form-group ';
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
		        };
		        
	      }
	  };
	});