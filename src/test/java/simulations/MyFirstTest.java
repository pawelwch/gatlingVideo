package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class MyFirstTest extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080/app/")
                    .header("Accept", "application/json")
                    .proxy(Proxy("localhost", 8080));


    //SCENARIO DEFINITION
    ChainBuilder getAllVideoGames =
            exec(http("Get all games").get("videogames"));


    //SCENARIO
    ScenarioBuilder myFirstScenario = scenario("My first test").exec(getAllVideoGames);


    //LOAD SCENARIO
    {
        setUp(
                myFirstScenario.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
