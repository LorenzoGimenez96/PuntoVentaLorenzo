var pagina = 1;

function ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete) {
    eval(pBeforeSend);
    $.ajax({
        type: 'POST',
        url: pUrl,
        data: pDatosFormulario,
        dataType: 'json'
    }).done(function (json) {
        eval(pSuccess);
    }).fail(function (e) {
        eval(pError);
    }).always(function (objeto, exito, error) {
        eval(pComplete);
    });
}

function ajax_error() {
    alert("ERROR: No se pudo conectar con el Servidor");
}

function siguiente_campo(actual, siguiente, preventDefault) {
    $(actual).on('keydown', function (event) {
        //console.log("---> "+event.which);
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}

function mensaje(mensaje, textoBoton, funcion) {
    $('body').append('<div id="mensajes"></div>');
    modal = '<div id="divModal" class="modal fade" tabindex="-1" role="dialog" ';
    modal += '     aria-labelledby="gridSystemModalLabel">';
    modal += '<div class="modal-dialog" role="document">';
    modal += '<div class="modal-content">';
    modal += '  <div class="modal-header">';
    modal += '    <h5 class="modal-title" id="gridSystemModalLabel">Mensaje del Sistema</h5>';
    modal += '    <button type="button" class="close" data-dismiss="modal" ';
    modal += '            aria-label="Close"><span aria-hidden="true">&times;</span></button>';
    modal += '  </div>';
    modal += '  <div class="modal-body">';
    modal += '     ' + mensaje;
    modal += '  </div>';
    modal += '  <div class="modal-footer">';
    modal += '    <button id="botonAceptar" type="button" class="btn btn-primary"';
    modal += '            data-dismiss="modal">' + textoBoton + '</button>';
    modal += '  </div>';
    modal += '</div>';
    modal += '</div>';
    modal += '</div>';
    $('#mensajes').html(modal);
    $('#divModal').modal('show');
    $('#divModal').on('shown.bs.modal', function (e) {
        $('#botonAceptar').focus();
    });
    $('#divModal').on('hidden.bs.modal', function (e) {
        eval(funcion);
        $('#mensajes').remove();
    });
}

function confirmar(mensaje, textoBoton, pFuncion) {
    $('body').append('<div id="mensajes"></div>');
    var modal = '<div id="divModal" class="modal fade" tabindex="-1" role="dialog">';
    modal += '  <div class="modal-dialog modal-dialog-centered" role="document">';
    modal += '      <div class="modal-content">';
    modal += '          <div class="modal-header">';
    modal += '              <h5 class="modal-title">Mensaje del Sistema</h5>';
    modal += '              <button type="button" class="close" data-dismiss="modal" aria-label="Close">';
    modal += '                  <span aria-hidden="true">&times;</span>';
    modal += '              </button>';
    modal += '          </div>';
    modal += '          <div class="modal-body">';
    modal += '              ' + mensaje;
    modal += '          </div>';
    modal += '          <div class="modal-footer">';
    modal += '              <button id="botonAceptar" type="button" onclick="eval(' + pFuncion + ')" class="btn btn-outline-primary"';
    modal += '                      data-dismiss="modal">' + textoBoton + '</button>';
    modal += '              <button id="botonCancelar" type="button" class="btn btn-outline-primary"';
    modal += '                      data-dismiss="modal">Cancelar</button>';
    modal += '          </div>';
    modal += '      </div>';
    modal += '  </div>';
    modal += '</div>';
    $('#mensajes').html(modal);
    $('#divModal').modal('show');
    $('#divModal').on('shown.bs.modal', function (e) {
        $('#botonAceptar').focus();
    });

    $('#divModal').on('hidden.bs.modal', function (e) {
        $("#mensajes").remove();
        $(".modal").remove();
        $(".modal-backdrop").remove();
    });
}

function focus(elemento){
    $(elemento).select();
}

function habilitar_agregar(){
    $('#boton-agregar').prop('disabled',false);
    $('#boton-modificar').prop('disabled',true);
    $('#boton-eliminar').prop('disabled',true);
}

function deshabilitar_agregar(){
    $('#boton-agregar').prop('disabled',true);
    $('#boton-modificar').prop('disabled',false);
    $('#boton-eliminar').prop('disabled',false);
}

function siguiente(funcion){
    if ($('#tbody_datos').text() !== '') {
        pagina++; 
        eval(funcion);
    }
}

function anterior(funcion){
    if (pagina > 1) {
        pagina--; 
        eval(funcion);
    }   
}

