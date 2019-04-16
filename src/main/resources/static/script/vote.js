const UPVOTE_CLASS_NAME = ".up-vote";
const DOWNVOTE_CLASS_NAME = ".down-vote";

const UPVOTE_BASE_URL = "/vote/upvote";
const DOWNVOTE_BASE_URL = "/vote/downvote";

$(document).ready(function() {
	prepareVote(UPVOTE_CLASS_NAME, UPVOTE_BASE_URL);
	prepareVote(DOWNVOTE_CLASS_NAME, DOWNVOTE_BASE_URL);
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
		
		executeVoteAction(url, method, data, token, className);
	});
}

function executeVoteAction(url, method, data, token, className)
{
	fetch(url, {
		method: method,
		headers: {
           "Content-Type": "application/json",
           'X-CSRF-TOKEN': token,
        }
	 })
	 .then(function(response) {
		 if(response.status == 200)
		 {
			 let promise = response.json();
			 promise.then(function(result){
				let totalScore = result["total_score"];				
			 	$("#boisson-score-" + data["id"]).text(totalScore);
			 	
			 	if("score" in result){
			 		let score = result["score"];
			 		let upVoteBtn = $("#up-vote-" + data["id"]);
			 		let downVoteBtn = $("#down-vote-" + data["id"]);
			 		
			 		upVoteBtn.css('color', 'blue');
			 		downVoteBtn.css('color', 'blue');
			 		
			 		if(score == 1)
			 			{
			 			upVoteBtn.css('color', 'green');
			 			}
			 		else if(score == -1)
			 			{
			 			downVoteBtn.css('color', 'red');
			 			}
			 	}
			 });
		 }
		 else
		 {
			 console.log("Error: " + response.status);
		 }
	 })
	 .catch(function(error) {
		 console.log(error)
	 });
}
