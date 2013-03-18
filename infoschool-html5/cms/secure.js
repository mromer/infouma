var request = $.ajax({
	type: 'GET',
	url: 'http://localhost:8080/infoschool/schoolrest/getstatus'

});

request.success(function(data, textStatus, jqXHR) {
	if (data == 'no_authenticated') {
		window.location.replace("index.html");
	} else {
		doPostValidate();
	}
});

request.error(function(jqXHR, textStatus, errorThrown) {
	alert('error:' + errorThrown);
});