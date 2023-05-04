<html>
	
	<head>

        <style>
            div {
                display:block;
            }
            </style>
		<script src="https://cdn.plot.ly/plotly-2.20.0.min.js" charset="utf-8"></script>
        <link rel="stylesheet" href="./css/mycss/graphstyle.css">
		
	</head>
	<body>
        <form action="ViewWorkoutGraph" method="post">
            <label for="exerciseName">Choose exercise name: </label>
            <select name="exerciseName" id="exerciseName">
                <option value="bicep_curl">Bicep Curl</option>
                <option value="hammer_curl">Hammer Curl</option>
                <option value="preacher_curl">Preacher Curl</option>
                <option value="tricep_pushdown">Tricep Pushdown</option>
                <option value="tricep_extention">Tricep Extention</option>
                <option value="reverse_dips">Reverse Dips</option>
                <option value="bench_press">Bench Press</option>
                <option value="chest_fly">Chest Fly</option>
                <option value="dips">Dips</option>
                <option value="lat_pulldown">Lat Pulldown</option>
                <option value="rows">Rows</option>
                <option value="deadlift">Deadlift</option>
                <option value="ab_crunches">Ab Crunches</option>
                <option value="russian_twists">Russian Twists</option>
                <option value="leg_raises">Leg Raises</option>
                <option value="weighted_squats">Weighted Squats</option>
                <option value="calf_raises">Calf Raises</option>
                <option value="leg_press">Leg Press</option>
                <option value="shoulder_press">Shoulder Press</option>
                <option value="reverse_flys">Reverse Flys</option>
                <option value="lateral_raise">Lateral Raise</option>
              </select>
            <input type="submit" value="Generate Graph">
        </form>

        <h1 id="title"></h1>
        <div id="workoutGraph"></div>
        
        <table>
            <tr>
                <td>
                    <div id="viewWorkoutDiv">
                        <form id="viewWorkoutForm" action="ViewWorkout" method="get">
                            <input type="submit" value="View Workout">
                        </form>
                    </div>
                </td>
                <td>
                    <div id="dashboardForm">
                        <form id="dashboardForm" action="dashboard.jsp" method="get">
                            <input type="submit" value="Dashboard">
                        </form>
                    </div>
                </td>
            </tr>
        </table>
        

        
        <script>



            var y = '<%= session.getAttribute("workoutIntensities")%>';
            var x = '<%= session.getAttribute("workoutDates")%>';
            var title = '<%= session.getAttribute("exerciseName")%>';
            if (title == "null") {
                title = "";
            }
            title = title.replace("_", " ");
            document.getElementById("title").innerHTML = (title.charAt(0).toUpperCase() + title.slice(1));
            x = x.replace('[', '');
            x = x.replace(']', '');
            y = y.replace('[', '');
            y = y.replace(']', '');
            xTab = x.split(',');
            yTab = y.split(',');

            var layout = {
                            title: title,
                            margin: { t: 0 }, 
                            xaxis: {
                                title: 'Date',
                                titlefont: {
                                family: 'Arial, sans-serif',
                                size: 18,
                                color: 'black'
                                },
                                showticklabels: true,
                                tickangle: 'auto',
                                tickfont: {
                                family: 'Old Standard TT, serif',
                                size: 14,
                                color: 'black'
                                },
                                exponentformat: 'e',
                                showexponent: 'all'
                            },
                            yaxis: {
                                title: 'Performance',
                                titlefont: {
                                family: 'Arial, sans-serif',
                                size: 18,
                                color: 'black'
                                },
                                showticklabels: true,
                                tickangle: 45,
                                tickfont: {
                                family: 'Old Standard TT, serif',
                                size: 14,
                                color: 'black'
                                },
                                exponentformat: 'e',
                                showexponent: 'all'
                            }
                            };
            graph = document.getElementById('workoutGraph');
			Plotly.newPlot( graph, [{
			x: xTab,
			y: yTab }], layout );
        </script>
	</body>
</html>