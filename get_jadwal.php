<?php
header("Access-Control-Allow-Origin: *");
$arr = null;
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "anmp_160420048_uts";
$conn = new mysqli($servername, $username, $password, $dbname);

// $conn =  new mysqli("localhost", "root","","native");
if ($conn->connect_errno) {
    die(json_encode(array('result' => 'ERROR', 'message' => 'Failed to connect DB'.$conn->connect_error)));
}
    // $conn->set_charset("UTF8");
    if(isset($_GET['jadwal'])){
        $id = $_GET['jadwal'];
        $sql = "SELECT * FROM `jadwal` WHERE jadwal.jadwal_id = $id";
        $result = $conn->query($sql);
        $array = array();
        if ($result->num_rows > 0) {
            while ($obj = $result->fetch_object()) {
                echo json_encode($obj);
            }
            
        }
    }
    
    
    
    
