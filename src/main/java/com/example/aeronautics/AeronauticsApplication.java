package com.example.aeronautics;

import com.example.aeronautics.configuration.properties.AeronauticsProperties;
import com.example.aeronautics.configuration.properties.FileProperties;
import com.example.aeronautics.ui.JavafxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AeronauticsProperties.class,
		FileProperties.class})
public class AeronauticsApplication {

	public static void main(String[] args) {
		Application.launch(JavafxApplication.class, args);
	}

}
