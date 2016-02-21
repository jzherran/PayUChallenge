function validateFormCreatePassenger() {
	var emailReg = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

	var firstName = $('#fistName');
	var lastName = $('#lastName');
	var idn = $('#idn');
	var age = $('#age');
	var email = $('#email');

	email.blur(function() {
		if (emailReg.test(email.val())) {
			email.addClass("hasError");
		}
	});
}

function deleteIDN(idn) {
	console.log(idn);
}

$(document).ready((function() {
	var form = $('#newPassenger');
	if (form.length > 0) {
		validateFormCreatePassenger();
	}

	var $button = $("#source-button").click(function() {
		var $actions = $(".actions");
		($actions.hasClass("hide")?$actions.removeClass("hide"):$actions.addClass("hide"));
	});

	$("#passengersTable").hover(function() {
		$(this).append($button);
		$button.show();
	}, function() {
		$button.hide();
	});
}));