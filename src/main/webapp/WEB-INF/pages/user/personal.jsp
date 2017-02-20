<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="section">
        <h2>Set Your Personal Details</h2>
        <div class="row">
            <form:form modelAttribute="userPersonal" method="post">
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>First Name:</label>
                    <form:input path="firstname" id="firstname" class="form-control" placeholder="First Name"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Last Name:</label>
                    <form:input path="lastname" id="lastname" class="form-control" placeholder="Last Name" value="${currentUser.lastname}"/>
                </div>

                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Telephone No:</label>
                    <form:input path="telephone" id="telephone" class="form-control" placeholder="Telephone No" value="${currentUser.telephone}"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Email Address:</label>
                    <form:input path="emailaddress" id="emailaddress" class="form-control" placeholder="Email Address"
                                value="${currentUser.emailaddress}"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Address:</label>
                    <form:input path="address" id="address" class="form-control" placeholder="Address" value="${currentUser.address}"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Postcode:</label>
                    <form:input path="postcode" id="postcode" class="form-control" placeholder="Postcode" value="${currentUser.postcode}"/>
                </div>
                <div class="col-md-6 col-xs-12 col-sm-6">
                    <label>Town/City:</label>
                    <form:input path="towncity" id="towncity" class="form-control" placeholder="Town/City" value="${currentUser.towncity}"/>
                </div>
                <c:if test="${currentUser.role == 'Employer'}">
                    <div class="col-md-6 col-xs-12 col-sm-6">
                        <label>Company:</label>
                        <form:input path="company" id="company" class="form-control" placeholder="Company" value="${currentUser.company}"/>
                    </div>
                </c:if>
                <div class="submit col-md-12 col-xs-12 col-sm-12">
                    <input type="submit" class="btn btn-default" value="Confirm">
                </div>
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