<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daily Calories</title>
    <link rel="stylesheet" href="css/mycss/modifycaloriesstyle.css">
    
</head>
<body>
    <form action="ModifyDailyCalories" method="post">
        <label>Enter new daily calories goal: </label>
        <input type="number" name="caloriesGoalInput" id="caloriesGoalInput">
        <input type="submit">
    </form>

    <script>
        let x = '<%= session.getAttribute("username") %>';
        //window.alert(x)
    </script>
</body>
</html>