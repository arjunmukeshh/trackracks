<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Workout</title>
    <link rel="stylesheet" href="css/mycss/dietstyle.css">
    
</head>
<body>
    <h1>Add a new workout</h1>

    <form action="./NewWorkout" method="post">
        <p>Date : </p><input type="date" name="workoutDate">
        <br>
        <input type="hidden" name="uname"  id = "uname">
        <br>
        <label for="description">Type:</label>

        <select name="description" id="description">
            <option value="biceps">Biceps</option>
            <option value="triceps">Triceps</option>
            <option value="chest">Chest</option>
            <option value="back">Back</option>
            <option value="legs">Legs</option>
            <option value="shoulders">Shoulders</option>
            <option value="abs">Abs</option>
        </select>
        <br><br>
        <input type="submit">
    </form>

    <script>
        let x = '<%= session.getAttribute("username") %>';
        function getName(){
            return x;
        }
        document.getElementById("uname").value = x;
    </script>
</body>
</html>