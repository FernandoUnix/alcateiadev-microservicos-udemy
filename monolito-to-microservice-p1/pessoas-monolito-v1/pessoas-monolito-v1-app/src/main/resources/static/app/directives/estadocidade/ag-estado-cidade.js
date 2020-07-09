angular
	.module('diretivas')
	.directive('agEstadoCidade', function($compile) {
	  return {
		restrict     : 'EA',
		replace      : false,
	    templateUrl  : 'app/directives/estadocidade/ag-estado-cidade.html',
	    controller   : 'AgEstadoCidadeController',
	    controllerAs : 'agEstadoCidadeController',
	    require: ['^form'],
	    scope: {
	    	ngLabel: '@',
	    	ngForm: '=',
	        ngModelEstado: '=',
	        ngModelCidade: '=',
	        ngRequired: '=',
        	ngName: '@',
        	ngSave: '=',
    		ngRegional: '=',
			ngDisabled: '=',
			ngAddObjeto: '='
	      },
	      link: function(scope, element, attrs, ctrls){
	    	  
	    	  scope.isPassou=false;

	    	  
	    	  var sel1 = element.find("select")[0];
	    	  var sel2 = element.find("select")[1];
	    	  
	    	  scope.nomeEstado = scope.ngName+"_estado";
	    	  scope.nomeCidade = scope.ngName+"_cidade";
	    	  
		      sel1.name = scope.nomeEstado;
		      sel2.name = scope.nomeCidade;
		      
		      $compile(sel1)(scope);
		      $compile(sel2)(scope);
		      
		      scope.form = ctrls[0];
		      scope.sel1 = scope.form[scope.ngName+"_estado"];
		      scope.sel2 = scope.form[scope.ngName+"_cidade"];
		        
		      scope.passouOnChange = false;
		      scope.classPrincipal = '';
		      
		      scope.validarClass = function(){
		    		
		    		if( !scope.ngRequired ){
		    			return 'form-group';
		    		}
		    		
		    		if( !scope.passouOnChange ) 
		    			return 'form-group' 
	    			else if( scope.sel1.$invalid || scope.sel2.$invalid ) 
	    				return 'form-group has-error' 
					else 
						return ' form-group ';//has-success
		    	};
		    	
		    	scope.valid = function(){
		    		scope.passouOnChange = true;
		        };
		      
		        scope.validarClassBasic = function(){
		    		
		    		if( !scope.ngRequired ){
		    			return 'form-group';
		    		}
		    		
	    			else if( scope.sel1.$invalid || scope.sel2.$invalid ){
	    				scope.passouOnChange = true;
	    				return 'form-group has-error' 
	    			}else 
						return ' form-group ';//has-success
		    	};
		    	
		      scope.$on('formClickValidation', function(event, data) { 
		    	  scope.validarClassBasic(); 
	    	  });
		      
			      
	      }
	  };
	});