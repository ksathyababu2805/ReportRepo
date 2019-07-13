call mvn clean test -Dcucumber.options="--tags @%1"

call mvn site
call start "" "D:\Krishnendu\SOA_UI_TestingFramework_Cucumber_Maven_Junit\SOA_UI_TestingFramework\target\site\allure-maven-plugin\index.html"
