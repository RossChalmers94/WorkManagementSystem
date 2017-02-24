<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../header.jsp"%>

<div class="container">
    <div class="row">
        <h1>Employer</h1>
        <h2>Set Your Preferences</h2>
    </div>
    <form>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h3 class="text-left">Job Title</h3>
                <input type="text" class="form-control" placeholder="Job Title">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h3 class="text-left">Job Description</h3>
                <textarea class="form-control" rows="10"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h3 class="text-left">Skills</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h3 class="text-left">Location</h3>
                <select class="form-control">
                    <option selected>Location</option>
                    <option>Aberdeen</option>
                    <option>Armagh</option>
                    <option>Bath</option>
                    <option>Bangor</option>
                    <option>Belfast</option>
                    <option>Birmingham</option>
                    <option>Bradford</option>
                    <option>Brighton</option>
                    <option>Bristol</option>
                    <option>Cambridge</option>
                    <option>Canterbury</option>
                    <option>Cardiff</option>
                    <option>Carlisle</option>
                    <option>Chester</option>
                    <option>Chichester</option>
                    <option>Coventry</option>
                    <option>Derby</option>
                    <option>Dundee</option>
                    <option>Durham</option>
                    <option>Edinburgh</option>
                    <option>Ely</option>
                    <option>Exeter</option>
                    <option>Glasgow</option>
                    <option>Gloucester</option>
                    <option>Hereford</option>
                    <option>Hull</option>
                    <option>Inverness</option>
                    <option>Lancaster</option>
                    <option>Leeds</option>
                    <option>Leicester</option>
                    <option>Lichfield</option>
                    <option>Lincoln</option>
                    <option>Lisburn</option>
                    <option>Liverpool</option>
                    <option>London</option>
                    <option>Londonderry</option>
                    <option>Manchester</option>
                    <option>Newcastle</option>
                    <option>Newport</option>
                    <option>Newry</option>
                    <option>Norwich</option>
                    <option>Nottingham</option>
                    <option>Oxford</option>
                    <option>Peterborough</option>
                    <option>Plymouth</option>
                    <option>Portsmouth</option>
                    <option>Preston</option>
                    <option>Ripon</option>
                    <option>Salford</option>
                    <option>Salisbury</option>
                    <option>Sheffield</option>
                    <option>Southampton</option>
                    <option>St Albans</option>
                    <option>St Davids</option>
                    <option>Stirling</option>
                    <option>Stoke-on-Trent</option>
                    <option>Sunderland</option>
                    <option>Swansea</option>
                    <option>Truro</option>
                    <option>Wakefield</option>
                    <option>Wells</option>
                    <option>Westminster</option>
                    <option>Winchester</option>
                    <option>Wolverhampton</option>
                    <option>Worcester</option>
                    <option>York</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-left">Salary (£)</h3>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="text-center radio-inline"><input type="radio"
                                                                   name="salarychoice" id="0-10000" value="0-10000">£10,000
                        or below</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="salarychoice" id="10000-20000" value="10000-20000">£10,000
                        - £20,000</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="salarychoice" id="20000-30000" value="20000-30000">£20,000
                        - £30,000</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="salarychoice" id="30000-40000" value="30000-40000">£30,000
                        - £40,000</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="salarychoice" id="40000above" value="40000">£40,000
                        +</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-left">Maximum Time to Complete Work</h3>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="text-center radio-inline"><input type="radio"
                                                                   name="timechoice" id="three" value="3Months">3 Months
                        or
                        below</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="timechoice" id="3-6Months" value="3-6Months">3 - 6
                        Months</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="timechoice" id="6-9Months" value="6-9Months">6 - 9
                        Months</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="timechoice" id="9-12Months" value="9-12Months">9 - 12
                        Months</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="timechoice" id="12Months" value="12Months">12 Months
                        +</label>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-left">Freelancer Rating (Minimum Rating)</h3>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="text-center radio-inline"><input type="radio"
                                                                   name="rating" id="poor" value="poor">Poor</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="belowaverage" value="belowaverage">Below Average</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="average" value="average">Average</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="good" value="good">Good</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="verygood" value="verygood">Very Good</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-left">Relax Preferences After</h3>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="text-center radio-inline"><input type="radio"
                                                                   name="preferences" id="never" value="never">Never</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="1week" value="1week">1 Week</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="2week" value="2week">2 Weeks</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="3week" value="3week">3 Weeks</label>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-2 col-lg-2">
                    <label class="radio-inline"><input type="radio"
                                                       name="rating" id="4week" value="4week">4 Weeks</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <input type="submit" class="btn btn-default" value="Confirm">
            </div>
        </div>
    </form>
</div>
</div>
</body>
<footer class="footer">
    <div class="container">
        Work Management System
    </div>
</footer>
</html>