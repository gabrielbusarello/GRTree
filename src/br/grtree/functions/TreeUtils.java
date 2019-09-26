package br.grtree.functions;

import java.util.Iterator;

import br.grtree.app.File;

public class TreeUtils {
	
	// private static String preOrder = "";
	// private static String postOrder = "";
	
	public static <E> int depth(Tree<E> T, Position<E> v) {
		if (T.isRoot(v)) {
			return 0;
		}
		Position<E> w = v.getParent();
		
		return 1 + depth(T,w);
	}
	
	public static <E> int height(Tree<E> T, Position<E> v) {
		if (T.isExternal(v)) {
			return 0;
		}
		
		int h = 0;
		
		Iterator<Position<E>> w = T.children(v);
		while (w.hasNext()) {
			h = Math.max(h, height(T, w.next()));
		}
		
		return 1 + h;
	}

	public static <E> String toStringPreOrder(Tree<E> T, Position<E> v) {
		String preOrder = v.getElement().toString();
		
		Iterator<Position<E>> w = T.children(v);
		
		while (w.hasNext()) {
			preOrder += ", " + toStringPreOrder(T, w.next());
		}

		return preOrder;
	}
	
	public static <E> String toStringPostOrder(Tree<E> T, Position<E> v) {
		String postOrder = "";
		Iterator<Position<E>> w = T.children(v);
		
		while (w.hasNext()) {
			postOrder += toStringPostOrder(T, w.next()) + ", ";
		}
		
		postOrder += v.getElement();

		return postOrder;
	}

	public static String toStringPreOrderFile(Tree<File> T, Position<File> v) {
		String preOrder = v.getElement().toString();
		
		Iterator<Position<File>> w = T.children(v);
		
		while (w.hasNext()) {
			Position<File> file = w.next();
			preOrder += toStringPreOrder(T, file).replace(", ", "");
		}

		return preOrder;
	}

	public static int toIntPostOrder(Tree<File> T, Position<File> v) {
		int postOrder = 0;
		Iterator<Position<File>> w = T.children(v);
		
		while (w.hasNext()) {
			postOrder += toIntPostOrder(T, w.next());
		}
		
		if (v.getElement().isDirectory()) {
			v.getElement().setSize(1 + postOrder);
		}
		
		return v.getElement().getSize();
	}

}
