<%@page isErrorPage="true" %>

<html>
    <body>
        <h1 style="color:red">ERROR OCCURRED WHILE LOADING THIS PAGE</h1>
        <p>This site is unavailable to render the service you requested . A bug in the system has caused the error to occur .</p>
        <%= exception.getMessage() %><p> is listed as cause of the error .</p>
    </body>
</html>