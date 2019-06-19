<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    table { background-color : #E4E2E2; width : 250pt; height : 70pt; text-align : center; }
    #left { float : left; margin-left : 7%; margin-bottom : 1%; }
    #right { float : right; margin-right : 7%; margin-bottom : 1%; }
    #foodName { color : black; font-size : 13pt; font-weight : bold; }
    #foodPrice { color : #8C8A8B; font-size : 11pt; font-weight : bold; }
</style>
</head>
<body>
<br>
</body>
</html>

<?php
include("./dbConnect.php");
showMenu();
?>