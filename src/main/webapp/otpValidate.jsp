<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
  <title>Simple Sign up from</title>
  <head>

  <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <style>
      html, body {
      display: flex;
      justify-content: center;
      font-family: Roboto, Arial, sans-serif;
      font-size: 15px;
      }
      form {
      border: 5px solid #f1f1f1;
      }
      input[type=text], input[type=password], select {
      width: 100%;
      padding: 16px 8px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      box-sizing: border-box;
      }
	  
      .icon {
      font-size: 110px;
      display: flex;
      justify-content: center;
      color: #4286f4;
      }
      #sendOTP {
         background-color: #586477;
		color: white;
		padding: 14px 0;
		margin: 10px 0;
		border: none;
		cursor: grab;
		width: 16%;
		height:10%;
		margin-left: 19px;
		padding: 10px 24px;
      }
	  
	  #submit {
      background-color: #4286f4;
      color: white;
      padding: 14px 0;
      margin: 10px 0;
      border: none;
      cursor: grab;
      width: 48%;
      }
      h1 {
      text-align:center;
      font-size:18;
      }
      button:hover {
      opacity: 0.8;
      }
      .formcontainer {
      text-align: center;
      margin: 24px 50px 12px;
      }
      .container {
      padding: 16px 0;
      text-align:left;
      }
      span.psw {
      float: right;
      padding-top: 0;
      padding-right: 15px;
      }
      /* Change styles for span on extra small screens */
      @media screen and (max-width: 300px) {
      span.psw {
      display: block;
      float: none;
      }
	  
	  

    </style>
  </head>
  <body>
    <form action="./otpValidate">
      <h1>Resource Alert Manager</h1>
      <div class="icon">
        <i class="fas fa-user-circle"></i>
      </div>
      <div class="formcontainer">
      <div class="container">
        
        <label for="uname"><strong>Enter Aquila Cloud User Id</strong></label>
        <input type="hidden" value="<%=request.getParameter("alert_id")%>" name="alert_id" required>
        <input type="text" placeholder="User Id" name="uid" required>
		 <label for="uname"><strong>Select Action</strong></label>
		 <select name="action">
          <option value=""></option>
          <option value="Shut_Down">Shut_Down</option>
          <option value="Keep_Running">Keep_Running</option>
         </select>
		
        <label for="mail"><strong>Enter OTP sent on E-mail</strong></label>
		<button type="button"  id = "sendOTP" width = "50px"; ><strong>Click to Generate OTP:</strong></button>
		<p id="status"></p>
        <input type="text" placeholder="Enter OTP" name="otp" required>
        
      </div>
      <button type="submit" id ="submit"><strong>Validate</strong></button>
     
        
       
      </div>
    </form>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		$("#sendOTP").click(function(){
    			
    			$.get("./sendOTP?alert_id="+<%=request.getParameter("alert_id")%>,function(data,status){
    				$("#status").text("OTP Sent to your Mail");
    			
    			});
    		});
    	});

    </script>
  
  </body>
</html>