var token = $('#csrfToken').val();
var header = $('#csrfHeader').val();
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1;
var yyyy = today.getFullYear();
var date = yyyy + '/' + mm + '/' + dd;

$('#linkSalir').click(logout);

//generar tabla
$.ajax({
    type: "GET",
    url: URL_SERVICIOS + "medicamentos/listaRegistradosConSaldo",
    contentType: "application/json; charset=utf-8",
    data : {fecha : date},
    dataType: "json",
    success: function (data) {

        var table = $('#table-medicamentos tbody');

        for (var i in data) { 
        	
            var row = '<tr id="row' + data[i].id + '"><td id="row-nombre' + data[i].id + '">' + data[i].nombreGenerico + '</td>'
            	+ '<td id="row-saldo' + data[i].id + '">' + data[i].saldo + '</td><td>'
                + '<button data-toggle="modal" data-target="#squarespaceModal" class="btn btn-primary" onclick="setIdMedicamento(' + data[i].id + ')">Actualizar</button>'
                + '</td></tr>';

            table.append(row);
        }

        $('#table-medicamentos').DataTable();
        $('#table-medicamentos_length').hide();
        $('#table-medicamentos_filter').hide();
        $('#table-medicamentos_info').hide();
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log("Error");
    }
});

//Set titulo modal, id input hide
function setIdMedicamento(idMedi) {

    $.ajax({
        type: "GET",
        url: URL_SERVICIOS + "historial/",
        data : {id : idMedi, fecha : date},
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {

            $('#lab-mayorista').val(data.labFarma);
            $('#ingreso-cantidad').val(data.ingreso);
            $('#salida-cantidad').val(data.salida);
            $('#salida-formulas').val(data.radFormulas);
            $('#id-historial').val(data.id);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log("Request: " + XMLHttpRequest.toString() + "\n\nStatus: " + textStatus + "\n\nError: " + errorThrown);
        }
    });

    limpiar();
    var nombre = $('#row-nombre' + idMedi).html();
    $('#lineModalLabel').html(nombre);
    $('#id-registro').val(idMedi);

}

$(document).on('ready', function () {
    $('#btn-guardar').click(validar);
});

function validar() {

    var correcto = true;
    var ingresoCantidad = $('#ingreso-cantidad').val();
    var labMayorista = $('#lab-mayorista').val();
    var salidaCantidad = $('#salida-cantidad').val();
    var salidaFormulas = $('#salida-formulas').val();
    var idMedicamento = $('#id-registro').val();
    var idHistial = $('#id-historial').val();

    if (ingresoCantidad == "" || /^\s+$/.test(ingresoCantidad)) {
        $('#ingreso-cantidad').parent().attr('class', 'form-group has-error');
        $('#ingreso-cantidad').parent().children('span').text('Los datos no son correctos');
        correcto = false;
    } else {
        $('#ingreso-cantidad').parent().attr('class', 'form-group');
        $('#ingreso-cantidad').parent().children('span').text('');
    }

    if (salidaCantidad == "" || /^\s+$/.test(salidaCantidad)) {
        $('#salida-cantidad').parent().attr('class', 'form-group has-error');
        $('#salida-cantidad').parent().children('span').text('Los datos no son correctos');
        correcto = false;
    } else {
        $('#salida-cantidad').parent().attr('class', 'form-group');
        $('#salida-cantidad').parent().children('span').text('');
    }

    if (salidaFormulas == "" || /^\s+$/.test(salidaFormulas)) {
        $('#salida-formulas').parent().attr('class', 'form-group has-error');
        $('#salida-formulas').parent().children('span').text('Los datos no son correctos');
        correcto = false;
    } else {
        $('#salida-formulas').parent().attr('class', 'form-group');
        $('#salida-formulas').parent().children('span').text('');
    }

    if (!correcto) {
        return false;
    }

    var historial = {
        id : idHistial,
        establecimientoMedicamento: {
            medicamentos: {
                id: idMedicamento
            }
        },
        labFarma: labMayorista,
        salida: salidaCantidad,
        radFormulas: salidaFormulas,
        fecha: dd +'/'+ mm +'/'+ yyyy,
        ingreso: ingresoCantidad
    }

    $.ajax({
        type: "PUT",
        url: URL_SERVICIOS + "historial/",
        data: JSON.stringify(historial),
        async: true,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success: function (models) {

            if (models.status == "OK") {
            	
            	$("#row-saldo"+idMedicamento).html(models.saldo);
                $('#mensaje').attr('class', 'alert alert-success alert-dismissable');
                $('#mensaje').html('<button type="button" class="close" data-dismiss="alert">&times;</button>'
                    + '<strong>Medicamento actualizado exitosamente.</strong>');
            }
        },
        error: function (XMLHttpRequest, textStatus,
            errorThrown) {

            $('#mensaje').attr('class', 'alert alert-danger alert-dismissable');
            $('#mensaje').html('<button type="button" class="close" data-dismiss="alert">&times;</button>'
                + '<strong>Ocurrio un error, intenta de nuevo.</strong>');

        }
    });

}

function limpiar() {
    $('#ingreso-cantidad').val('');
    $('#lab-mayorista').val('');
    $('#salida-cantidad').val('');
    $('#salida-formulas').val('');
    $('#id-registro').val('');
    $('#id-historial').val('');
}

function logout(){
	document.getElementById("logoutForm").submit();
}