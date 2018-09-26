<style>
.error{
color:red;
}
</style>

<html>
    <body>
        <h1 align="center">Welcome to Mini-Care</h1>
        <br><br><br><br>
        <div align="center"><form action="/minicare-1.0-SNAPSHOT/visitor/login.do" method="post">
            <p style="color:green"> ${Message} </p>
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
        </form></div>

        <br><br><br><br>


        <div align="center"><h3> New User :</h3>
        <a href="/minicare-1.0-SNAPSHOT/jsp/select_member.jsp"> Register Here</a>
        </div>
    </body>
</html>

