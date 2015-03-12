/**
 * 
 */

var ROOT_CONTEXT_URL = "service/";

$(document).ready(function() {

setEmptyUserIdDropDown();
//alert("hii");

$.ajax({
	type : "GET",
	url : '' + ROOT_CONTEXT_URL + 'getAllUsers',
	dataType : "json",
	async : true,
	beforeSend : function(request) {
		request
				.setRequestHeader("Content-Type",
						"application/json");
	},
	success : function(data) {
		//alert(data.Data.CharterIds);
		if (data.Data.Code == "SUCCESS") {
			//alert(data.Data.CharterIds);
			charterIdsListString = data.Data.CharterIds;
			setCharterIdDropDown();
		} else if (data.Data.Code == "FAILED") {
			alert("FAILED");
		} else if (data.Data.Code == "FORM_ERROR") {
			alert(data.Data.Message);
		} else if (data.Data.Code == "NO_DATA_AVAILABLE") {
			changeInputFieldValue("#accountNumber", AC_NUM_ERROR_MSG, "add");
			SEARCH_CAN_SUBMIT = false;
		} else if (data.Data.Code == "ERROR") {
			alert(data.Data.Message);
		} else if(data.Data.Code == "NO_INTR") {
			changeInputFieldValue("#accountNumber", AC_NUM_ERROR_MSG, "add");
			SEARCH_CAN_SUBMIT = false;
		}
	},
	error : function(xhr, ajaxOptions, thrownError) {
		//console.log(JSON.stringify(xhr));
		//alert("Error");
	}
});


























function getCharterIds(accountNumber) {
	
	$('#charterID').empty();
	setEmptyCharterIdDropDown();

		if(accountNumber!=undefined && accountNumber!=null && accountNumber!="") {
		
			$.ajax({
				type : "GET",
				url : '' + ROOT_CONTEXT_URL + 'device-manager/'+accountNumber+'/getcharterids?'+Math.random(),
				dataType : "json",
				async : true,
				beforeSend : function(request) {
					request
							.setRequestHeader("Content-Type",
									"application/json");
				},
				success : function(data) {
					//alert(data.Data.CharterIds);
					if (data.Data.Code == "SUCCESS") {
						//alert(data.Data.CharterIds);
						charterIdsListString = data.Data.CharterIds;
						setCharterIdDropDown();
					} else if (data.Data.Code == "FAILED") {
						alert("FAILED");
					} else if (data.Data.Code == "FORM_ERROR") {
						alert(data.Data.Message);
					} else if (data.Data.Code == "NO_DATA_AVAILABLE") {
						changeInputFieldValue("#accountNumber", AC_NUM_ERROR_MSG, "add");
						SEARCH_CAN_SUBMIT = false;
					} else if (data.Data.Code == "ERROR") {
						alert(data.Data.Message);
					} else if(data.Data.Code == "NO_INTR") {
						changeInputFieldValue("#accountNumber", AC_NUM_ERROR_MSG, "add");
						SEARCH_CAN_SUBMIT = false;
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					//console.log(JSON.stringify(xhr));
					//alert("Error");
				}
			});
		}	
}

function setCharterIdDropDownListString(selectedId) {
	var charterList = charterIdsListString.split(",");
    var charterIdDropDownString = ""; 
    for(var i = 0; i< charterList.length; i++){
    	if(charterList[i] == selectedId) {
    		charterIdDropDownString = charterIdDropDownString+"<option value="+charterList[i]+" selected=\"selected\">"+charterList[i]+"</option>";  
        }  else {
        	charterIdDropDownString = charterIdDropDownString+"<option value="+charterList[i]+" >"+charterList[i]+"</option>";  
        }
    	
    }
    //alert(charterIdDropDownString);
    return charterIdDropDownString;
}
function setCharterIdDropDown() {
	charterIdSelectString =  setCharterIdDropDownListString("0");
	$('#charterID').append(charterIdSelectString);
	
}

function setEmptyUserIdDropDown() {
	var userIdSelectString =  "<option value=\"0\" selected=\"selected\">Select UserID</option>";
	//alert("hii");
	$('#userId').empty();
	$('#userId').append(userIdSelectString);	
	$('#userId').append(userIdSelectString);
}

});