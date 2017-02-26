<%@ page language="java"
	import="java.util.*,com.chinasofti.meeting.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
<script type="text/javascript">
var xmlHttp;
//创建XMLHttpRequest对象		
        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function validate() {
        	//创建HttpXML对象
            createXMLHttpRequest(); 
        	//创建DOM对象，得到id为username的域
            var username = document.getElementById("username");
            var url = "ValidateUsernameServlet?username=" + escape(username.value);           
            //向服务器端的ValidateUsernameServlet 发送异步请求
            xmlHttp.open("GET", url, true);
            //当状态发生变化时，都会调用callback方法
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }

        function callback() {
        	//当客户端完全接收完服务器端的响应，并且状态为200，也就是正常
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                	//使用responseXML属性，接收服务器返回的XML文件，使用DOM解析XML	
                    var message = xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data;
                    var passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
                    setMessage(message, passed);
                }
            }
        }
        
        function setMessage(message, passed) {            
            var validateMessage = document.getElementById("validateMessage");
            var fontColor = "red";
            if (passed == "true") {
                fontColor = "green";                
            }
            validateMessage.innerHTML = "<font color=" + fontColor + ">" + message + " </font>";
        }
        
	/*function check() {
		if (form1.firstpassword.value!=form1.secondpassword.value) {
			confirminfo.innerHTML = "<font color=red>两次输入的密码不相符</font>";
		}else{
			confirminfo.innerHTML="<font color=green>两次输入的密码相符</font>";
		}
	}*/
	function check(){
		   var password = document.getElementById("firstpassword").value;
		   var repsword = document.getElementById("secondpassword").value;
		   if(password === ''){
		      alert('密码不能为空');
		      return false;
		   }
		   if(password != repsword) {
		      alert("两次密码不同，请重新输入");
		      return false;
		   }
		    
		}
</script>

</head>
<body>
	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问Cool-Meeting会议管理系统</div>
	</div>
	<div class="page-content">
		<div class="content-nav">人员管理 > 员工注册</div>
		<form name="form1" action="RegistServlet" method="post">
			<fieldset>
				<legend>员工信息</legend>

				<tr>
					<td>提示信息:</td>
					<td><font color='red'>${requestScope.msg}</font></td>
				</tr>

				<table class="formtable" style="width:50%">
					<tr>
						<td>姓名：</td>

						<td><input type="text" id="employeename" name="employeename"
							maxlength="20" value="${param.employeename}" ></td>
					</tr>
					<tr>
						<td>账户名：</td>

						<td><input type="text" id="username" name="username"
							maxlength="20" value="${param.username}" onchange="validate()">
							<div id="validateMessage"></div></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" id="firstpassword" name="password"
							maxlength="20" placeholder="请输入6位以上的密码">
						</td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" id="secondpassword"
							name="password" maxlength="20" onchange="check()" />
							<div id="confirminfo"></div></td>
					</tr>
					<tr>
						<td>联系电话：</td>

						<td><input type="text" id="phone" name="phone" maxlength="20"
							value="${param.phone}"></td>
					</tr>
					<tr>
						<td>电子邮件：</td>
						
						<td><input type="text" id="email" name="email" maxlength="20"
							value="${param.email}">
						</td>
					</tr>
					
					<tr>
					<td>所在部门：</td>
					<td>
					<select name="deptid">
							<c:forEach var="department" items="${requestScope.departmentsList}">							
					 			<c:if test="${department.departmentid== param.deptid}">
                                 <option value="${department.departmentid}" selected>${department.departmentname}</option>
                                </c:if>
                                <c:if test="${department.departmentid!= param.deptid}">
                                 <option value="${department.departmentid}">${department.departmentname}</option>
                                </c:if> 			
							</c:forEach>
					</select>
					</td>
					</tr>

					<tr>
						<td colspan="6" class="command">
						<input type="submit" class="clickbutton" value="注册" /> 
						<input type="reset"	class="clickbutton" value="重置" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>