$('#linkSalir').click(logout);

$.ajax({
	type: "GET",
	url: URL_SERVICIOS + "medicamentos/listarSumSaldo",
	contentType: "application/json; charset=utf-8",
	dataType: "json",
	success: function (data) {

		var table = $('#table-medicamentos tbody');

		for (var i in data) {

			var row = '<tr><td>' + data[i].nombreGenerico + '</td><td>' + data[i].saldo + '</td></tr>';

			table.append(row);
		}

		$('#table-medicamentos').DataTable();
		$('#table-medicamentos_length').hide();
		$('#table-medicamentos_filter').hide();
		$('#table-medicamentos_info').hide();
	},
	error: function (XMLHttpRequest, textStatus, errorThrown) {
		console.log("Request: " + XMLHttpRequest.toString() + "\n\nStatus: " + textStatus + "\n\nError: " + errorThrown);
	}
});

function logout(){
	document.getElementById("logoutForm").submit();
}