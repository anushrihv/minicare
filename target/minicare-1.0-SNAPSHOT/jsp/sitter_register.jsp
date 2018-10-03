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

        .error{
        color:red;
        }

</style>

<html>
    <body>
    <h1 style="text-align:center">Register here</h1>
        <div align="center"><form action="/minicare-1.0-SNAPSHOT/visitor/register.do" method="post">
            <table>
                <tr>
                    <td>First Name :</td>
                    <td><input type="text" name="firstname" value="${SitterFormBean.firstname}"></td>
                    <td class="error"> ${FirstNameError} </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><input type="text" name="lastname" value="${SitterFormBean.lastname}"></td>
                    <td class="error"> ${LastNameError} </td>
                </tr>
                <tr>
                    <td>Phone Number : </td>
                    <td> <input type="text" name="phonenumber" value="${SitterFormBean.phonenumber}"></td>
                    <td class="error"> ${PhoneNumberError} </td>
                </tr>
                <tr>
                    <td> Email : </td>
                    <td> <input type="text" name="email" value="${SitterFormBean.email}"> </td>
                    <td class="error"> ${EmailError} </td>
                </tr>
                <tr>
                    <td> Address : </td>
                    <td> <textarea rows="4" cols="30" name="address" >${SitterFormBean.address.trim()}</textarea> </td>
                    <td class="error"> ${AddressError} </td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td> <input type="password" name="password" value="${SitterFormBean.password}"> </td>
                    <td class="error"> ${PasswordError} </td>
                </tr>
                <tr>
                    <td> Re enter password </td>
                    <td> <input type="password" name="password2" value="${SitterFormBean.password2}"> </td>
                    <td class="error"> ${Password2Error} </td>
                </tr>
                <tr>
                    <td> Years Of Experience : </td>
                    <td> <input type="text" name="yearsofexperience" value="${SitterFormBean.yearsOfExperience}"> </td>
                    <td class="error"> ${YearsOfExperienceError} </td>
                </tr>
                <tr>
                    <td> Expected Pay : </td>
                    <td> <input type="text" name="expectedpay" value="${SitterFormBean.expectedPay}"></td>
                    <td class="error"> ${ExpectedPayError} </td>
                </tr>
                <tr>

                     <td> <input type="hidden" name="type" value="Sitter" checked > </td>
                </tr>
            </table>
            <input type="submit" value="Submit">

        </form></div>
        <div align="center"><form action="/minicare-1.0-SNAPSHOT/" > <input type="submit" value="BACK" > </form></div>
    </body>
</html>