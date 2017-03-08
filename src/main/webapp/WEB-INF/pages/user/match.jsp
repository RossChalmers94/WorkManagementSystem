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
            ${match.userMatch.firstname}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Last Name:</label>
            ${match.userMatch.lastname}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Contact Telephone No:</label>
            ${match.userMatch.telephone}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Email Address:</label>
            ${match.userMatch.emailaddress}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Address:</label>
            ${match.userMatch.address}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Postcode:</label>
            ${match.userMatch.postcode}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Town/City:</label>
            ${match.userMatch.towncity}
        </div>
        <c:if test="${currentUser.role == 'Freelancer'}">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Company:</label>
            ${match.userMatch.company}
        </div>
        </c:if>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2 class="text-left">Preferences</h2>
        </div>
        <c:if test="${currentUser.role == 'Freelancer'}">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Job Title:</label>
            ${match.jobTitle}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Job Description:</label>
            ${match.jobDescription}
        </div>
        </c:if>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Skills:</label>
            ${match.skills}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Location:</label>
            ${match.location}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Salary (£):</label>
            ${match.salary}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Maximum Time to Complete Work:</label>
            ${match.jobLength}
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <label>Previous Rating:</label>
            ${match.previousRating}
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <a class="contact btn btn-default" href="completematch">Complete Match</a>
            <a class="contact btn btn-default" href="mailto:${userMatch.emailaddress}">Contact</a>
            <a class="contact btn btn-default" href="userhome">Back</a>
        </div>
    </div>
</div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>