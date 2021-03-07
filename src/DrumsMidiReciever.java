
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.sound.midi.MidiMessage;

public class DrumsMidiReciever extends AbstractMidi {

	public static final byte NOTE_PEDAL = 36;
	public static final byte NOTE_RED = 38;
	public static final byte NOTE_YELLOW = 47;
	public static final byte NOTE_GREEN = 43;
	public static final byte NOTE_BLUE = 45;
	
	public static final byte ACTION_HIT = -112;
	public static final byte ACTION_RELEASE = -128;
	
	private static final HashMap<Byte, Integer> KEY_COMBOS = new HashMap<Byte, Integer>();
	
	static {
		KEY_COMBOS.put(NOTE_RED, KeyEvent.VK_2);
		KEY_COMBOS.put(NOTE_YELLOW, KeyEvent.VK_1);
		KEY_COMBOS.put(NOTE_BLUE, KeyEvent.VK_3);
		KEY_COMBOS.put(NOTE_GREEN, KeyEvent.VK_4);
		KEY_COMBOS.put(NOTE_PEDAL, KeyEvent.VK_5);
	}
	
	public void send(MidiMessage msg, long timeStamp) {

		
		byte action = msg.getMessage()[0];
		byte note = msg.getMessage()[1];
		byte howHard = msg.getMessage()[2];
		
		StringBuilder b = new StringBuilder();
		switch(note) {
		case NOTE_PEDAL: b.append("Pedal"); break;
		case NOTE_RED: b.append("Red"); break;
		case NOTE_YELLOW: b.append("Yellow"); break;
		case NOTE_BLUE: b.append("Blue"); break;
		case NOTE_GREEN: b.append("Green"); break;
		default: b.append("Unknown: " + note); break;
		}
		
		b.append(" ");
		
		switch(action) {
		case ACTION_RELEASE: b.append("Release"); break;
		case ACTION_HIT: b.append("Hit"); break;
		default: b.append("Unknown: " + action); break;
		}
		
		b.append(" ");
		
		if(action == ACTION_HIT) {
			b.append("Hard: ");
			b.append(howHard);
		}
		
		System.out.println(b.toString());
		
		sendKeyStrokes(note, action);
		
	}
	
	private void sendKeyStrokes(byte note, byte action) {
		Integer key = KEY_COMBOS.getOrDefault(note, null);
		
		if(key != null) {
			if(action == ACTION_HIT) {
				robot.keyPress(key);
			}
			else if(action == ACTION_RELEASE) {
				robot.keyRelease(key);
			}
		}
	}
	
	public void close() {}


}
