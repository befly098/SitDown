<?php
include("./observer.php");

$host = "localhost";
$user = "root";
$password = "123456";
$dbName = $_GET['sname'];
$tables = array();

$conn = new mysqli($host, $user, $password, $dbName);
if (!$conn) {
    echo "mysql 연결실패\n";
}

$noty = new Publisher('NotificationPublisher');
$email = new Observer('EmailObserver', 50);
$slack = new Observer('SlackObserver', 10);
$dashboard = new Observer('DashboardObserver', 30);

$noty->attach($email);
$noty->attach($slack);
$noty->attach($dashboard);

$noty->setEvent("");

function totalTable() {
    global $conn, $tables;

    $sql = "SELECT * FROM Otable WHERE Tnum = -1";
    $result = mysqli_query($conn, $sql);
    
    $row = mysqli_fetch_assoc($result);
    if ($row[Tquant] != count($tables)) {
        $bound = count($tables);
        if ($row[Tquant] > count($tables)) {
            for ($i = 0; $i < $row[Tquant] - $bound; $i++) {
                array_push($tables, '-1');
            }
        }
        else {
            for ($i = 0; $i < $bound - $row[Tquant]; $i++) {
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

    $sql = "SELECT * FROM Otable WHERE Tnum > -1";
    if (!$result = mysqli_query($conn, $sql)) {
        echo "Otable query fail...\n";
    }

    while ($row = mysqli_fetch_assoc($result)) {
        $tables[$row[Tnum]] = 1;
    }

    echo "<div align = 'center'>";
    for ($i = 0; $i < count($tables); $i++) {
        $j = $i + 1;
        if ($tables[$i] == 1) {
            echo "<input type = 'button' value = '테이블$j' id = 'occupied'>&nbsp;";
        }
        else {
            echo "<input type = 'button' value = '테이블$j' id = 'empty'>&nbsp;";
        }

        if ($j % 4 == 0) {
            echo "<br><br>";
        }
    }
    echo "</div>";
}

function showMenu() {
    global $conn;
    $cnt = 0;

    $sql = "SELECT * FROM Menu";
    if (!$result = mysqli_query($conn, $sql)) {
        echo "Menu query fail...\n";
    }

    while ($row = mysqli_fetch_assoc($result)) {
        if ($cnt % 2 == 0) {
            echo "<div id = 'left'>";
            echo "<table> <tr><td id = 'foodName'>$row[Fname]</td></tr>
            <tr><td id = 'foodPrice'>$row[Fprice]원 </td></tr></table>";
            echo "</div>";
        }
        else {
            echo "<div id = 'right'>";
            echo "<table> <tr><td id = 'foodName'>$row[Fname]</td></tr>
            <tr><td id = 'foodPrice'>$row[Fprice]원 </td></tr></table>";
            echo "</div><br>";
        }
        $cnt++;
    }
}
?>