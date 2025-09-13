![Java](https://img.shields.io/badge/java-white?style=for-the-badge&logo=openjdk&logoSize=auto&color=%23e69138&cacheSeconds=3600&link=https%3A%2F%2Fwww.oracle.com%2Fjava%2F)
![Gradle](https://img.shields.io/badge/gradle-info?style=for-the-badge&logo=gradle&logoSize=auto&color=%23a64d79&cacheSeconds=3600&link=https%3A%2F%2Fdocs.gradle.org%2Fcurrent%2Fuserguide%2Fuserguide.html)
![Gatling](https://img.shields.io/badge/gatling-info?style=for-the-badge&logo=gatling&logoSize=auto&color=%23fce5cd&cacheSeconds=3600&link=https%3A%2F%2Fdocs.gatling.io)

#  API Load Automation Demo Project: Gatling
This project is written in Java and utilizes the Gatling plugin for Gradle. For detailed usage, please refer to the [plugin documentation](https://docs.gatling.io/reference/integrations/build-tools/gradle-plugin/) on the Gatling website.<br/>

## Table of Contents
1. [Getting Started](#one)
2. [Project Overview](#two)
   * 2.1. [Project Structure](#two-one)
3. [Running Simulations](#three)
4. [Reviewing Reports](#four)
5. [Example Load Testing Scenario: performed for ClickUp](#five)

<a id="one"></a>
## 1. Getting Started
- Ensure you have Java JDK installed.<br/>
- Configure your `JAVA_HOME` environment variable.<br/>

<a id="two"></a>
## 2. Project Overview
This project is designed to automate performance testing using the Gatling framework. It provides a set of predefined simulation scripts to ensure the system performs as expected under load.<br/>

#### Features:
- **Gradle Wrapper**: This project includes the Gradle Wrapper, so there's no need to install Gradle manually. However, make sure you have a JDK installed and `$JAVA_HOME` configured.<br/>
- **Configuration File `build.gradle`**: The project uses a `build.gradle` file that leverages the Gradle wrapper.<br/>
- **Gatling Plugin**: The `3.10.5` version of the `io.gatling.gradle` plugin is applied for load testing.<br/>
- **Sample Simulation Class**: Includes a sample `SpaceSimulation` class to demonstrate Gatling's functionality. You can find more information on simulation classes in the [Gatling glossary](https://docs.gatling.io/reference/glossary/).<br/>

<a id="two-one"></a>
### 2.1. Project Structure
The typical Gradle project structure for use with the Gatling framework includes the following:<br/>
```text
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
                |—-config/
                    |—-local.yaml
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

<a id="three"></a>
## 3. Running Simulations
Before running simulations, please create a hidden `local.yaml` file using the following structure:<br/>
```yaml
api:
    url: 'https://api.clickup.com/'
    token: ‘{token}’

workspace:
    username: ‘{username}’
```

> **Note:**<br/>
> * Replace `{token}` with your personal token.<br/>
> * Replace `{username}` with your username used during authorization.<br/>
> To obtain your personal token, please refer to the [helper](https://help.clickup.com/hc/en-us/articles/6303426241687-Use-the-ClickUp-API).<br/>

#### Gradle Command Terminal:
The `simulations = { }` block in the `build.gradle` file is used to specify which Gatling simulation files should be included when running the command:<br/>
```bash
./gradlew gatlingRun
```
By configuring this block, you can define the set of simulations that Gatling will execute during performance testing.<br/>

<a id="four"></a>
## 4. Reviewing Reports
In Gatling, once you run your performance tests using the `./gradlew gatlingRun` command, the test results are generated in the `build/reports/gatling` directory. This directory contains various reports and logs that provide detailed insights into the performance of your application under load. Here is a breakdown of some important files and directories within `build/reports/gatling`:<br/>
* `<simulation-name>/index.html`: This is the main HTML report file for a specific simulation, providing an overview of the test results, including charts, statistics, and metrics.<br/>
* `global/index.html`: This HTML report aggregates results from all simulations, giving an overall summary of the performance tests conducted.<br/>
* `js/`: This directory contains JavaScript files used to render interactive charts and graphs in the HTML reports.<br/>
* `results/`: Contains detailed data files (.log, .json) with raw performance metrics collected during the test execution.<br/>
* `simulation.log`: A log file that records detailed information about the simulation run, including request responses, errors, and debug information.<br/>

When reviewing reports in Gatling, you can analyze key performance indicators such as response times, throughput, error rates, and other metrics to assess the performance of your system under the defined load. The visual representations provided in the HTML reports make it easier to identify performance bottlenecks, trends, and areas for improvement in your application.<br/>

<a id="five"></a>
## 5. Example Load Testing Scenario: performed for ClickUp
This example outlines a load testing scenario conducted using the Gatling framework for the ClickUp application. The objective is to evaluate the server's performance under varying load conditions and identify potential bottlenecks.<br/>

#### Load Simulation Parameters:
1. **Initial Load Phase**: 
    * **20 users/sec for 20 seconds**
2. Ramp-up Phase:
    * Gradually increase to **100 users/sec over 30 seconds**

#### Simulation Code:
```java
public class AuthorizedUserSimulation extends Simulation {
    {
        setUp(
            getAuthorizedUserScenario()
                .injectOpen(
                    constantUsersPerSec(20).during(Duration.ofSeconds(20)),
                    rampUsersPerSec(20).to(100).during(Duration.ofSeconds(30))
                )
                .protocols(HttpProtocolConfig.getHttpProtocol()));
    }
}
```

#### Test Results Summary:
```text
===================================================================
---- Global Information -------------------------------------------
> request count                    2200   (OK=108      KO=2092  )
> min response time                128    (OK=148      KO=128   )
> max response time                20174  (OK=20174    KO=13137 )
> mean response time               1058   (OK=1257     KO=1048  )
> std deviation                    2432   (OK=3957     KO=2326  )
> response time 50th percentile    139    (OK=162      KO=139   )
> response time 75th percentile    161    (OK=170      KO=149   )
> response time 95th percentile    7141   (OK=11185    KO=7141  )
> response time 99th percentile    11141  (OK=19955    KO=10144 )
> mean requests/sec                30.986 (OK=1.521    KO=29.465)

---- Response Time Distribution -----------------------------------
> t < 800 ms                       99   (5% )
> 800 ms <= t < 1200 ms            1    (0% )
> t >= 1200 ms                     8    (0% )
> failed                           2092 (95%)

---- Errors -------------------------------------------------------
> status.find.is(200), but actually found 429       2092 (100.0%)
===================================================================
```

#### Assessment of Results:
* **Request Limit Exceeded:** The test showed a 429 error status for 95% of the requests, indicating that the load significantly surpassed the server's capacity to handle requests.<br/>
* **Significant Response Delays:** The server exhibited long response times, with the maximum response delay peaking at 20174 ms.<br/>

#### Recommendations for Improvement:
1. **Decrease Load:** To improve performance, consider reducing the load in simulations or ramping up more gradually. The following configuration can be utilized:<br/>
```java
public class AuthorizedUserSimulation extends Simulation {
    {
        setUp(
            getAuthorizedUserScenario()
                .injectOpen(
                    constantUsersPerSec(20).during(Duration.ofSeconds(20)),
                    rampUsersPerSec(20).to(50).during(Duration.ofSeconds(30))
                )
                .protocols(HttpProtocolConfig.getHttpProtocol()));
    }
}
```
2. **Implement Error Handling Logic:** It's advisable to integrate error-handling mechanisms into testing scenarios, such as automatically retrying requests upon receiving a 429 status code, with a suitable delay between retry attempts.<br/>

#### Reporting Results:
<img width="1917" height="1315" alt="result1" src="https://github.com/user-attachments/assets/e6f44aae-16f6-45f0-93cc-5327c8b4d410" />
<img width="1917" height="1216" alt="result2" src="https://github.com/user-attachments/assets/1873e716-2b95-44ca-9565-3df042ee09c2" />


---
#### Copyright (c) 2024 Marta Kravchuk under MIT License.