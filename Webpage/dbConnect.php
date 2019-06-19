<?php
include("./observer.php");

$host = "localhost";
$user = "root";
$password = "123456";
$dbName = "sitdown";
$tables = array();

$conn = new mysqli($host, $user, $password, $dbName);
if (!$conn) {
    echo "mysql 연결실패<br />";
}

$noty = new Publisher('NotificationPublisher');
$email = new Observer('EmailObserver', 50);
$slack = new Observer('SlackObserver', 10);
$dashboard = new Observer('DashboardObserver', 30);

$noty->attach($email);
$noty->attach($slack);
$noty->attach($dashboard);

$noty->setEvent("server LNX109 is going down");


function totalTable() {
    global $conn, $tables;

    $sql = "SELECT * FROM Otable WHERE Tnum == -1";
    $result = mysqli_query($conn, $sql);
    if ($result === false) {
        return mysqli_error($conn);
    }
    
    $row = mysqli_fetch_assoc($result);
    if ($row['Tquant'] != count($tables)) {
        if ($row['Tquant'] > count($tables)) {
            for ($i = 0; $i < $row['Tquant'] - count($tables); $i++) {
                array_push($tables, '-1');
            }
        }
        else {
            for ($i = 0; $i < count($tables) - $row['Tquant']; $i++) {
                $tables = array_pop($tables);
            }
        }
    }

    for ($i = 0; $i < count($tables); $i++) {
        $tables[$i] = -1;
    }
}

function seats() {
    global $conn, $tables;

    totalTable();

    $sql = "SELECT * FROM Otable WHERE Tnum != -1";
    if (!$result = mysqli_query($conn, $sql)) {
        echo "Otable query fail...\n";
    }

    while ($row = mysqli_fetch_assoc($result)) {
        $tables[$row['Tnum']] = 1;
    }

    for ($i = 0; $i < count($tables); $i++) {
        $j = $i + 1;
        if ($tables[$i] == 1) {
            echo "<script>var table = document.getElementById('numTable');
            table.innerHTML += '<input type = 'button' value = '테이블\"($j)\"' id = 'occupied'>'</script>";
        }
        else {
            echo "<script>var table = document.getElementById('numTable');
            table.innerHTML += '<input type = 'button' value = '테이블\"($j)\"' id = 'empty'>'</script>";
        }

        if ($j % 3 == 0) {
            echo "<script>var table = document.getElementById('numTable');
            table.innerHTML += '<br>'";
        }
    }
}

function showMenu() {
    global $conn;

    $sql = "SELECT * FROM Menu";
    if (!$result = mysqli_query($conn, $sql)) {
        echo "Menu query fail...\n";
    }

    while ($row = mysqli_fetch_assoc($result)) {
        echo "<script>var list = document.getElementById('menulist');
        list.innerHTML += '<table> <tr class = 'foodName'><td>.$row[Fname].</td></tr>
        <tr class = 'foodPrice'><td id = 'fprice'>.$row[Fprice].원 </td></tr></table>";
    }
}
?>