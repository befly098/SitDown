<?php
include("./dbConnect.php");
?>

<!DOCTYPE html>
<html>
<head>
    <title>앉자</title>
<meta charset="UTF-8">
<style>
    table { background-color : #E4E2E2; }
    .foodName { color : black; font-size : 12pt; }
    .foodPrice { color : #9C9B9B; font-size : 11pt; }
</style>
</head>

<body>
<div id = "menulist">
<?php showMenu(); ?>
</div>
</body>
</html>