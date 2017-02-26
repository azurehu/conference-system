<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>无标题文档</title>
 <link rel="stylesheet" href="styles/common.css"/>
</head>
<body>
  <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
              欢迎您，<strong>${sessionScope.employeename}</strong>
       <a href="#">[修改密码]</a>
            </div>
             <div class="header-quicklink">
            目前网站访问次数：<font color='red'>${applicationScope.visitcount}</font>
            </div>
        </div>
</body>
</html>
