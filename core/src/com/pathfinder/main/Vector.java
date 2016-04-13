package com.pathfinder.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

public class Vector extends Group {
	public VectorData data;
	public Texture texture;
	public boolean isAnimated;
	private Decoration decoration;
	

	public Vector() {
		data = new VectorData();
		texture = new Texture(Gdx.files.internal("vector.png"));
		decoration = new Decoration(this, this.getWidth()/2);
		addActor(decoration);
		isAnimated = true;
	}
	private void adjustPosition() {
		float deltaY = data.startNode.getY() - data.endNode.getY();
		float deltaX = data.startNode.getX() - data.endNode.getX();
		float angleInDegrees = (float) (MathUtils.atan2(deltaY, deltaX) * 180 / Math.PI);
		setSize(
				(float) Math.abs(Math.sqrt(Math.pow(deltaX, 2)
						+ Math.pow(deltaY, 2))), 3f);
		setRotation(angleInDegrees);
		//setFlip(true, false);
		setPosition(data.endNode.getX() + data.endNode.getWidth() / 2,
				data.endNode.getY() + data.endNode.getHeight() / 2);
		decoration.mta.setPosition(getWidth(), -1.5f-decoration.getHeight()/2);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(getColor());
		adjustPosition();
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
				false, false);
	}

	class VectorData {
		Node startNode, endNode;
		int cost;
	}

	private class Decoration extends Actor {
		private Texture texture;
		private Vector vector;
		private float startX;
		private RepeatAction ra, colorRA;
		public MoveToAction mta;
		private Decoration(Vector v, float startX) {
			this.vector = v;
			this.startX = startX;
			setBounds(startX, -1.5f-30/2, 30, 30);
			texture = new Texture(Gdx.files.internal("vectordecoration.png"));
			ra = new RepeatAction();
			mta = new MoveToAction();
			
			mta.setDuration(12);
			ra.setCount(RepeatAction.FOREVER);
			ra.setAction(mta);
			ColorAction colorAction = new ColorAction();
			colorAction.setEndColor(Color.CLEAR);
			colorAction.setDuration(12);
			colorAction.setInterpolation(new Interpolation(){

				@Override
				public float apply(float a) {
					return 0.05f;
				}
				
			});
			colorRA = new RepeatAction();
			colorRA.setCount(RepeatAction.FOREVER);
			colorRA.setAction(colorAction);
			addAction(colorRA);
			addAction(ra);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			batch.setColor(getColor());
			batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation(), 0, 0, texture.getWidth(),
					texture.getHeight(), false, false);
			if(getX()>=vector.getWidth()-getWidth()/2){
				setX(startX);
				setColor(Color.WHITE);
			}
		}
		
		@Override
		public void act(float delta){
			if(isAnimated)
			super.act(delta);
		}
	}
}
