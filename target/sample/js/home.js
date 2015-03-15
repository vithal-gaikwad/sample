/**
 * 
 */

var ROOT_CONTEXT_URL = "http://localhost:7080/sample/service/";

$(document).ready(function() {
	
	//showPosition('17.4431728','78.45198169999999');
	//getLocation();
	
$.ajax({
	type : "GET",
	url : ROOT_CONTEXT_URL + 'getAllUsers',
	dataType : "json",
	async : true,
	beforeSend : function(request) {
		request
				.setRequestHeader("Content-Type",
						"application/json");
	},
	success : function(data) {
		//alert(data.User[0].Id);
		var userIdSelectString="<option value=\"0\" selected=\"selected\">Select UserID</option>";
		
		var obj = data.User;
		$.each(obj, function(key,value) {			
			userIdSelectString=userIdSelectString+"<option value="+value.userId+" >"+value.userId+"</option>"; 
		}); 
		$('#userId').append(userIdSelectString);
		
	},
	error : function(xhr, ajaxOptions, thrownError) {
		//console.log(JSON.stringify(xhr));
		//alert("Error");
	}
});

});