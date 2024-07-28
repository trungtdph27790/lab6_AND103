<?php
$res=array();
$s="localhost"; $u="root"; $p=""; $db="lab5";
$con=new mysqli($s,$u,$p,$db);
$MaSP=$_POST['MaSp'];//truyen tham so cho bien MaSP
$TenSP=$_POST['TenSp'];
$MoTa=$_POST['Mota'];
$sql="UPDATE sanpham SET TenSp='$TenSP', Mota='$MoTa' WHERE MaSp='$MaSP'";
if($con->query($sql)===TRUE){
    $res['success']=1;
    $res['message']="update thanh cong";
    echo json_encode($res);
}
else{
    $res['success']=0;
    $res['message']="update that bai";
    echo json_encode($res);
}
$con->close();
