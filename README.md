# Gatling Load Testing Project (Java Demo Automation Project)

This project is written in Java and utilizes the Gatling plugin for Gradle. For detailed usage, please refer to the [plugin documentation](https://docs.gatling.io/reference/integrations/build-tools/gradle-plugin/) on the Gatling website.<br/>

## Table of Contents
1. Getting Started<br/>
2. Project Overview<br/>
3. Project Structure<br/>
4. Running Simulations<br/>
5. Reviewing Reports<br/>

## 1. Getting Started
- Ensure you have Java JDK installed.<br/>
- Configure your `JAVA_HOME` environment variable.<br/>

## 2. Project Overview
This project is designed to automate performance testing using the Gatling framework. It provides a set of predefined simulation scripts to ensure the system performs as expected under load.<br/>

### Features
- **Gradle Wrapper**: This project includes the Gradle Wrapper, so there's no need to install Gradle manually. However, make sure you have a JDK installed and `$JAVA_HOME` configured.<br/>
- **Configuration File `build.gradle`**: The project uses a `build.gradle` file that leverages the Gradle wrapper.<br/>
- **Gatling Plugin**: The `3.10.5` version of the `io.gatling.gradle` plugin is applied for load testing.<br/>
- **Sample Simulation Class**: Includes a sample `SpaceSimulation` class to demonstrate Gatling's functionality. You can find more information on simulation classes in the [Gatling glossary](https://docs.gatling.io/reference/glossary/).<br/>

## 3. Project Structure
The typical Gradle project structure for use with the Gatling framework includes the following:<br/>
```
|——gradle-gatling-frmk
    |—-gradle/
    |—-build/
    |—-build.gradle
    |—-gradlew
    |—-gradlew.bat
    |—-settings.gradle
    |—-src
        |—-gatling/
            |—-java/
            |—-resources/
```
* `gradle/`: This directory typically contains Gradle wrapper files and settings required for building the project using Gradle.<br/>
* `build/`: This directory is where the build output, such as compiled classes, packaged jars, and build logs, are stored.<br/>
* `build.gradle`: This file is the configuration script for the Gradle build tool, defining tasks and dependencies for the project.<br/>
* `gradlew, gradlew.bat`: These are Gradle wrapper scripts that allow the project to be built without requiring a pre-installed Gradle distribution.<br/>
* `settings.gradle`: This file specifies settings for the Gradle build, such as project structure and included modules.<br/>
* `src/`: This directory typically contains the source code and resources for the project.<br/>
  * `gatling/`: This directory holds resources specific to Gatling performance testing framework.<br/>
    * `java/`: This directory might contain Java source code files for custom Gatling simulations.<br/>
    * `resources/`: This directory contains configuration files, test data, and other resources used in Gatling simulations.<br/>

## 4. Running Simulations
#### Gradle Command Terminal
The `simulations = { }` block in the `build.gradle` file is used to specify which Gatling simulation files should be included when running the command:<br/>

    > ./gradlew gatlingRun 
By configuring this block, you can define the set of simulations that Gatling will execute during performance testing.<br/>

## 5. Reviewing Reports
In Gatling, once you run your performance tests using the `./gradlew gatlingRun` command, the test results are generated in the `build/reports/gatling` directory. This directory contains various reports and logs that provide detailed insights into the performance of your application under load. Here is a breakdown of some important files and directories within `build/reports/gatling`:<br/>
* `<simulation-name>/index.html`: This is the main HTML report file for a specific simulation, providing an overview of the test results, including charts, statistics, and metrics.<br/>
* `global/index.html`: This HTML report aggregates results from all simulations, giving an overall summary of the performance tests conducted.<br/>
* `js/`: This directory contains JavaScript files used to render interactive charts and graphs in the HTML reports.<br/>
* `results/`: Contains detailed data files (.log, .json) with raw performance metrics collected during the test execution.<br/>
* `simulation.log`: A log file that records detailed information about the simulation run, including request responses, errors, and debug information.<br/>

When reviewing reports in Gatling, you can analyze key performance indicators such as response times, throughput, error rates, and other metrics to assess the performance of your system under the defined load. The visual representations provided in the HTML reports make it easier to identify performance bottlenecks, trends, and areas for improvement in your application.<br/>