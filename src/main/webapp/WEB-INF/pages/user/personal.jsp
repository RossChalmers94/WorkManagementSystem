<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="section">
        <div class="row">
            <h1>Set Your Personal Details</h1>
        </div>
        <div class="row">
            <form:form modelAttribute="userPersonal" method="post">
                <c:if test="${error == true}">
                    <div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <label>There has been an error.</label>
                    </div>
                </c:if>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>First Name:</label>
                    <form:input path="firstname" id="firstname" class="form-control" placeholder="First Name"/>
                    <form:errors path="firstname" class="alert-danger"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Last Name:</label>
                    <form:input path="lastname" id="lastname" class="form-control" placeholder="Last Name"/>
                    <form:errors path="lastname" class="alert-danger"/>
                </div>

                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Telephone No:</label>
                    <form:input path="telephone" id="telephone" class="form-control" placeholder="Telephone No"/>
                    <form:errors path="telephone" class="alert-danger"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Email Address:</label>
                    <form:input path="emailaddress" id="emailaddress" class="form-control" placeholder="Email Address"/>
                    <form:errors path="emailaddress" class="alert-danger"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Address:</label>
                    <form:input path="address" id="address" class="form-control" placeholder="Address"/>
                    <form:errors path="address" class="alert-danger"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Postcode:</label>
                    <form:input path="postcode" id="postcode" class="form-control" placeholder="Postcode"/>
                    <form:errors path="postcode" class="alert-danger"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Town/City:</label>
                    <form:input path="towncity" id="towncity" class="form-control" placeholder="Town/City"/>
                    <form:errors path="towncity" class="alert-danger"/>
                </div>
                <c:if test="${currentUser.role == 'Employer'}">
                    <div class="col-md-6 col-xs-12 col-sm-6">
                        <label>Company:</label>
                        <form:input path="company" id="company" class="form-control" placeholder="Company"/>
                        <form:errors path="company" class="alert-danger"/>
                    </div>
                <div class="submit col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Confirm Employer" name="employer">
                    <a class="contact btn btn-default" href="userhome">Back</a>
                </div>
                </c:if>
                <c:if test="${currentUser.role == 'Freelancer'}">
                <div class="submit col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Confirm Freelancer" name="freelancer">
                    <a class="contact btn btn-default" href="userhome">Back</a>
                </div>
                </c:if>
            </form:form>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>

</body>
</html>