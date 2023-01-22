<%--
  Created by IntelliJ IDEA.
  User: 20228428
  Date: 08.01.2023
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>

<form name="signUpForm" action="signUp" method="POST">
    <table>
        <tr>
            <td>Your First name :</td>
            <td><label>
                <input type="text" name="firstName" required/>
            </label></td>
        </tr>
        <tr>
            <td>Your Last name :</td>
            <td><label>
                <input type="text" name="lastName" required/>
            </label></td>
        </tr>
        <tr>
            <td>Your Phone number :</td>
            <td><label>
                <input type="tel" name="phoneNumber" required/>
            </label></td>
        </tr>
        <tr>
            <td>Your Email :</td>
            <td><label>
                <input type="email" name="email" required/>
            </label></td>
        </tr>
        <tr>
            <td>Your Password :</td>
            <td><label>
                <input type="password" name="password" required/>
            </label></td>
        </tr>
        <th><input type="submit" value="Submit" name="find"/></th>
        <th><input type="reset" value="Reset" name="reset" /></th>
    </table>
</form>

</body>
</html>
