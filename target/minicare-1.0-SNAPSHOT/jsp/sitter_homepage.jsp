<style>
 input[type=submit] {
    background-color: #555555; /*black*/
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 12px;
}
h1{
text-align : center;
}

.right{
float : right ;
}
</style>


<html>
    <body>
        <h1>Home Page</h1>
        <form action="/minicare-1.0-SNAPSHOT/sitter/showjob.do "><input type="submit" value="SHOW JOB"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/showjob.do " class="right"><input type="submit" value="CLOSE ACCOUNT"></form>
        <form action="/minicare-1.0-SNAPSHOT/sitter/listjobapplications.do "><input type="submit" value="JOB APPLICATIONS"></form>

    </body>
</html>
