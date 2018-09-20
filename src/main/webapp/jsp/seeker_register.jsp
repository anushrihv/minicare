<style>
    .error{
        color:red;
    }
</style>

<html>
    <body>
        <form action="/minicare-1.0-SNAPSHOT/visitor/register.do" method="post">

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
                     <td> Seeker : </td>
                     <td> <input type="radio" name="type" value="Seeker" checked > </td>

                </tr>
            </table>
            <input type="submit" value="Submit">

        </form>
    </body>
</html>