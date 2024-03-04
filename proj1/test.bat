set JUNIT=.\dependencies\junit\4.13.2\junit-4.13.2.jar
set HAMCRESt_CORE=.\dependencies\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar
set FILE_PATH=./tax_calculator/src/main/java

javac -cp ".;%JUNIT%;%HAMCRESt_CORE%" ^
    %FILE_PATH%/Main.java ^
    %FILE_PATH%/taxCalculator/taxCalculator.java ^
    %FILE_PATH%/taxCalculator/Utils.java ^
    %FILE_PATH%/taxCalculator/TestTaxCalculator.java

java -cp "%FILE_PATH%;%JUNIT%;%HAMCRESt_CORE%" ^
    org.junit.runner.JUnitCore ^
    taxCalculator.TestTaxCalculator
