function validate() {
    
    var x=document.forms["myform"]["name"].value;
    var y=document.forms["myform"]["contact_number"].value;
    var w=document.forms["myform"]["email"].value;
    var t=document.forms["myform"]["location_lost"].value;
    var d=document.forms["myform"]["item_lost"].value;
    var df=document.forms["myform"]["date_lost"].value;
    
    if (x === "") {
        alert("Please fill in your name");
        
        return false;
    } else if (y === "") {
        alert("Please fill in your contact number");
   
        return false;
    } else if (w === "") {
        alert("Please fill in your email address");
        
        return false;
    } else if (t === "") {
        alert("Please fill in the location lost");
        
        return false;
    } else if (d === "") {
        alert("Please fill in the item lost");
        
        return false;
    } else if (df === "") {
        alert("Please fill in the date lost");
        
        return false;
    } else {
        return true;
    }
}
