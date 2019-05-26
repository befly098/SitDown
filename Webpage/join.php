<?php
include("dbConnect.php");

$id = $_POST['id'];
$pw = $_POST['pw'];
$name = $_POST['name'];

// id 중복 검사
$sql = "SELECT * FROM member WHERE id = '{$id}'";
$res = $conn->query($sql);
if ($res->num_rows >= 1) {
    echo '이미 존재하는 아이디가 있습니다.';
    exit;
}

// 이름이 빈값이 아닌지
if ($name == '')
    echo '이름을 입력해주십시오.';

mysqli_select_db($conn, "sitdown");

$sql = "INSERT INTO member VALUES($id, $pw, $name);";

if ($conn->query($sql))
    echo '환영합니다';*/
?>

<script>
alert("환영합니다!");
window.location.href = 'http://localhost:155/login.html';
</script>