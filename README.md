# Diagnosis and Treatment of Cucumber Diseases

Java implementation of the Cucumber Diseases workshop

## Installation
For the workshop Java 17 or later is a pre-requisite for building und runnnig the Cucumber features. It needs to be installed before the installation can begin.

### Local

* Clone the repository localy
* Import the repository in your favourite IDE and configure the Maven build
* Install a Cucumber extension or plugin in you IDE
   * IntelliJ IDEA: https://www.jetbrains.com/help/idea/cucumber-support.html
   * VS Code: https://marketplace.visualstudio.com/items?itemName=CucumberOpen.cucumber-official

### Platform notes
The VC Code Cucumber extension fails on any Unix distribution. It's recommended to use VS Code on Windows.

Without the Cucumber extension there are some restrictions:
* You won't have Gherkin support
* You can't generate step definition
* There is NO linkage between your feature files and step definintion implementation
* You are still able to edit all file and execute your Unit Tests.

### gitpod.io
Since the VC Code Cucumber plugin fails on any Unix distribution, it's not recommeneded to use gitpod.io. 

<a href="https://gitpod.io/#https://github.com/rolger/cucumber-diseases-java" target="_blank"> 
<img src="https://gitpod.io/button/open-in-gitpod.svg" alt="Open in Gitpod">
</a>



1. [Login to GitHub](https://github.com/login) or [create an account](https://github.com/join).

2. [Create a new workspace at Gitpod](https://gitpod.io/new/#https://github.com/Nagarro-Technical-Coaching-Workshops/cucumber-diseases).
   This usually takes a little while, just be patient.

3. Open  from the file tree on the left and wait for the Java installation to finish.

   A box will appear on the bottom right, saying _Opening Java Projects_, you can click on _check details_ to follow the installation progress.

4. After the installation is done, you can right-click into the code, and select _Run test at Cursor_ (to run a single test) or _Run Tests in Current File_ (to run all tests).


