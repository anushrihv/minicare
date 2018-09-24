<%@page import="java.util.*,com.minicare.model.JobModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: center;
    border-bottom: 1px solid #ddd;
}
h1{
text-align:center
}
</style>

<html>
    <body>
        <h1>LIST OF JOBS</h1>
        <table>
            <tr>
                <th> JOB TITLE </th>
                <th> STATUS </th>
                <th> START DATE TIME </th>
                <th> END DATE TIME </th>
                <th> PAY PER HOUR </th>
            </tr>
            <c:forEach items="${JobList}" var="Job">
            <tr>
                <td><c:out value="${Job.jobTitle}" /></td>
                <td><c:out value="${Job.status}" /></td>
                <td><c:out value="${Job.startDateTime}" /></td>
                <td><c:out value="${Job.endDateTime}" /></td>
                <td><c:out value="${Job.payPerHour}" /></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>