habilitar_agregar();

$('#id_tipoitem').select();

siguiente_campo('#id_tipoitem','#nombre_tipoitem',false);
siguiente_campo('#nombre_tipoitem','#boton-agregar',true);

function buscar_id(){
    console.log('buscar_id');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "tipoitem/buscar/id";
        var pBeforeSend = "";
        var pSuccess = "buscar_id_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_success(json){
    console.log(json);
    $('#id_tipoitem').val(json.id_tipoitem);
    $('#nombre_tipoitem').val(json.nombre_tipoitem);
    $('#nombre_tipoitem').select();
    if (json.id_tipoitem === 0) {
         siguiente_campo('#nombre_tipoitem','#boton-agregar',true);
        habilitar_agregar();
    } else {
        siguiente_campo('#nombre_tipoitem','#boton-modificar',true);
        deshabilitar_agregar();
    }
}

function buscar_nombre(){
    console.log('buscar_nombre');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto="+texto+"&pagina="+pagina;
        console.log(pDatosFormulario);
        var pUrl = "tipoitem/buscar/nombre";
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
        var linea = '<tr onclick="seleccionar_tipoitem($(this))">';
        linea += '  <td>' + value.id_tipoitem + '</td>';
        linea += '  <td>' + value.nombre_tipoitem + '</td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_tipoitem($this){
    console.log($this);
    var id_tipoitem = $this.find('td').eq(0).text();
    var nombre_tipoitem = $this.find('td').eq(1).text();
    $('#id_tipoitem').val(id_tipoitem);
    $('#nombre_tipoitem').val(nombre_tipoitem);
    salir_busqueda('#id_tipoitem');
    deshabilitar_agregar();
    siguiente_campo('#nombre_tipoitem','#boton-modificar',true);
}

function agregar(){
    console.log('agregar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "tipoitem/agregar";
        var pBeforeSend = "";
        var pSuccess = "agregar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function limpiar_campos(){
    $('#id_tipoitem').val("0");
    $('#nombre_tipoitem').val("");
}

function agregar_success(json){
    console.log(json);
    if (json.agregado) {
        limpiar_campos();
        focus('#id_tipoitem');
    } else {
        mensaje('Registro no agregado.','Aceptar','focus("#nombre_tipoitem")');
    }
}

function modificar(){
    console.log('modificar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "tipoitem/modificar";
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
        focus('#id_tipoitem');
    } else {
        mensaje('Registro no modificado.','Aceptar','focus("#nombre_tipoitem")');
    }
}

function eliminar(){
    console.log('eliminar()');
    confirmar('??Est?? seguro de eliminar este registro?','Eliminar','eliminar_ajax()');
}

function eliminar_ajax(){
    console.log('eliminar_ajax()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "tipoitem/eliminar";
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
        focus('#id_tipoitem');
    } else {
        mensaje('Registro no eliminado.','Aceptar','focus("#nombre_tipoitem")');
    }
}

