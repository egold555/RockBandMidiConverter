import java.awt.AWTException;
import java.awt.Robot;

import javax.sound.midi.Receiver;

public abstract class AbstractMidi implements Receiver {

	protected static Robot robot;
	
	
	
	static {
		try {
			robot = new Robot();
		} 
		catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		
	}

}
