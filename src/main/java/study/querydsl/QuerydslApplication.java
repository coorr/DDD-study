package study.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import study.querydsl.commnad.after.HeaterOnCommand;
import study.querydsl.commnad.after.LampOnCommand;
import study.querydsl.commnad.after.OkGoogles;
import study.querydsl.commnad.before.Heater;
import study.querydsl.commnad.before.Lamp;

@SpringBootApplication
@EnableJpaAuditing
public class QuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerydslApplication.class, args);
		Heater heater = new Heater();
		Lamp lamp = new Lamp();
		HeaterOnCommand heaterOnCommand = new HeaterOnCommand(heater);
		LampOnCommand lampOnCommand = new LampOnCommand(lamp);
		OkGoogles okGoogles = new OkGoogles();

		okGoogles.setCommand(heaterOnCommand);
	}

}
