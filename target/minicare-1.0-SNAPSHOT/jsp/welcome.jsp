<style>
.error{
color:red;
}

button {
    background-color: #555555; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 12px;
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

        <div align="center">
           <form>
               <button type="submit" name="seeker" formaction="/minicare-1.0-SNAPSHOT/jsp/sitter_register.jsp" >Register as Sitter </button>
               <button type="submit" name="sitter" formaction="/minicare-1.0-SNAPSHOT/jsp/seeker_register.jsp" >Register as Seeker </button>
            </form>
        </div>


    </body>

</html>

