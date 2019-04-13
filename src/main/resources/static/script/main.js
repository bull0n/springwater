$(document).ready(function() {
	prepareFavoriteButton();
});

const ADD_BUTTON_TEMPLATE = "<i class=\"far fa-star\"></i>";
const REMOVE_BUTTON_TEMPLATE = "<i class=\"fas fa-star\"></i>";

function prepareFavoriteButton()
{
	$(".favorite-link").click(function(e) {
		let target = $(e.currentTarget);
		let action = target.attr("data-action");
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
			boissonId : target.attr("data-id-boisson")
		}
		
		url += "/" + data.boissonId;

		let token = $("meta[name='_csrf']").attr("content");

		fetch(url, {
			method: method,
			headers: {
	           "Content-Type": "application/json",
	           'X-CSRF-TOKEN': token
	        },
	        body: JSON.stringify(data)
		 })
		 .then(function(response) {
			 if(response.status == 200)
			 {
				if(action == "remove")
				{
					target.html(ADD_BUTTON_TEMPLATE);
					target.attr("data-action", "add");
					console.log("hello");
				}
				else
				{
					console.log("hello2");
					target.html(REMOVE_BUTTON_TEMPLATE);
					target.attr("data-action", "remove");
				}
			 }
			 else
			 {
				 console.log("error");
			 }
		 })
		 .catch(function(error) {
			 console.log(error)
		 });

		return false;
	});
}