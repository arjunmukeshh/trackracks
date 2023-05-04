<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Workout</title>
    <link rel="stylesheet" href="css/mycss/exercisestyle.css">
</head>
<body>
    <h1>Add a new Exercise</h1>
    <form action="./NewExercise" method="post">
        <input type="hidden" name="workoutID" id = "workoutID">
        <br>
        <p>Exercise name: </p>
       
        <select name="name" id="name">
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
        <br>
        <p>Sets : </p><input type="number" name="sets" min="1"><br>
        <p>Reps : </p><input type="number" name="reps" min="1"><br>
        <p>Weight : </p><input type="number" name="weight" min="0"><br>
        <p>How tired were you?</p>
        <div class="slidecontainer">
            <input type="range" min="1" max="10" value="50" class="slider" id="tirednessfactor" name="tirednessfactor">
        </div>

        <br>
        <input type="submit" value="add">
        
    </form>
    <form action="dashboard.jsp" method="POST">
        <input type="submit" value="dashboard"/>
    </form>
    <script>
        let x = '<%= session.getAttribute("workoutID") %>';
        function getName(){
            return x;
        }
        document.getElementById("workoutID").value = x;
    </script>
</body>
</html>