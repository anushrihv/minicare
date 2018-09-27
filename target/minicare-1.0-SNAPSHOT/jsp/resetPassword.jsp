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
</style>

<html>
    <div align="center">
        <form action="/minicare-1.0-SNAPSHOT/member/resetpassword.do" method="post">
            <table>
                <tr>
                    <td>Enter old password</td>
                    <td> <input type="password" name="oldpassword" value="${OldPassword}" required> </td>
                    <td class="error"> ${OldPasswordError} </td>
                </tr>
                <tr>
                    <td>Enter new password</td>
                    <td> <input type="password" name="newpassword" value="${NewPassword}" required> </td>
                </tr>
                <tr>
                    <td>Re-enter new password</td>
                    <td> <input type="password" name="newpassword2" value="${NewPassword2}" required> </td>
                    <td class="error"> ${NewPasswordError} </td>
                </tr>
                <tr>
                <td><input type="submit" value="Reset Password"></td>
                </tr>
            </table>

        </form>
    </div>
</html>