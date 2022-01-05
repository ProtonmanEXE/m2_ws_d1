package protonmanexe.workshopday2_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Workshopday21Application {

	private final static Logger logging = LoggerFactory.getLogger(Workshopday21Application.class); // instantiate logger

	private final static int DEFAULT_PORT = 3000;

	public static void main(String[] args) {

		// variable declaration
		int portNumber = DEFAULT_PORT;

		// object initialisation
		SpringApplication app = new SpringApplication(Workshopday21Application.class);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> optsVal = appArgs.getOptionValues("port"); // this is port option
		String environmentPort = System.getenv("PORT"); // this is environment variable for PORT (case sensitive) 

		// check if port option is set
		if (optsVal != null && optsVal.get(0) != null && optsVal.get(0).matches("[+-]?\\d+")) {
			portNumber = Integer.valueOf(optsVal.get(0));
			// logging.info("1) portNumber is " +portNumber);
		// check if environment variable port is set
		} else if (environmentPort != null && environmentPort.matches("[+-]?\\d+")) {
			portNumber = Integer.valueOf(environmentPort);
			// logging.info("2) portNumber is " +portNumber);
		// if none of the above are set, default to port 3000
		} else {
			// logging.info("3) portNumber is " +portNumber);
		}			

		logging.info("portNumber is " +portNumber);
		// override the spring boot app port number using the defaultproperties from spring boot framework
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
				
		app.run(args);
	}

}