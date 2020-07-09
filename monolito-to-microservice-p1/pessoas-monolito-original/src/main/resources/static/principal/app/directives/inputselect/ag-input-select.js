angular
	.module('diretivas')
	.directive('agInputSelect', function($compile) {
	  return {
		restrict     : 'EA',
		replace      : false,
	    templateUrl  : 'app/directives/inputselect/ag-input-select.html',
	    controller   : 'AgInputSelectController',
	    controllerAs : 'agInputSelectController',
	    require: ['^form'],
	    scope: {
	    	ngItem: '=',
	    	ngObrigatorio: '=',
	    	ngIgnorar: '=',
	    	ngTipo: '@',
	    	ngLabel: '@',
    		ngName: '@',
    		ngLabelItem: '@'
	      },
	      link: function(scope, element, attrs, ctrls){
	    	  
	    	  var campo = element.find("input")[0];
		      campo.name=scope.ngName;
		      
		      $compile(campo)(scope);
		      
		      scope.form = ctrls[0];
		      scope.campo = scope.form[scope.ngName];
		        
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
	    				scope.$parent.setErroForm();
	    				scope.passouOnChange = true;
	    				return 'form-group has-error' 
		       		}else 
						return ' form-group ';//has-success
		       };
		       
		    	
		    	scope.valid = function(){
		    		scope.passouOnChange = true;
		        };
		      
		        scope.$on('formClickValidation', function(event, data) { 
			    	  scope.validarClassBasic(); 
		    	  });
	      }
	  };
	});