<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Your Match</h1>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Personal Details</h2>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>First Name:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Last Name:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Contact Telephone No:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Email Address:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Address:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Postcode:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Town/City:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Company:</label>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Preferences</h2>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Job Title:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Job Description:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Skills:</label>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Location:</label>
            ${worker.location}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Salary (£):</label>
            ${worker.salary}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Maximum Time to Complete Work:</label>
            ${worker.jobLength}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Previous Rating:</label>
            ${worker.rating}
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <a class="contact btn btn-default" href="mailto:${match.emailaddress}">Contact</a>
            <a class="contact btn btn-default" href="userhome">Back</a>
        </div>
    </div>
    ${worker.compareValue}
</div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>