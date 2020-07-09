angular
    .module('diretivas')
    .directive('agBotaoPermissao', function($compile) {
        return {
            restrict: 'A',
            replace:true,
            link: function(scope, element, attrs, ctrls){

                // attrs["class"] = attrs["class"] + " text-uppercase"
                element.addClass('text-uppercase');

            }
        };
    });