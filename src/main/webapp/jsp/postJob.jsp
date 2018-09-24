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

.error{color:red;}

</style>

<html>
    <body>
        <h1>POST JOB</h1>
        <div align ="center"><form action="/minicare-1.0-SNAPSHOT/seeker/postjob.do">
            <table>
                <tr>
                    <td>Job Title : </td>
                    <td> <input type="text" name="jobtitle" value="${JobFormBean.jobTitle}"> </td>
                    <td class="error"> ${JobTitleError} </td>
                </tr>
                <tr>
                    <td>Start Date Time :</td>
                    <td> <input type="text" name="startdatetime" placeholder="YYYY-MM-DD HH:MM:SS" value="${JobFormBean.startDateTime}"> </td>
                    <td class="error"> ${StartDateTimeError} </td>
                </tr>
                <tr>
                    <td>End Date Time :</td>
                    <td> <input type="text" name="enddatetime" placeholder="YYYY-MM-DD HH:MM:SS" value="${JobFormBean.endDateTime}"> </td>
                    <td class="error"> ${EndDateTimeError} </td>
                </tr>
                <tr>
                    <td>Pay Per Hour :</td>
                    <td> <input type="text" name="payperhour" value="${JobFormBean.payPerHour}"> </td>
                    <td class="error"> ${PayPerHourError} </td>
                </tr>

            </table>
            <input type="submit" value="Submit">
        </form></div>
    </body>
</html>