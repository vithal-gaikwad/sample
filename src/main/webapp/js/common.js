/**
 * 
 */

var ROOT_CONTEXT_URL = "http://localhost:8080/sample/service/";

$(document).ready(function() {
	
	
	
	//showPosition('17.4431728','78.45198169999999');
	//getLocation();
	
	$('#lastUpdate').hide();
	$.get("http://ipinfo.io", function(response) {
	    alert(response.city);
	}, "jsonp");
	
	$.get('http://jsonip.com/', function(r){ alert(r.ip); });	
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

$('#viewLocBtn').on('click', function() {	
	
	//alert($('#userId').val()); 
	var userId=$('#userId').val();
	
	
	$.ajax({
		type : "GET",
		url : ROOT_CONTEXT_URL + 'getUserData?userid='+userId,
		dataType : "json",
		async : true,
		beforeSend : function(request) {
			request
					.setRequestHeader("Content-Type",
							"application/json");
		},
		success : function(data) {
			//alert(data.User[0].Id);
			//var userIdSelectString="<option value=\"0\" selected=\"selected\">Select UserID</option>";
			
			var lat = data.lat;
			var lon = data.lon;
			var lastUpdated=data.lastUpdated;
			
			$('#lastUpateTime').text("");
			$('#lastUpateTime').append(lastUpdated);
			$('#lastUpdate').show();
			
			//alert("lat"+lat);
			//alert("lastUpdated"+lastUpdated);
			 document.cookie="lat="+lat;
			 document.cookie="lon="+lon;
			 document.cookie="lastUpdated="+lastUpdated;
			 
			 
			getLocation();
			
			//$.each(obj, function(key,value) {			
			//	userIdSelectString=userIdSelectString+"<option value="+value.userId+" >"+value.userId+"</option>"; 
			//}); 
			//$('#userId').append(userIdSelectString);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			//console.log(JSON.stringify(xhr));
			//alert("Error");
		}
	});

	

});



$('#delUserBtn').on('click', function() {	
	
	//alert($('#userId').val()); 
	var userId=$('#userId').val();
	
	
	$.ajax({
		type : "GET",
		url : ROOT_CONTEXT_URL + 'deleteUser?userid='+userId,
		dataType : "json",
		async : true,
		beforeSend : function(request) {
			request
					.setRequestHeader("Content-Type",
							"application/json");
		},
		success : function(data) {
			//alert(data.User[0].Id);
			//var userIdSelectString="<option value=\"0\" selected=\"selected\">Select UserID</option>";
			
			var Code = data.Code;
			var Message = data.Message;
			
			if(Code === "SUCCESS"){
				$('#message').css("color","green");
				
			}
            if(Code === "NO_DATA"){
            	$('#message').css("color","red");
				
			}
			
            $('#message').text("");
			
			$('#message').append(Message);
			
			//alert("lat"+lat);
			//alert("lastUpdated"+lastUpdated);
			 
			 
			
			//$.each(obj, function(key,value) {			
			//	userIdSelectString=userIdSelectString+"<option value="+value.userId+" >"+value.userId+"</option>"; 
			//}); 
			//$('#userId').append(userIdSelectString);
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
			//console.log(JSON.stringify(xhr));
			//alert("Error");
		}
	});

	

});


});

