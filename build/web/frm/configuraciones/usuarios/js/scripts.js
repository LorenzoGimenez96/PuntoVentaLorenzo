
habilitar_agregar();

$('#id_usuario').select();

siguiente_campo('#id_usuario', '#nombre_usuario', false);
siguiente_campo('#nombre_usuario', '#usuario_usuario', false);
siguiente_campo('#usuario_usuario', '#clave_usuario', false);
siguiente_campo('#clave_usuario', '#id_rol', false);
siguiente_campo('#id_rol', '#boton-agregar', true);

function buscar_id() {
    console.log('buscar_id');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "usuario/buscar/id";
    var pBeforeSend = "";
    var pSuccess = "buscar_id_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_success(json) {
    console.log(json);
    $('#id_usuario').val(json.id_usuario);
    $('#nombre_usuario').val(json.nombre_usuario);
    $('#usuario_usuario').val(json.usuario_usuario);
    $('#clave_usuario').val(json.clave_usuario);
    $('#id_rol').val(json.id_rol);
    $('#nombre_rol').val(json.nombre_rol);
    $('#nombre_usuario').select();
    if (json.id_usuario === 0) {
        siguiente_campo('#id_rol', '#boton-agregar', true);
        habilitar_agregar();
    } else {
        siguiente_campo('#id_rol', '#boton-modificar', true);
        deshabilitar_agregar();
    }
}

function buscar_nombre() {
    console.log('buscar_nombre');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto=" + texto + "&pagina=" + pagina;
    console.log(pDatosFormulario);
    var pUrl = "usuario/buscar/nombre";
    var pBeforeSend = "";
    var pSuccess = "buscar_nombre_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombre_success(json) {
    console.log(json);
    $('#tbody_datos').text('');
    $.each(json, function (key, value) {
        var linea = '<tr onclick="seleccionar_usuario($(this))">';
        linea += '  <td>' + value.id_usuario + '</td>';
        linea += '  <td>' + value.nombre_usuario + '</td>';
        linea += '  <td>' + value.usuario_usuario + '</td>';
        linea += '  <td>' + value.clave_usuario + '</td>';
        linea += '  <td>' + value.id_rol + '</td>';
        linea += '  <td>' + value.nombre_rol + '</td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_usuario($this) {
    console.log($this);
    var id = $this.find('td').eq(0).text();
    var nombre = $this.find('td').eq(1).text();
    var usuario = $this.find('td').eq(2).text();
    var clave = $this.find('td').eq(3).text();
    var id_rol = $this.find('td').eq(4).text();
    var nombre_rol = $this.find('td').eq(5).text();

    $('#id_usuario').val(id);
    $('#nombre_usuario').val(nombre);
    $('#usuario_usuario').val(usuario);
    $('#clave_usuario').val(clave);
    $('#id_rol').val(id_rol);
    $('#nombre_rol').val(nombre_rol);

    salir_busqueda('#nombre_usuario');
    deshabilitar_agregar();
    siguiente_campo('#id_rol', '#boton-modificar', true);
}

function agregar() {
    console.log('agregar()');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "usuario/agregar";
    var pBeforeSend = "";
    var pSuccess = "agregar_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function limpiar_campos() {
    $('#id_usuario').val("0");
    $('#nombre_usuario').val("");
    $('#usuario_usuario').val("");
    $('#clave_usuario').val("");
    $('#id_rol').val("0");
    $('#nombre_rol').val("");
}

function agregar_success(json) {
    console.log(json);
    if (json.agregado) {
        limpiar_campos();
        focus('#id_usuario');
    } else {
        mensaje('Registro no agregado.', 'Aceptar', 'focus("#nombre_usuario")');
    }
}

function modificar() {
    console.log('modificar()');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "usuario/modificar";
    var pBeforeSend = "";
    var pSuccess = "modificar_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_success(json) {
    console.log(json);
    if (json.modificado) {
        limpiar_campos();
        focus('#id_usuario');
    } else {
        mensaje('Registro no modificado.', 'Aceptar', 'focus("#nombre_usuario")');
    }
}

function eliminar() {
    console.log('eliminar()');
    confirmar('¿Está seguro de eliminar este registro?', 'Eliminar', 'eliminar_ajax()');
}

function eliminar_ajax() {
    console.log('eliminar_ajax()');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "usuario/eliminar";
    var pBeforeSend = "";
    var pSuccess = "eliminar_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function eliminar_success(json) {
    console.log(json);
    if (json.eliminado) {
        limpiar_campos();
        focus('#id_usuario');
    } else {
        mensaje('Registro no eliminado.', 'Aceptar', 'focus("#nombre_usuario")');
    }
}

//Roles

function buscar_id_rol() {
    console.log('buscar_id_rol');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "rol/buscar/id";
    var pBeforeSend = "";
    var pSuccess = "buscar_id_rol_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function buscar_id_rol_success(json) {
    console.log(json);
    $('#id_rol').val(json.id_rol);
    $('#nombre_rol').val(json.nombre_rol);
}

function buscar_nombre_rol() {
    console.log('buscar_nombre_rol');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto=" + texto + "&pagina=" + pagina;
    console.log(pDatosFormulario);
    var pUrl = "rol/buscar/nombre";
    var pBeforeSend = "";
    var pSuccess = "buscar_nombre_rol_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombre_rol_success(json) {
    console.log(json);
    $('#tbody_datos').text('');
    $.each(json, function (key, value) {
        var linea = '<tr onclick="seleccionar_rol($(this))">';
        linea += '   <td>' + value.id_rol + '</td>';
        linea += '   <td>' + value.nombre_rol + '</td>';
        linea += '  <td><textarea class="form-control form-control-sm" rows="5" readonly >' + value.menu_rol + '</textarea></td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_rol($this) {
    console.log($this);
    var id = $this.find('td').eq(0).text();
    var nombre = $this.find('td').eq(1).text();
    $('#id_rol').val(id);
    $('#nombre_rol').val(nombre);
    salir_busqueda('#menu_permiso');
}



