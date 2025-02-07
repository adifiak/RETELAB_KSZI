package hu.bme.mit.train.system;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);

	TrainSystem(){
		//Createing timed tesk
		TimerTask task = new TimerTask() {
			public void run() {
				controller.followSpeed();
			}
		};
		//Createing timer
		Timer timer = new Timer("Timer");
    
		//Seting delay to 10ms
		long delay = 10L;
		//Starting timed task
		timer.schedule(task, delay);
	}

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

}
