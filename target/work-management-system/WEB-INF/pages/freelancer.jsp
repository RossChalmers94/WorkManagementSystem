<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Become a Freelancer</h1>
    </div>
        <div class="row">
            <h3 class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-left">Personal Details</h3>
            <form:form method="post" modelAttribute="userPersonal">
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
                <div class="submit details col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Confirm">
                </div>

            </form:form>
        </div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>