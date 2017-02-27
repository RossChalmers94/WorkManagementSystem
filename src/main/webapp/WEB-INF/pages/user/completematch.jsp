<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp" %>

<div class="container">
    <div class="row">
        <h1>Complete Match</h1>
    </div>
    <form:form modelAttribute="completeMatch" method="post">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h2>We hope you were satisfied by your match.</h2>
        </div>
        <div class="row text-center">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-center">What was your experience with your match?</h3>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 col-md-offset-1 col-lg-offset-1">
                <label class="text-center radio-inline"><form:radiobutton path="rating" value="1"/>Poor</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="rating" value="2"/>Below Average</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="rating" value="3"/>Average</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="rating" value="4"/>Good</label>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <label class="text-center radio-inline"><form:radiobutton path="rating" value="5"/>Excellent</label>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 col-md-offset-4 col-lg-offset-4">
                <input type="submit" class="btn btn-default" value="Complete Match">
                <a class="contact btn btn-default" href="match">Back</a>
            </div>
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