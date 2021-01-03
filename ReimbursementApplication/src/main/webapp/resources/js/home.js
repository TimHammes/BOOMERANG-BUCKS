'use strict'

 // let reimBtn = document.getElementById("reimb-btn");
 //let ticketForm = document.getElementById("create-ticket");
 let listDisplay = document.getElementById("list-full");
 //let ticketBtn = document.getElementById("new-ticket-btn");
 let tableBody = document.querySelector("tbody");
 //let testButton = document.getElementById("test-btn");
 let filterButton = document.getElementById("filter-btn");
 let filterDrop = document.getElementById("status-op");
 let reimbs;
 let users;
 let username;
 
 
window.onload = function(){
	
	getUsers();
	getReimbursements();
	
	//testButton.addEventListener("click", test);
	filterButton.addEventListener("click", filterReimb);
	filterDrop.addEventListener("change", filterReimb);
    //ticketBtn.addEventListener("click", showTicketForm);
    //ticketForm.style.display = "none";
    
    username = sessionStorage.getItem("loggedusername");

}

function test(){
	console.log(reimbs);
	console.log(reimbs[0].reimbStatus)
	console.log(users);
	console.log(users[0].firstName);
}

function filterReimb(){

	   let cells = document.querySelectorAll('td');
	   
	   let rows = [];
	   
	    for(let cell of cells){
	    	
		    
	        if(cell.innerHTML == filterDrop.value){
	           rows.push(cell.parentElement);
	        }
	        
	      
	}
	    createTableFromArray(rows);
}

function getReimbursements (){

    fetch('http://localhost:9001/ReimbursementApplication/json/reimbursements')
    .then(
    	function(response){
    		if(response.status !== 200){
    			return;
    		}
    		
    		const reimbs =  response.json();
    		return reimbs;
    		}
    ).then(
    		function(reimbs){
    			createTableFromJSONObject(reimbs);
    });
 }

function createTableFromArray(arr){
	
	
	
}

function createTableFromJSONObject(jsonObject){
	 //console.log(jsonObject);
	 let e = Object.entries(jsonObject);
	// console.log(e);
	 let reimbursements = [];
     let props = ['reimb_id','reimb_amount','reimb_submitted','reimb_resolved',
         'reimb_description','reimbStatus','reimbType','reimb_author',
         'reimb_resolver', 'reimb_receipt'];
     
 
     
     for(let it in jsonObject){
         reimbursements.push(jsonObject[it]);
     }

    // console.log( Object.values(reimbursements[0]));
     let rows = reimbursements.length;
     let cols = Object.keys(reimbursements[0]).length;
     
     for(let r = 0; r < rows; r++){
    	 
         let x = tableBody.insertRow(r);
        // let keys = Object.keys(reimbursements[r]);
        // let values = Object.values(reimbursements[r]);
         let entries = Object.entries(reimbursements[r]);
        // console.log(entries);
         for(let c = 0; c < cols; c++){
             let y = x.insertCell(c);
             for(let e of entries){
                 if(e[0] == props[c]){
                     y.innerHTML = e[1];
                 }
             }

         }
     }
     
     reimbs = getReimbObject(jsonObject);
    
     
}


function getReimbObject(object){return object;}

function getUserObject(object){return object;}


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

}



function showTicketForm(){

	console.log("ticket form click");
	ticketForm.style.display = "block";
    listDisplay.style.display = "none";


};

