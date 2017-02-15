<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Looking For Work/To Hire?</h1>
    </div>
    <div class="row text-center">
        <div class="border-right col-md-4 col-xs-6 col-sm-4 ">
            <img src="images/chooserole.png" class="img-responsive">
            <p>CHOOSE YOUR ROLE</p>
        </div>
        <div class="border-right col-md-4 col-xs-6 col-sm-4 ">
            <img src="images/preferences.png" class="img-responsive">
            <p>SET YOUR PREFERENCES</p>
        </div>
        <div class="col-md-4 col-xs-6 col-sm-4">
            <img src="images/findmatch.png" class="img-responsive">
            <p>RECEIVE YOUR MATCH</p>
        </div>
    </div>
    <div class="row text-center">
        <h2>Are you a Freelancer or an Employer?</h2>
        <div class="col-md-3 col-xs-12 col-sm-3 col-md-offset-3 col-sm-offset-3">
            <a class="selection btn btn-default" href="createaccount">Freelancer</a>
        </div>
        <div class="col-md-3 col-xs-12 col-sm-3">
            <a class="selection btn btn-default" href="createaccount">Employer</a>
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