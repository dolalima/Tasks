angular.module(appName).controller("TaskCadastroController", function($scope) {
    c = this;
    
    c.salvar = function(){
        $.post(appApi+"/atividades",c.atividade,function(response){
            console.debug(response);            
        });
    };
    
});


