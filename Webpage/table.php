<?php
include("dbConnect.php");

if ($conn)
    echo "DB 연결성공";
else
    echo "DB 연결실패";

$db = mysqli_select_db($conn, "sitdown");
if ($db)
    echo "DB 연결성공";
else
    echo "DB 연결실패";

$sql = "create table php_tbl(id varchar(20), pw varchar(20), name varchar(10))";
mysqli_query($conn, $sql);
?>

<script>
alert("테이블 생성!!");
</script>