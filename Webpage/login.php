<?php
include("dbConnect.php");

session_start();


$id = $_POST['id'];
$pw = md5($pw = $_POST['pw']);

$sql = "SELECT * FROM member WHERE id = '{$id}' AND password = '{$pw}'";
$res = $conn->query($sql);

$row = $res->fetch_array(MYSQLI_ASSOC);

if ($row != null) {
    $_SESSION['ses_userid'] = $row['memberId'];
    echo $_SESSION['ses_userid'].'님 안녕하세요';
    echo '';
}
else
    echo '아이디와 비밀번호가 일치하지 않습니다.';
?>
