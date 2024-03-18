package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
//import hu.bme.mit.train.interfaces.TrainSystem;
//import hu.bme.mit.train.system.TrainSystem;
/*import hu.bme.mit.train.system.TrainSystem;
import hu.bme.mit.train.interfaces.TrainSystem;*/

public class TrainSensorTest {

    TrainController mockedController;
	TrainSensorImpl sensor;
	TrainUser mockedUser;

    
	@Before
	public void before() {
	
		mockedController = mock(TrainController.class);
        mockedUser = mock(TrainUser.class);
		
        sensor = new TrainSensorImpl(mockedController,mockedUser);
		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void test50percentslower() {
        when(mockedController.getReferenceSpeed()).thenReturn(150);
		sensor.overrideSpeedLimit(150);
        sensor.overrideSpeedLimit(50);
        verify(mockedUser, times(1)).setAlarmState(true);
		//Assert.assertEquals(true, user.getAlarmState());
	}

    @Test
	public void testnoalarm() {
        when(mockedController.getReferenceSpeed()).thenReturn(150);
		sensor.overrideSpeedLimit(150);
        sensor.overrideSpeedLimit(140);
        verify(mockedUser, times(0)).setAlarmState(true);
		//Assert.assertEquals(false, user.getAlarmState());
	}
//asdasdasdasd
    @Test
	public void testnegativevalue() {
		sensor.overrideSpeedLimit(-10);
        verify(mockedUser, times(2)).setAlarmState(true);
		//Assert.assertEquals(true, user.getAlarmState());
	}

    @Test
	public void testlargevalue() {
		sensor.overrideSpeedLimit(600);
        verify(mockedUser, times(1)).setAlarmState(true);
		//Assert.assertEquals(true, user.getAlarmState());
	}
}
