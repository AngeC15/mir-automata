package Controller.audio;

public interface MusicListener {
	
	public void beat_0();
	public void beat_1();
	public void beat_2();
	
	public void phase_change(int phase, int phaseIdx);
	
}
