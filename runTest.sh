mvn clean test -Dcucumber.options="--tags @$1" -DEnv=$2
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo
echo
echo ":::::::::::::::::TEST COMPLETED AND ALL CASES PASSED AND GENERATING TEST REPORT:::::::::::::::::"
echo
else
echo
echo ":::::::::::::::::TEST COMPLETED WITH FAILURES AND GENERATING TEST REPORT::::::::::::::::::::::::"
echo
fi
mvn site
echo
echo ":::::::::::::::::REPORT GENERATION COMPLETED::::::::::::::::::::::::::::::::::::::::::::::::::::"
echo
echo ":::::::::::::::::GATHERING REPORT DATA FOR EMAIL NOTIFICATION:::::::::::::::::::::::::::::::::::"
echo
sleep 5 
mkdir -p Reports/TestReport_$(date +"%Y%m%d_%H%M%S") && cp -a target/site/allure-maven-plugin/* $_
echo ":::::::::::::::::REPORT ARCHIVED::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
echo
echo ":::::::::::::::::PROCESS COMPLETED > PLEASE CHECK REPORTS:::::::::::::::::::::::::::::::::::::::"
echo
open -a firefox.app target/site/allure-maven-plugin/index.html