<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    #info { font-size : 9pt; }
    input { width : 100pt; height : 100pt; border : none; outline : none; }
    #empty { background-color : #BCEBF1; font-size : 10pt; border-radius : 1em; }
    #occupied { background-color : #FF5F74; font-size : 10pt; border-radius : 1em; }
</style>
</head>

<body>

<div id = "info" align = "center">
    <img src = "./빈테이블.PNG" width = "30" height = "30"> 빈 테이블&nbsp;&nbsp;&nbsp;&nbsp;
    <img src = "./손님이 있는 테이블.PNG" width = "30" height = "30"> 손님이 있는 테이블
</div>
<br><br>
</body>
</html>

<?php 
include("./dbConnect.php"); 
seats();
?>