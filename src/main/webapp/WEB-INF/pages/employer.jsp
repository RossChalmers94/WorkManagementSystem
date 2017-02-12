<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="section">
        <h2>Become an Employer..</h2>
        <div class="row">
            <form:form modelAttribute="newUser"  action="employerpreferences" method="post">
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>First Name:</label>
                    <form:input path="firstname" id="firstname" class="form-control" placeholder="First Name"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Last Name:</label>
                    <form:input path="lastname" id="lastname" class="form-control" placeholder="Last Name"/>
                </div>

                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Telephone No:</label>
                    <form:input path="telephone" id="telephone" class="form-control" placeholder="Telephone No"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Email Address:</label>
                    <form:input path="emailaddress" id="emailaddress" class="form-control" placeholder="Email Address"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Address:</label>
                    <form:input path="address" id="address" class="form-control" placeholder="Address"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Postcode:</label>
                    <form:input path="postcode" id="postcode" class="form-control" placeholder="Postcode"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Town/City:</label>
                    <form:input path="towncity" id="towncity" class="form-control" placeholder="Town/City"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Company:</label>
                    <form:input path="company" id="company" class="form-control" placeholder="Company"/>
                </div>
                <div class="submit col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Become an Employer" >
                </div>
            </form:form>
            <label>${newUser.username}</label>
            <label>${newUser.password}</label>
            <label>${newUser.role}</label>
            <label>${newUser.firstname}</label>
            <label>${newUser.lastname}</label>
            <label>${newUser.telephone}</label>
            <label>${newUser.emailaddress}</label>
            <label>${newUser.address}</label>
            <label>${newUser.postcode}</label>
            <label>${newUser.towncity}</label>
            <label>${newUser.postcode}</label>
            <label>${newUser.company}</label>
        </div>
    </div>
</div>

</body>
</html>