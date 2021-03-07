
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

public class LiveMidi {

	public void start() {

		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);

				List<Transmitter> transmitters = device.getTransmitters();

				if(device.getDeviceInfo().getName().equals("loopMIDI Port")) {
					for(int j = 0; j<transmitters.size();j++) {
						transmitters.get(j).setReceiver(new DrumsMidiReciever());
					}

					Transmitter trans = device.getTransmitter();
					trans.setReceiver(new DrumsMidiReciever());
					device.open();
					System.out.println(device.getDeviceInfo()+" Was Opened");
					
				}
				else if(device.getDeviceInfo().getName().equals("Arduino Leonardo")) {
					for(int j = 0; j<transmitters.size();j++) {
						transmitters.get(j).setReceiver(new GuitarMidiReciever());
					}

					Transmitter trans = device.getTransmitter();
					trans.setReceiver(new GuitarMidiReciever());
					device.open();
					System.out.println(device.getDeviceInfo()+" Was Opened");
					
				}
				else {
					System.out.println("Ignoring: " + device.getDeviceInfo().getName());
				}

			} 
			catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
}
