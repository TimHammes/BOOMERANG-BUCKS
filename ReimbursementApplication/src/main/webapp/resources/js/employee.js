
'use strict'


 let listDisplay = document.getElementById("list-full");
 let ticketBtn = document.getElementById("new-ticket-btn");
 let ticketForm = document.getElementById("create-ticket");
 let tableBody;
 let users;
 let currentUser;
 let newArray;
 let reimbursements;
 let testBtn = document.getElementById("test-btn")
 let theCookie;
 let userField = document.getElementById("user-id");
 
window.onload = function(){
	
	theCookie = document.cookie;
	getUsers();
	getReimbursements();
	
	setTimeout(createTableFromJSONObject, 1000);
	let logoutBtn = document.getElementById("logoutBtn");
	logoutBtn.addEventListener("click", logout);
    ticketBtn.addEventListener("click", showTicketForm);
    //ticketForm.style.display = "none";
    tableBody = document.getElementById("t-body");
    
    console.log("Cookie:" + theCookie);
   // testBtn.addEventListener("click", test);
    

   
    
    
    
}

function test(){
	
	console.log(users);
	console.log(users[0].firstName);
	currentUser = getCurrentUser();
	
	console.log(currentUser);
}

function getReimbObject(object){return object;}

function getUserObject(object){return object;}

function logout(){
	location.href = "http://localhost:9001/ReimbursementApplication/index.html";
}

function getReimbursements() {
	
	console.log("beginning fetch");
    fetch('http://localhost:9001/ReimbursementApplication/json/reimbursements')
        .then(
            function (response) {
                if (response.status !== 200) {
                    return;
                }

                const reimbs = response.json();
                return reimbs;
            }
        ).then(
            function (reimbs) {
                reimbursements = getReimbObject(reimbs);
                console.log(reimbursements);
            });
}

function createTableFromJSONObject() {

		
  
    	newArray = reimbursements.filter(r => {
    			//console.log(r.reimb_author);
    			//console.log(currentUser.userId);
    			return r.reimb_author == currentUser.userId;
    		
        });
  
    
    if(newArray.length != 0){
    	
    	 let e = Object.entries(newArray);
    	    let _reimbursements = [];
    	    let props = ['reimb_id', 'reimb_amount', 'reimb_submitted', 'reimb_resolved',
    	        'reimb_description', 'reimbStatus', 'reimbType', 'reimb_author',
    	        'reimb_resolver', 'reimb_receipt'];

    	    for (let it in newArray) {
    	        _reimbursements.push(newArray[it]);
    	    }

    	    
    	    console.log( Object.values(reimbursements[0]));
    	    let rows = _reimbursements.length;
    	    let cols = Object.keys(_reimbursements[0]).length;

    	    let newTableBody = document.createElement('tbody');
    	    tableBody.parentNode.replaceChild(newTableBody, tableBody);
    	    
    	    for (let r = 0; r < rows; r++) {

    	        let x = newTableBody.insertRow(r);
    	        // let keys = Object.keys(reimbursements[r]);
    	        // let values = Object.values(reimbursements[r]);
    	        let entries = Object.entries(_reimbursements[r]);
    	        // console.log(entries);
    	        for (let c = 0; c < cols; c++) {
    	            let y = x.insertCell(c);
    	            for (let e of entries) {
    	            	if(e[0] == props[c] && props[c] == 'reimb_amount'){
    	            		
    	            		y.innerHTML = "$" + e[1].toFixed(2);
    	            		
    	            	}else if(e[0] == props[c] && props[c] == 'reimb_submitted'){
    	            		if(e[1]){
    	            			let date = new Date(parseInt(e[1])).toString();
        	            		y.innerHTML = date.substr(0,25);
    	            		}
    	            		
    	            	}else if(e[0] == props[c] && props[c] == 'reimb_resolved'){
    	            		if(e[1]){
    	            			let date = new Date(parseInt(e[1])).toString();
        	            		y.innerHTML = date.substr(0,25);
    	            		}
    	            		
    	            	
    	            	}else if (e[0] == props[c]) {
    	                    y.innerHTML = e[1];
    	                }else{
    	                	
    	                }
    	            }

    	        }
    	    }

    	    tableBody = newTableBody;
    }else{
    	console.log("is null")
    	let newTableBody = document.createElement('tbody');
 	    tableBody.parentNode.replaceChild(newTableBody, tableBody);
 	    tableBody = newTableBody;
 	    console.log("WTF");
    }

   
}

function getCurrentUser(){
	
	let disectedCookie = theCookie.split("=");
	let username = disectedCookie[1];
	console.log(username);
	
	console.log(users);
	let _currentUser = users.filter(user => {
		console.log(user.userName);
		return user.userName == username;
	} )
	currentUser = _currentUser[0];
	console.log("currrentUser: " + currentUser.userId);
	userField.value = currentUser.userId;
	console.log("setting field " +  userField.value);
	return currentUser;
	
}


function getUsers(){
	
	 fetch('http://localhost:9001/ReimbursementApplication/json/users')
	    .then(
	    	function(response){
	    		if(response.status !== 200){
	    			return;
	    		}
	    		
	    		const u =  response.json();
	    		return u;
	    		}
	    ).then(
	    		function(u){
	    		users = getUserObject(u);
	    });
	 
	 setTimeout(getCurrentUser, 1000);

}



function showTicketForm(){

	
	listDisplay.style.display = "none";
	ticketForm.style.display = "block";
   


};

