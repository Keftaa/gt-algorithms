package com.pathfinder.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
	DrawingScreen screen;
	Stage stage;
	GraphManager graphMan;
	TouchManager touchMan;
	InputMultiplexer multiplexer;
	
	@Override
	public void create() {
		multiplexer = new InputMultiplexer();

		stage = new Stage(new FitViewport(1366, 768));
		screen = new DrawingScreen(this);
		graphMan = new GraphManager(this);
		touchMan = new TouchManager(this);
		
	//	multiplexer.addProcessor(stage);
		stage.addListener(touchMan);
		
		Gdx.input.setInputProcessor(stage);
		this.setScreen(screen);
	}
	
}