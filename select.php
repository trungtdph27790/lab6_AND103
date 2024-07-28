<?php
$res=array();
$s="localhost"; $u="root"; $p=""; $db="lab5";
$con=new mysqli($s,$u,$p,$db);
$sql="SELECT * FROM sanpham";
$result=$con->query($sql);//doc du lieu va tra ve result
if($result->num_rows>0){//neu so dong >0
    $res['products']=array();
    while($row=$result->fetch_assoc()){//doc tung dong
        $product=array();
        $product['MaSp']=$row['MaSp'];
        $product['TenSp']=$row['TenSp'];
        $product['Mota']=$row['Mota'];
        array_push($res['products'],$product);//day du lieu doc duoc vao mang
    }
    $res['success']=1;
    $res['message']="select thanh cong";
    echo json_encode($res);
}
else{
    $res['success']=0;
    $res['message']="khong co du lieu";
    echo json_encode($res);
}
$con->close();