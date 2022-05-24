function cargar_formulario(frm){
    $('#formularios').load(frm);
    $('#formularios').fadeIn('slow', function(){
        
    });
}

function salir_formulario(){
    $('#formularios').fadeOut('slow', function(){
        $('#formularios').text('');
    });
}

function cargar_busqueda(frm){
    $('#formularios').fadeOut('slow',function(){
        $('#busquedas').load(frm);
        $('#busquedas').fadeIn('slow', function(){
           $('#texto').focus(); 
           $('#texto').select();
        });
    });   
}

function salir_busqueda(campo){
    $('#busquedas').fadeOut('slow', function(){
        $('#formularios').fadeIn('slow', function(){
            $('#busquedas').text('');
            $(campo).select();
        });
    });
}
