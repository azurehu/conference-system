<%@ page language="java" import="java.util.*,com.chinasofti.meeting.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common03.css"/>            
    </head>
    <body>
            <div class="page-content">
                <div class="content-nav">
                    人员管理 > 注册审批
                </div>
                <table class="listtable">
                    <caption>所有待审批注册信息：</caption>
                    
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="emp" items="${requestScope.employeesList}">
                    <tr>
                        <td>${emp.employeename}</td>
                        <td>${emp.username}</td>
                        <td>${emp.phone}</td>
                        <td>${ emp.email}</td>
                        <td>
                            <input type="button" class="clickbutton" value="通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=yes'"/>
                            <input type="button" class="clickbutton" value="不通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=no'"/>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
    
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>