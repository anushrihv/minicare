<style>
.error{
color:red;
}
</style>

<html>
    <body>
        <h1>Welcome to Mini-Care</h1>
        <br><br><br><br>
        <form action="/minicare-1.0-SNAPSHOT/visitor/login.do" method="post">
            <h2>Login here</h2>
            <table>
                <tr>
                <td> <input type="text" name="loginemail" placeholder="Email" value="${LoginFormBean.email}"> </td>
                <td class="error"> ${LoginEmailError} </td>
                </tr>
                <tr>
                    <td> <input type="password" name="loginpassword"  placeholder="Password" value="${LoginFormBean.password}"> </td>
                    <td class="error"> ${LoginPasswordError} </td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>

            <br><br><br><br>


            <h3> New User :</h3>
            <a href="/minicare-1.0-SNAPSHOT/jsp/select_member.jsp"> Register Here</a>
    </body>
</html>

