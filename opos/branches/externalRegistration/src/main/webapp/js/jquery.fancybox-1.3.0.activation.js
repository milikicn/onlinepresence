jQuery(document).ready(function() {
	
	/* Using custom settings */

	jQuery("a#inline").fancybox( {
		'hideOnContentClick' : false
	});

	jQuery("a.group").fancybox( {
		'speedIn' : 600,
		'speedOut' : 200,
		'overlayShow' : false
	});
});