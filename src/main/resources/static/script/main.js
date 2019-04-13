$(document).ready(function() {
	$(".favorite-link").click(function(e) {
		console.log(e.currentTarget);

		let action = $(e.currentTarget).attr("data-action");
		let url = "";
		let method = "";

		if (action == "add") {
			url = "/favorite/add";
			method = "POST";
		} else {
			url = "/favorite/remove";
			method = "DELETE";
		}
		
		let data = {
			id : $(e.currentTarget).attr("data-id-action")
		}

		let token = $("meta[name='_csrf']").attr("content");
		
		console.log(header);

		fetch(url, {
			method: method,
			headers: {
	           "Content-Type": "application/json",
	           'X-CSRF-TOKEN': token
	        },
	        body: JSON.stringify(data)
		 })
		 .then(function(response) {
			 console.log(response.json());
		 })
		 .catch(function(error) {
			 console.log(error)
		 });

		return false;
	});
});