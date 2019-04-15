const UPVOTE_CLASS_NAME = ".up-vote";
const DOWNVOTE_CLASS_NAME = ".down-vote";

const UPVOTE_BASE_URL = "/vote/upvote";
const DOWNVOTE_BASE_URL = "/vote/downvote";

$(document).ready(function() {
	console.log("Document is ready")
	prepareVote(UPVOTE_CLASS_NAME, UPVOTE_BASE_URL);
	console.log("upvote is ready")
	prepareVote(DOWNVOTE_CLASS_NAME, DOWNVOTE_BASE_URL);
	console.log("downvote is ready")
});

function prepareVote(className, baseUrl)
{
	$(className).click(function(e) {
		let target = $(e.currentTarget);
		let data = {id : target.attr("data-id-boisson")};
		let url = baseUrl + "/" + data["id"];
		let method = "POST";
		let token = $("meta[name='_csrf']").attr("content");
		console.log("Clicked on " + className);
		
		executeVoteAction(url, method, data, token);
	});
}

function executeVoteAction(url, method, data, token)
{
	fetch(url, {
		method: method,
		headers: {
           "Content-Type": "application/json"
        },
        //body: JSON.stringify(data)
	 })
	 .then(function(response) {
		 if(response.status == 200)
		 {
			 console.log("New score set !");
		 }
		 else
		 {
			 console.log("Error: there's a problem when applying the vote.");
		 }
	 })
	 .catch(function(error) {
		 console.log(error)
	 });
}
