package com.gmail.robmadeyou;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class Sound {
	
	
	public static float volume = 1F;
	
	public static Audio introMusic;
	
	public static Audio wavEffect;
	public static Audio buttonPress;
	
	public static boolean isSoundMuted= false;
	
	public static void init() {

	try {
		
		introMusic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/intro_song.wav"));
		
		wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/boom.wav"));
		buttonPress = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sound/press.wav"));
		
	}catch (IOException e) {
		e.printStackTrace();
	}
	}
	public static void playSound(Audio sound){
		sound.playAsSoundEffect(volume, 1.0F, false);
	}
	public static void playMusic(Audio sound){
		sound.playAsMusic(volume, 1.0F, true);
	}
	public static void muteSound(){
		SoundStore.get().pauseLoop();
		SoundStore.get().setMusicVolume(0F);
		SoundStore.get().setSoundVolume(0F);
		isSoundMuted = true;
	}
	public static void unMuteSound(){
		SoundStore.get().restartLoop();
		SoundStore.get().setMusicVolume(1F);
		SoundStore.get().setSoundVolume(1F);
		isSoundMuted = false;
	}
	public static void toggleSounds(){
		if(isSoundMuted){
			unMuteSound();
		}else{
			muteSound();
		}
	}
	
}
