package com.pathfinder.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;

public class GraphManager {
	public Node startNode;
	public Node endNode;
	public Node selectedNode;
	public Array<Node> nodes;
	public Array<Vector> vectors;
	private BitmapFont font;
	private GlyphLayout fontLayout;
	private int lastID;
	private Main main;
	
	public GraphManager(Main main) {
		this.main = main;
		nodes = new Array<Node>();
		vectors = new Array<Vector>();
//		font = new BitmapFont(Gdx.files.internal("font.fnt"));
//		fontLayout = new GlyphLayout(font, "9");
//		font.setColor(Color.LIGHT_GRAY);
	}

	public void setStartNode(Node n) {
		startNode = n;
		Gdx.input.vibrate(80);
	}

	public void setEndNode(Node n) {
		endNode = n;
		Gdx.input.vibrate(80);
	}

	public void addVector(Node start, Node end) {
		for (Vector v : vectors) {
			if (v.data.startNode == start && v.data.endNode == end) {
				selectedNode = null;
				return;
			}
		}
		Vector vector = new Vector();
		vector.data.startNode = start;
		vector.data.endNode = end;
		vectors.add(vector);
		start.data.startVectors.add(vector);
		end.data.endVectors.add(vector);
		selectedNode = null;
		main.stage.addActor(vector);
		Gdx.input.vibrate(100);
	}

	public void removeVector(Node start, Node end) {
		for (Vector v : vectors) {
			if (v.data.startNode == start && v.data.endNode == end) {
				start.data.startVectors.removeValue(v, true);
				end.data.endVectors.removeValue(v, true);
				vectors.removeValue(v, true);

				break;
			}
		}
		Gdx.input.vibrate(new long[] { 0, 50, 50, 50 }, -1);

	}

	public void addNode(Node n) {
		n.ID = lastID++;
		nodes.add(n);
		Gdx.input.vibrate(100);
		main.stage.addActor(n);
	}

	public void removeNode(Node n) {
		Array<Vector> vectors = new Array<Vector>();
		vectors.addAll(n.data.startVectors);
		vectors.addAll(n.data.endVectors);
		vectors.removeAll(n.data.startVectors, true);
		vectors.removeAll(n.data.endVectors, true);
		nodes.removeValue(n, true);
		Gdx.input.vibrate(new long[] { 0, 50, 50, 50 }, -1);

	}

	public void setVectorCost(Vector v, int cost) {
		v.data.cost = cost;
	}

	public Vector getVector(Node a, Node b) {
		for (Vector v : vectors) {
			if (v.data.startNode == a && v.data.endNode == b)
				return v;
		}
		return null;
	}

	public void reset() {
		nodes.clear();
		vectors.clear();
		startNode = null;
		endNode = null;

		lastID = 0;

	}

}
