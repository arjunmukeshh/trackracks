<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add food</title>
    <link rel="stylesheet" href="css/mycss/foodstyle.css">
</head>
<body>
    <h1>Add a new meal</h1>
    <form action="./NewMeal" method="post">
        <input type="hidden" name="dietID" id = "dietID">
        <br>
        <p>Name : </p><input type="text" name="name"><br>
        <p>Calories : </p><input type="number" name="calories" min="20" step="0.1"><br>
        <br>
        <input type="submit" value="Add">
        
    </form>
    <form action="dashboard.jsp" method="POST">
        <input type="submit" value="dashboard"/>
    </form>
    <script>
        let x = '<%= session.getAttribute("dietID") %>';
        function getName(){
            return x;
        }
        document.getElementById("dietID").value = x;
    </script>
</body>
</html>