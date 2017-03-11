<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>No Match</h1>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2>Unfortunately, our application has been unable to find you a match.</h2>
            <h2>You can alter your preferences or you can leave them as they are and we will try to allocate you a match
            in the near future.</h2>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 col-md-offset-4 col-lg-offset-4">
                <a class="selection btn btn-default" href="preferencesdetails">Edit Preferences</a>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <a class="selection btn btn-default" href="userhome">Home Page</a>
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