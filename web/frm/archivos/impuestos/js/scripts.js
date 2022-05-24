habilitar_agregar();

$('#id_impuesto').select();

siguiente_campo('#id_impuesto','#nombre_impuesto',false);
siguiente_campo('#nombre_impuesto','#porcentaje_impuesto',false);
siguiente_campo('#porcentaje_impuesto','#boton-agregar',true);

function buscar_id(){
    console.log('buscar_id');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "impuesto/buscar/id";
        var pBeforeSend = "";
        var pSuccess = "buscar_id_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_success(json){
    console.log(json);
    $('#id_impuesto').val(json.id_impuesto);
    $('#nombre_impuesto').val(json.nombre_impuesto);
    $('#porcentaje_impuesto').val(json.porcentaje_impuesto);
    $('#porcentaje_impuesto').select();
    if (json.id_impuesto === 0) {
         siguiente_campo('#porcentaje_impuesto','#boton-agregar',true);
        habilitar_agregar();
    } else {
        siguiente_campo('#porcentaje_impuesto','#boton-modificar',true);
        deshabilitar_agregar();
    }
}

function buscar_nombre(){
    console.log('buscar_nombre');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto="+texto+"&pagina="+pagina;
        console.log(pDatosFormulario);
        var pUrl = "impuesto/buscar/nombre";
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
        var linea = '<tr onclick="seleccionar_impuesto($(this))">';
        linea += '  <td>' + value.id_impuesto + '</td>';
        linea += '  <td>' + value.nombre_impuesto + '</td>';
        linea += '  <td>' + value.porcentaje_impuesto + '</td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_impuesto($this){
    console.log($this);
    var id = $this.find('td').eq(0).text();
    var nombre = $this.find('td').eq(1).text();
    var porcentaje_impuesto = $this.find('td').eq(2).text();
    $('#id_impuesto').val(id);
    $('#nombre_impuesto').val(nombre);
    $('#porcentaje_impuesto').val(porcentaje_impuesto);
    salir_busqueda('#nombre_impuesto');
    deshabilitar_agregar();
    siguiente_campo('#porcentaje_impuesto','#boton-modificar',true);
}

function agregar(){
    console.log('agregar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "impuesto/agregar";
        var pBeforeSend = "";
        var pSuccess = "agregar_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function limpiar_campos(){
    $('#id_impuesto').val("0");
    $('#nombre_impuesto').val("");
    $('#porcentaje_impuesto').val("");
}

function agregar_success(json){
    console.log(json);
    if (json.agregado) {
        limpiar_campos();
        focus('#id_impuesto');
    } else {
        mensaje('Registro no agregado.','Aceptar','focus("#nombre_impuesto")');
    }
}

function modificar(){
    console.log('modificar()');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "impuesto/modificar";
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
        focus('#id_impuesto');
    } else {
        mensaje('Registro no modificado.','Aceptar','focus("#nombre_impuesto")');
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
        var pUrl = "impuesto/eliminar";
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
        focus('#id_impuesto');
    } else {
        mensaje('Registro no eliminado.','Aceptar','focus("#nombre_impuesto")');
    }
}

