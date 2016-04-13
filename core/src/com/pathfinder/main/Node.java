package com.pathfinder.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class Node extends Actor {
	private Texture texture;
	public int ID;
	public NodeData data;
	public boolean isTouched;

	Node() {
		setWidth(100);
		setHeight(100);
		setColor(Color.BLUE);
		setTouchable(Touchable.enabled);
		texture = new Texture(Gdx.files.internal("node.png"));
		data = new NodeData();
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				isTouched = true;
				System.out.println("Is touched !");
				return false;
			}
		});
	}

	@Override
	public void draw(Batch batch, float pa) {
		super.draw(batch, pa);
		batch.setColor(getColor());
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
				false, false);
	}

	class NodeData {
		Array<Vector> startVectors;
		Array<Vector> endVectors;

		NodeData() {
			startVectors = new Array<Vector>();
			endVectors = new Array<Vector>();
		}
	}
}
