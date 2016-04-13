package com.pathfinder.main;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class TouchManager extends ActorGestureListener {
	private Main main;

	public TouchManager(Main main) {
		this.main = main;
	}



	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		Node touched = getTouchedNode(x, y);
		if (touched == null) {
			if (main.graphMan.selectedNode != null) {
				System.out.println("efefe");
				Node n = main.graphMan.selectedNode;
				n.setPosition(x - n.getWidth() / 2,
						y - n.getHeight() / 2);
				main.graphMan.selectedNode = null;
				return;
			}
			System.out.println("fefefe");
			Node n = new Node();
			n.setPosition(x - n.getWidth() / 2,
					y - n.getHeight() / 2);
			main.graphMan.addNode(n);
		} else {
			System.out.println("In else");
			if(main.graphMan.selectedNode != null){
				System.out.println("not null");
				main.graphMan.addVector(main.graphMan.selectedNode, touched);
				main.graphMan.selectedNode = null;
				return;
			}
			main.graphMan.selectedNode = touched;
			System.out.println("selected node set to: "+main.graphMan.selectedNode);
		}
	}


	private Node getTouchedNode(float x, float y) {
		for (Node n : main.graphMan.nodes) {
			if (n.isTouched) {
				n.isTouched = false;
				System.out.println("huehue");
				return n;
			}
		}
		return null;
	}
}
