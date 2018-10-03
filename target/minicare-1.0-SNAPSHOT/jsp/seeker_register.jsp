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
                    <td><input type="text" name="firstname" value="${SeekerFormBean.firstname}"></td>
                    <td class="error"> ${FirstNameError} </td>
                </tr>
                <tr>
                    <td>Last Name :</td>
                    <td><input type="text" name="lastname" value="${SeekerFormBean.lastname}"></td>
                    <td class="error"> ${LastNameError} </td>
                </tr>
                <tr>
                    <td>Phone Number : </td>
                    <td> <input type="text" name="phonenumber" value="${SeekerFormBean.phonenumber}"></td>
                    <td class="error"> ${PhoneNumberError} </td>
                </tr>
                <tr>
                    <td> Email : </td>
                    <td> <input type="text" name="email" value="${SeekerFormBean.email}"> </td>
                    <td class="error"> ${EmailError} </td>
                </tr>
                <tr>
                    <td> Address : </td>
                    <td> <textarea rows="4" cols="30" name="address" >${SeekerFormBean.address.trim()}</textarea> </td>
                    <td class="error"> ${AddressError} </td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td> <input type="password" name="password" value="${SeekerFormBean.password}"> </td>
                    <td class="error"> ${PasswordError} </td>
                </tr>
                <tr>
                    <td> Re enter password </td>
                    <td> <input type="password" name="password2" value="${SeekerFormBean.password2}"> </td>
                    <td class="error"> ${Password2Error} </td>
                </tr>
                <tr>
                    <td> Number of Children : </td>
                    <td> <input type="text" name="numberofchildren" value="${SeekerFormBean.numberOfChildren}"> </td>
                    <td class="error"> ${NumberOfChildrenError} </td>
                </tr>
                <tr>
                    <td> Spouse Name : </td>
                    <td> <input type="text" name="spousename" value="${SeekerFormBean.spouseName}"></td>
                    <td class="error"> ${SpouseNameError} </td>
                </tr>
                <tr>

                     <td> <input type="hidden" name="type" value="Seeker" checked > </td>

                </tr>
            </table>
            <input type="submit" value="Submit">

        </form></div>
        <div align="center"><form action="/minicare-1.0-SNAPSHOT/" > <input type="submit" value="BACK" > </form></div>
    </body>
</html>