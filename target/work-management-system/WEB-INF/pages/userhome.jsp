<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>What would you like to do?</h1>
    </div>
    <div class="row text-center">
        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-md-offset-1 col-lg-offset-1">
            <a class="selection btn btn-default" href="personal">Edit Personal Details</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 ">
            <a class="selection btn btn-default" href="freelancerpreferences">Edit Preferences</a>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
            <a class="selection btn btn-default" href="match">View Match</a>
        </div>
    </div>
    ${currentUser.username}
</div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>