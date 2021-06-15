package Controller.audio;

import java.io.RandomAccessFile;
import java.util.ArrayList;

import Controller.audio.info3.game.sound.RandomFileInputStream;
import View.GameCanvas;

public class Maestro {
	private long offset;
	private long beatLength;
	private long length;
	
	private int subBeats[];
	private ArrayList<Boolean> subBeatMasks[];
	private ArrayList<Long> phaseTimings;
	private ArrayList<Integer> phaseIndex;
	
	private long elapsed;
	private long beatElapsed;
	private int beatCounters[];
	private int phase;
	private boolean enabled;
	
	private ArrayList<MusicListener> listeners;
	private GameCanvas canvas;
	private RandomFileInputStream music_stream;
	
	public Maestro(GameCanvas canvas) {
		this.canvas = canvas;
		subBeats = new int[2];
		subBeatMasks = new ArrayList[3];
		beatCounters = new int[3];
		listeners = new ArrayList<MusicListener>();
		clear();
		
	}
	void clear() {
		phaseTimings = new ArrayList<Long>();
		phaseIndex = new ArrayList<Integer>();
		enabled = false;
	}
	public void addPhase(long timing, int index) {
		phaseTimings.add(timing);
		phaseIndex.add(index);
	}
	public void setSubBeats(int sub1, int sub2) {
		subBeats[0] = sub1;
		subBeats[1] = sub2;
		
	}
	public void setBeatMask(int beat, boolean[] mask) {
		subBeatMasks[beat] = new ArrayList<Boolean>();
		for(int i=0; i < mask.length; i++) {
			subBeatMasks[beat].add(mask[i]);
		}
	}
	public void load(String music_path, String timings_path) {
		try { 
			RandomAccessFile file = new RandomAccessFile(music_path,"r");
			music_stream = new RandomFileInputStream(file);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	public void tick(long elapsed) {
		if(!enabled)
			return;
		
		this.elapsed += elapsed;
		beatElapsed += elapsed;
		if(beatElapsed >= beatLength) {
			beatElapsed -= beatLength;
			
			if(subBeatMasks[0].get(beatCounters[0]))
				beat_0();
			
			beatCounters[0]++;

			
			if(beatCounters[0] >= subBeats[0]) {
				beatCounters[0] = 0;
				if(subBeatMasks[1].get(beatCounters[1]))
					beat_1();
				beatCounters[1]++;
			}
			if(beatCounters[1] >= subBeats[1]) {
				beatCounters[1] = 0;
				if(subBeatMasks[2].get(beatCounters[2]))
					beat_2();
			}
		}
		if(this.elapsed > phaseTimings.get(phase)) {
			phase_change();
			phase++;
		}
		
	}
	
	public void start(float volume) {
		beatElapsed = elapsed = -offset;
		for(int i=0; i < 3; i++)
			beatCounters[i] = 0;
		phase = 0;
		canvas.playMusic(music_stream, 0, 1.0F);
		resume();
	}
	public void pause() {
		enabled = false;
	}
	public void resume() {
		enabled = true;
	}
	public void beat_0() {
		for(int i=0; i < listeners.size(); i++) {
			listeners.get(i).beat_0();
		}
	}
	public void beat_1() {
		for(int i=0; i < listeners.size(); i++) {
			listeners.get(i).beat_1();
		}
	}
	public void beat_2() {
		for(int i=0; i < listeners.size(); i++) {
			listeners.get(i).beat_2();
		}
	}
	public void phase_change() {
		for(int i=0; i < listeners.size(); i++) {
			listeners.get(i).phase_change(phase, phaseIndex.get(phase));
		}
	}
}
