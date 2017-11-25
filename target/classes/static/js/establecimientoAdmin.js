$('#linkSalir').click(logout);

var idEstablecimiento = getUrlValue("id");

generarTabla(idEstablecimiento);

function generarTabla(id){
	
	var url = "";
	
	var cabeceraTabla = $('#table-establecimieto thead');
	
	if(id == null) {
		url = URL_SERVICIOS + "establecimiento/";
		cabeceraTabla.html('<tr><th>Establecimieto</th><th>Accion</th></tr>');
	}else{
		url = URL_SERVICIOS + "medicamentos/listaPorIdEstablecimiento/1";
		cabeceraTabla.html('<tr><th>Medicamento</th><th>Saldo</th></tr>');
	}
	
	$.ajax({
		type: "GET",
		url: url,
		contentType: "application/json; charset=utf-8",
		//data : {fecha : date},
		dataType: "json",
		success: function (data) {
	
			var table = $('#table-establecimieto tbody');
	
			if(id == null){
				for (var i in data) {
		
					var row = '<tr ><td>' + data[i].nombre + '</td><td>' +
						'<a href="/inicio?id=' + data[i].id + '"><button class="btn btn-primary">Ver</button>'
						+ '</a></td></tr>';
		
					table.append(row);
				}
			}else{
				for (var i in data) {
					
					var row = '<tr ><td>' + data[i].nombreGenerico + '</td><td>' 
						+ data[i].saldo + '</td></tr>';
		
					table.append(row);
				}
			}
	
			$('#table-establecimieto').DataTable();
			$('#table-establecimieto_length').hide();
			$('#table-establecimieto_filter').hide();
			$('#table-establecimieto_info').hide();
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			console.log("Request: " + XMLHttpRequest.toString() + "\n\nStatus: " + textStatus + "\n\nError: " + errorThrown);
		}
	});
}

function logout(){
	document.getElementById("logoutForm").submit();
}

//Obtener el valor de la url
function getUrlValue(variable) {
	
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	for (var i=0;i<vars.length;i++) {
		var pair = vars[i].split("=");
	    if (pair[0] == variable) {
	      return pair[1];
	    }
	}
}