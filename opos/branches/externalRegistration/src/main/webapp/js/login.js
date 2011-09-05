;
jQuery(document).ready(function() {

	jQuery(".signin").click(function(e) {
		e.preventDefault();
		jQuery("fieldset#signin_menu").toggle();
		jQuery(".signin").toggleClass("menu-open");
		jQuery('#username').focus();
	});

	jQuery("fieldset#signin_menu").mouseup(function() {
		return false
	});

	jQuery(document).mouseup(function(e) {
		if (jQuery(e.target).parent("a.signin").length == 0) {
			jQuery(".signin").removeClass("menu-open");
			jQuery("fieldset#signin_menu").hide();
		}
	});
});