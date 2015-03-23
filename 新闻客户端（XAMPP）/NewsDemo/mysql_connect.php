<?php
	$con = mysql_connect("localhost","root","");
	//设置字符集为utf-8
	mysql_query("SET NAMES 'utf8'");
	mysql_query("SET CHARACTER SET utf8");
	mysql_query("SET CHARACTER_SET_RESULT=utf8");
	if(!$con){
		die(mysql_error());
		echo "0000000000";
	}
	mysql_select_db("newsdome",$con);
?>