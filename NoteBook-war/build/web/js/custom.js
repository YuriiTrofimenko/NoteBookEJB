$(document).ready(function (){
    
    /* Сразу после загрузки страницы получаем данные о видах состояний заказа
     * и выводим в виде таблицы 
     * */
    //var $container = $(this).find("#container");
    var $container = $("#container");
    $.ajax({
        type: 'POST',
        url: 'Api?action=get_states',
        //contentType: 'text/plain',
        cache: false
    }).done(function(responseText, textStatus, jqXHR) {
        
        if(responseText !== ''){
            
            var template = Hogan.compile(
                '<table class="table">'
                +  '<thead>'
                +    '<tr>'
                +      '<th>ID</th>'
                +      '<th>Раздел</th>'
                +    '</tr>'
                +  '</thead>'
                +  '<tbody>'
                + 		'{{#states}}'
                + 			'<tr>'
                +   			'<th scope="row">{{id}}</th>'
                +   			'<td>{{name}}</td>'
                + 			'</tr>'
                + 		'{{/states}}'
                +   '</tbody>'
                +'</table>'
            );
            //
            $container.html(template.render(responseText));
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {

        alert("Произошла ошибка. Обратитесь к разработчику сайта");
    });
    
    /*
     * Обработчик клика по кнопке добавления заказа
     * */
    $("form input[type=submit]").click(function(event){
        
        event.preventDefault();
        $.ajax({
                type: 'POST',
                url: 'Api',
                data: $('form').serialize(),
                //contentType: 'text/plain',
                cache: false
            }).done(function(responseText, textStatus, jqXHR) {

                alert(responseText);
            }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("Произошла ошибка. Обратитесь к разработчику сайта");
        });
    });
});


