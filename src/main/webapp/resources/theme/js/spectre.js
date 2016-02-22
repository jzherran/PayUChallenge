$(document).ready((function() {
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
	
	$("#routesTable").hover(function() {
		$(this).append($button);
		$button.show();
	}, function() {
		$button.hide();
	});
	
	$("#planesTable").hover(function() {
		$(this).append($button);
		$button.show();
	}, function() {
		$button.hide();
	});
	
	$("#flightsTable").hover(function() {
		$(this).append($button);
		$button.show();
	}, function() {
		$button.hide();
	});
}));