habilitar_agregar();

$('#id_rol').select();

siguiente_campo('#id_rol','#nombre_rol',false);
siguiente_campo('#nombre_rol','#menu_rol',false);
siguiente_campo('#menu_rol','#boton-agregar',true);

function buscar_id(){
    console.log('buscar_id');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "rol/buscar/id";
        var pBeforeSend = "";
        var pSuccess = "buscar_id_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_success(json){
    console.log(json);
    $('#id_rol').val(json.id_rol);
    $('#nombre_rol').val(json.nombre_rol);
    $('#menu_rol').val(json.menu_rol);
    $('#menu_rol').select();
    if (json.id_rol === 0) {
         siguiente_campo('#menu_rol','#boton-agregar',true);
        habilitar_agregar();
    } else {
        siguiente_campo('#menu_rol','#boton-modificar',true);
        deshabilitar_agregar();
    }
}

function buscar_nombre(){
    console.log('buscar_nombre');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto="+texto+"&pagina="+pagina;
        console.log(pDatosFormulario);
        var pUrl = "rol/buscar/nombre";
        var pBeforeSend = "";
        var pSuccess = "buscar_nombre_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombre_success(json){
    console.log(json);
    $('#tbody_datos').text('');
    $.each(json, function (key, value) {
        var linea = '<tr onclick="seleccionar_rol($(this))">';
        linea += '  <td>' + value.id_rol + '</td>';
        linea += '  <td>' + value.nombre_rol + '</td>';
        linea += '  <td><textarea class="form-control form-control-sm" rows="5" readonly >' + value.menu_rol + '</textarea></td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_rol($this){
    console.log($this);
    var id = $this.find('td').eq(0).text();
    var nombre = $this.find('td').eq(1).text();
    var menu_rol = $this.find('td').eq(2).text();
    $('#id_rol').val(id);
    $('#nombre_rol').val(nombre);
    $('#menu_rol').val(menu_rol);
    salir_busqueda('#nombre_rol');
    deshabilitar_agregar();
    siguiente_campo('#menu_rol','#boton-modificar',true);
}

function agregar(){
    console.log('agregar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "rol/agregar";
        var pBeforeSend = "";
        var pSuccess = "agregar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function limpiar_campos(){
    $('#id_rol').val("0");
    $('#nombre_rol').val("");
    $('#menu_rol').val("");
}

function agregar_success(json){
    console.log(json);
    if (json.agregado) {
        limpiar_campos();
        focus('#id_rol');
    } else {
        mensaje('Registro no agregado.','Aceptar','focus("#nombre_rol")');
    }
}

function modificar(){
    console.log('modificar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "rol/modificar";
        var pBeforeSend = "";
        var pSuccess = "modificar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_success(json){
    console.log(json);
    if (json.modificado) {
        limpiar_campos();
        focus('#id_rol');
    } else {
        mensaje('Registro no modificado.','Aceptar','focus("#nombre_rol")');
    }
}

function eliminar(){
    console.log('eliminar()');
    confirmar('¿Está seguro de eliminar este registro?','Eliminar','eliminar_ajax()');
}

function eliminar_ajax(){
    console.log('eliminar_ajax()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "rol/eliminar";
        var pBeforeSend = "";
        var pSuccess = "eliminar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function eliminar_success(json){
    console.log(json);
    if (json.eliminado) {
        limpiar_campos();
        focus('#id_rol');
    } else {
        mensaje('Registro no eliminado.','Aceptar','focus("#nombre_rol")');
    }
}

