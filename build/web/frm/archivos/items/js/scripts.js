habilitar_agregar(); 

$('#id_item').select();

siguiente_campo('#id_item', '#nombre_item', false);
siguiente_campo('#nombre_item', '#precio_item', false);
siguiente_campo('#precio_item', '#id_tipoitem', false);
siguiente_campo('#id_tipoitem', '#id_impuesto', false);
siguiente_campo('#id_impuesto', '#boton-agregar', true);

function buscar_id() {
    console.log('buscar_id');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "item/buscar/id";
    var pBeforeSend = "";
    var pSuccess = "buscar_id_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_success(json) {
    console.log(json);
    $('#id_item').val(json.id_item);
    $('#nombre_item').val(json.nombre_item);
    $('#precio_item').val(json.precio_item);
    $('#id_tipoitem').val(json.id_tipoitem);
    $('#nombre_tipoitem').val(json.nombre_tipoitem);
    $('#nombre_impuesto').val(json.nombre_impuesto);
    $('#id_impuesto').val(json.id_impuesto);


    if (json.id_item === 0) {
        siguiente_campo('#nombre_item', '#boton-agregar', true);
        habilitar_agregar();
    } else {
        siguiente_campo('#nombre_item', '#boton-modificar', true);
        deshabilitar_agregar();
    }
}

function buscar_nombre() {
    console.log('buscar_nombre');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto="+texto+"&pagina="+pagina;
    console.log(pDatosFormulario);
    var pUrl = "item/buscar/nombre";
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
        var linea = '<tr onclick="seleccionar_item($(this))">';
        linea += '  <td>' + value.id_item + '</td>';
        linea += '  <td>' + value.nombre_item + '</td>';
        linea += '  <td>' + value.precio_item + '</td>';
        linea += '  <td>' + value.id_tipoitem + '</td>';
        linea += '  <td>' + value.nombre_tipoitem + '</td>';
        linea += '  <td>' + value.id_impuesto + '</td>';
        linea += '  <td>' + value.nombre_impuesto + '</td>';
        linea += '  <td>' + value.porcentaje_impuesto + '</td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}

function seleccionar_item($this) {
    console.log($this);
    var id_item = $this.find('td').eq(0).text();
    var nombre_item = $this.find('td').eq(1).text();
    var precio_item = $this.find('td').eq(2).text();
    var id_tipoitem = $this.find('td').eq(3).text();
    var nombre_tipoitem = $this.find('td').eq(4).text();
    var id_impuesto = $this.find('td').eq(5).text();
    var nombre_impuesto = $this.find('td').eq(6).text();
    var porcentaje_impuesto = $this.find('td').eq(7).text();
    $('#id_item').val(id_item);
    $('#nombre_item').val(nombre_item);
    $('#precio_item').val(precio_item);
    $('#id_tipoitem').val(id_tipoitem);
    $('#nombre_tipoitem').val(nombre_tipoitem);
    $('#id_impuesto').val(id_impuesto);
    $('#nombre_impuesto').val(nombre_impuesto);
    $('#porcentaje_impuesto').val(porcentaje_impuesto);
    salir_busqueda('#id_item');
    deshabilitar_agregar();
    siguiente_campo('#id_impuesto', '#boton-modificar', true);
}

function agregar() {
    console.log('agregar()');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "item/agregar";
    var pBeforeSend = "";
    var pSuccess = "agregar_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function limpiar_campos() {
    $('#id_item').val("0");
    $('#nombre_item').val("");
    $('#precio_item').val("");
    $('#id_tipoitem').val("");
    $('#id_impuesto').val("");
}

function agregar_success(json) {
    console.log(json);
    if (json.agregado) {
        limpiar_campos();
        focus('#id_item');
    } else {
        mensaje('Registro no agregado.', 'Aceptar', 'focus("#nombre_item")');
    }
}

function modificar() {
    console.log('modificar()');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "item/modificar";
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
        focus('#id_item');
    } else {
        mensaje('Registro no modificado.', 'Aceptar', 'focus("#nombre_item")');
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
    var pUrl = "item/eliminar";
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
        focus('#id_item');
    } else {
        mensaje('Registro no eliminado.', 'Aceptar', 'focus("#nombre_item")');
    }
}
//Buscar Tipo Item////////////////////////////////////////////////////////


function buscar_id_tipoitem() {
    console.log('buscar_id_tipoitem');
    var pDatosFormulario = $("#form-formulario").serialize();
    console.log(pDatosFormulario);
    var pUrl = "tipoitem/buscar/id";
    var pBeforeSend = "";
    var pSuccess = "buscar_id_tipoitem_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function buscar_id_tipoitem_success(json) {
    console.log(json);
    $('#id_tipoitem').val(json.id_tipoitem);
    $('#nombre_tipoitem').val(json.nombre_tipoitem);
}

function buscar_nombre_tipoitem() {
    console.log('buscar_nombre_tipoitem');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto=" + texto + "&pagina=" + pagina;
    console.log(pDatosFormulario);
    var pUrl = "tipoitem/buscar/nombre";
    var pBeforeSend = "";
    var pSuccess = "buscar_nombre_tipoitem_success(json)";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombre_tipoitem_success(json) {
    console.log(json);
    $('#tbody_datos').text('');
    $.each(json, function (key, value) {
        var linea = '<tr onclick="seleccionar_tipoitem($(this))">';
        linea += '   <td>' + value.id_tipoitem + '</td>';
        linea += '   <td>' + value.nombre_tipoitem + '</td>';
        linea += '</tr>';
        $('#tbody_datos').append(linea);
    });
}
 
function seleccionar_tipoitem($this) {
    console.log($this);
    var id_tipoitem = $this.find('td').eq(0).text();
    var nombre_tipoitem = $this.find('td').eq(1).text();
    $('#id_tipoitem').val(id_tipoitem);
    $('#nombre_tipoitem').val(nombre_tipoitem);
    salir_busqueda('#id_item');
}




// Buscar Impuesto////////////////////////////////////////////////////////

function buscar_id_impuesto(){
    console.log('buscar_id_impuesto');
    var pDatosFormulario = $("#form-formulario").serialize();
        console.log(pDatosFormulario);
        var pUrl = "impuesto/buscar/id";
        var pBeforeSend = "";
        var pSuccess = "buscar_id_impuesto_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_id_impuesto_success(json){
    console.log(json);
    $('#id_impuesto').val(json.id_impuesto);
    $('#nombre_impuesto').val(json.nombre_impuesto);
    $('#porcentaje_impuesto').val(json.porcentaje_impuesto);
  
}

function buscar_nombre_impuesto(){
    console.log('buscar_nombre_impuesto');
    var texto = $('#texto').val();
    var pDatosFormulario = "&texto="+texto+"&pagina="+pagina;
        console.log(pDatosFormulario);
        var pUrl = "impuesto/buscar/nombre";
        var pBeforeSend = "";
        var pSuccess = "buscar_nombre_impuesto_success(json)";
        var pError = "ajax_error()";
        var pComplete = "";
        ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombre_impuesto_success(json){
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
}