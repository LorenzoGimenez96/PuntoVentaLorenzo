siguiente_campo('#usuario_usuario', '#clave_usuario', false);
siguiente_campo('#clave_usuario', '#boton-ingresar', false);

function validar_usuario() {
    if (validar_formulario()) {
        console.log('validar_usuario()');
        var pDatosFormulario = $("#form-acceso").serialize();
        console.log(pDatosFormulario);
        var pUrl = "usuario/validar";
        var pBeforeSend = "";
        var pSuccess = "validar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
    }
}

function validar_formulario(){
    var ok = true;
    var usuario = $('#usuario_usuario').val();
    var clave = $('#clave_usuario').val();
    if (usuario.trim() === '') {
        mensaje('Usuario vacio', 'Aceptar', 'focus("#usuario_usuario")');
        ok = false;
    } else if (clave.trim() === '') {
        mensaje('Contraseña vacia', 'Aceptar', 'focus("#clave_usuario")');
        ok = false;
    }
    return ok;
}

function validar_success(json) {
    console.log(json);
    if (json.acceso) {
        location.href = './menu.html';
    } else {
        mensaje('Credencial incorrecta', 'Aceptar', 'focus("#usuario_usuario")');
    }
}
