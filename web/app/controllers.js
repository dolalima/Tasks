angular.module(appName).controller("TaskCadastroController", function ($scope) {
    c = this;

    c.salvar = function () {
        $.post(appApi + "/atividades", c.atividade, function (response) {
            console.debug(response);
        });
    };

});

angular.module(appName).controller("TaskListController", function ($scope) {
    c = this;
    
    $.get(appApi+"atividades",function(response){
      if(response.erro===false){
          c.lista = response.entidades;
      }else{
          c.lista=[];
      }  
    },"json");
    
    c.excluir = function(id){
                
    };

    c.lista = [
        {id:1,title: "Aviversario Dola", content: "lista de Presente", data: "24/03/1985"},
        {id:2,title: "Aniversario Luanna", content: "Aluguel do local", data: "29/05/1986"}];



});


