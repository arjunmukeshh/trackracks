<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Diet</title>
    <link rel="stylesheet" href="css/mycss/dietstyle.css">
    
</head>
<body>
    <h1>Add a new diet</h1>

    <form action="./NewDiet" method="post">
        <p>Date : </p><input type="date" name="dietDate">
        <br>
        <input type="hidden" name="uname"  id = "uname">
        <br>
        <p>Description : </p><input type="text" name="description">
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