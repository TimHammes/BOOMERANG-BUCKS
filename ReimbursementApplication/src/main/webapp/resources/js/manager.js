'use strict'


let listDisplay = document.getElementById("list-full");
let tableBody;
let sfilter;
let newArray;
let reimbursements;
let users;
let username;
let currentUser;
let theCookie;
window.onload = function () {
	theCookie = document.cookie;
	getUsers();
    getReimbursements();
    setTimeout(createTableFromJSONObject, 1000);
    tableBody = document.getElementById("t-body");
    let logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.addEventListener("click", logout);
    let listDisplay = document.getElementById("list-full");
    let filterDrop = document.getElementById("status-op");
    console.log("Cookie:" + theCookie);
    filterDrop.addEventListener("change", createTableFromJSONObject);

}


function getReimbObject(object) { return object; }

function getUserObject(object) { return object; }

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

function getUsers() {

    fetch('http://localhost:9001/ReimbursementApplication/json/users')
        .then(
            function (response) {
                if (response.status !== 200) {
                    return;
                }

                const u = response.json();
                return u;
            }
        ).then(
            function (u) {
                users = getUserObject(u);
            });
    
    setTimeout(getCurrentUser, 1000);

}


function createTableFromJSONObject() {
	
	sfilter = document.getElementById("status-op").value;
	
    if (sfilter != "ALL") {
    	newArray = reimbursements.filter(r => {
    		if(r.reimbStatus == sfilter){
    			return r.reimbStatus;
    		}
        });
    } else {
        newArray = reimbursements;
    }
    
    if(newArray.length != 0){
    	
    	 let e = Object.entries(newArray);
    	    let _reimbursements = [];
    	    let props = ['reimb_id', 'reimb_amount', 'reimb_submitted', 'reimb_resolved',
    	        'reimb_description', 'reimbStatus', 'reimbType', 'reimb_author',
    	        'reimb_resolver', 'reimb_receipt'];

    	    for (let it in newArray) {
    	        _reimbursements.push(newArray[it]);
    	    }

    	    
    	    //console.log( Object.values(reimbursements[0]));
    	    let rows = _reimbursements.length;
    	    let cols = Object.keys(_reimbursements[0]).length;

    	    let newTableBody = document.createElement('tbody');
    	    tableBody.parentNode.replaceChild(newTableBody, tableBody);
    	    
    	    for (let r = 0; r < rows; r++) {

    	        let x = newTableBody.insertRow(r);
    	        let entries = Object.entries(_reimbursements[r]);
   
    	        for (let c = 0; c < cols; c++) {
    	            let y = x.insertCell(c);
    	            for (let e of entries) {
    	            	if(e[0] == props[c] && props[c] == 'reimbStatus'){
    	            		let el = document.createElement('input');
    	            		el.setAttribute("type", "text");
    	            		el.setAttribute("value", e[1]);
    	            		el.innerHTML = e[1];
    	            		el.disabled = true;
    	            		y.appendChild(el);
    	            		let btn = document.createElement('button');
    	            		btn.innerHTML = "CHANGE";
    	            		btn.addEventListener("click", changeStatusHandler)
    	            		y.appendChild(btn);
    	            	}else if(e[0] == props[c] && props[c] == 'reimb_amount'){
    	            		
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
	
	return currentUser;
	
}

function changeStatusHandler(){
	console.log("this:" + this)
	console.log("change status button clicked");
	let currentStatus = this.previousElementSibling.value
	let idCellText = this.parentElement.parentElement.firstChild.innerText;
	let reimbId = parseInt(idCellText);
	
	console.log(typeof(reimbId));
	
	console.log("currentStatus:" + currentStatus);
	switch(currentStatus){
		case "PENDING":
			this.previousElementSibling.value = "APPROVED";
			updateStatus(reimbId,this.previousElementSibling.value, currentUser.userId)
		
			
			break;
		case "APPROVED":
			this.previousElementSibling.value = "DENIED";
			updateStatus(reimbId,this.previousElementSibling.value, currentUser.userId)
		
			break;
		case "DENIED":
			this.previousElementSibling.value = "PENDING";
			updateStatus(reimbId,this.previousElementSibling.value, currentUser.userId)
			
			break;
		default:
			break;
	}
	
	 //getReimbursements();
	//createTableFromJSONObject();
}

function updateStatus(reimbId, reimbStatus, resolver ){
	console.log("finance manager: " +currentUser.firstName);
	//console.log("reimbStatus: " + reimbStatus);
	const data = {"reimb_id": reimbId , "reimb_status": reimbStatus, "resolver":resolver};
	console.log(data);
	
	fetch('http://localhost:9001/ReimbursementApplication/json/update',{
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(data)
			
			})
			.then(response => response.json())
			.then(ourData => {
				console.log('Success:', ourData);
			})
			.catch((error) => {
				console.error('Error:', error);
			});
}



