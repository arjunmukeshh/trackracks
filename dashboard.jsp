<html>
	
	<head>
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">		<style>
		body {
			display: flex;
			flex-direction: column;
			justify-content: flex-start;
			align-items: center;
			height: 100vh;
			margin: 0;
			padding: 0;
			font-family: 'Archivo Black', sans-serif;
			}

		.container {
			display: grid;
			grid-template-columns: repeat(2, 1fr);
			grid-gap: 20px;
			max-width: 600px;
			text-align: center;
			margin-top: 150px;
		}

		.container form {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 250px;
			width: 250px;
			background-color: #f2f2f2;
			border-radius: 10px;
		}

		.container input[type="submit"] {
			width: 90%;
			height: 90%;
			font-size: 24px;
			background-color: #24b54b;
			color: white;
			border: none;
			border-radius: 5px;
			cursor: pointer;
		}

		#imageContainer {
			margin-top: 10px;
			position: absolute;
			top: 0;
			width:100%;
		}

		</style>

		<!-- <link rel="stylesheet" href="css/mycss/dashboardstyle.css"> -->
	</head>
	<body>
		<div id="imageContainer">
			<img src="assets/logo.png" width="50%">
		</div>
		<div class="container">
			<div id="addWorkoutDiv">
				<form id="addWorkoutForm" action="addWorkout.jsp" method="get">
					<input type="submit" value="Add workout">
				</form>
			</div>

			<div id="addDietDiv">
				<form id="addDietForm" action="addDiet.jsp" method="get">
					<input type="submit" value="Add diet">
				</form>
			</div>

			<div id="viewWorkoutDiv">
				<form id="viewWorkoutForm" action="ViewWorkout" method="get">
					<input type="submit" value="View Workout">
				</form>
			</div>

			<div id="viewDietDiv">
				<form id="viewDietForm" action="ViewDiet" method="get">
					<input type="submit" value="View Diet">
				</form>
			</div>
		</div>

		<div class="logout">
			<div class="top-right-div">
				<form action="Logout" method="get">
					<br> <input type="submit" value="Logout" />
				</form>
			</div>
		  </div>
		


		<!-- <div id="adminChanges" class="admin" style="display: none;">
			<form>
				<label>Admin Command Console:</label><br>
				<input id="adminSelector" type="text" placeholder="Enter Selector">
				<input id="adminProperty" type="text" placeholder="Enter Property">
				<input id="adminValue" type="text" placeholder="Enter Value">
				<button onclick="executeAdminCommand()">Change</button>
			</form>
		</div> -->


		<script>
			let x = '<%= session.getAttribute("isAdmin") %>';
			if(x == "1") {
				document.getElementById("addWorkoutDiv").style.display = "none";
				document.getElementById("addDietDiv").style.display = "none";
				document.getElementById("viewWorkoutForm").action = "ViewWorkoutAdmin";
				document.getElementById("viewDietForm").action = "ViewDietAdmin";
				document.getElementById("adminChanges").style.display = "block";
			}
			
			function changeStylesheetRule(stylesheet, selector, property, value) {
				// Make the strings lowercase
				selector = selector.toLowerCase();
				property = property.toLowerCase();
				value = value.toLowerCase();
				
				// Change it if it exists
				for(var i = 0; i < stylesheet.cssRules.length; i++) {
					var rule = stylesheet.cssRules[i];
					if(rule.selectorText === selector) {
						rule.style[property] = value;
						return;
					}
				}
			
				// Add it if it does not
				stylesheet.insertRule(selector + " { " + property + ": " + value + "; }", 0);
			}

			function executeAdminCommand() {
				var sel = document.getElementById("adminSelector").value;
				var prop = document.getElementById("adminProperty").value;
				var val = document.getElementById("adminValue").value;
				changeStylesheetRule(document.styleSheets[0], sel, prop, val);
			}
		</script>

	</body>
</html>