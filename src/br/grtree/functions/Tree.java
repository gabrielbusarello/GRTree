package br.grtree.functions;

import java.util.Iterator;

/*
 *  aÌ�rvore onde os nodos podem ter uma quantidade arbitraÌ�ria de filhos.
 * */
public class Tree<E> {
	
	private int size;
	private Position<E> root;
	
	/** Retorna a raiz da aÌ�rvore. */
	public Position<E> root() throws EmptyTreeException{
		if (root == null) {
			throw new EmptyTreeException("�?rvore vazia.");
		}
		
		return root;
	}
	
	/** Retorna o pai de um dado nodo. */
	public Position<E> parent(Position<E> position) throws BoundaryViolationException{
		if (position.getParent() == null) {
			throw new BoundaryViolationException("Esse nó não tem pai");
		}

		return position.getParent();
	}
	
	/** Retorna uma colecÌ§aÌƒo iteraÌ�vel dos filhos de um dado nodo. */
	public Iterator<Position<E>> children(Position<E> position)  throws InvalidPositionException {
		if (position == null) {
			throw new InvalidPositionException("Posição inválida");
		}

		return position.getChildren();
	}
	
	/** Retorna se um dado nodo eÌ� interno. */
	public boolean isInternal(Position<E> position) throws InvalidPositionException  {
		if (position == null) {
			throw new InvalidPositionException("Posição inválida");
		}

		return position.size() > 0;
	}
	
	/** Retorna se um dado nodo eÌ� externo. */
	public boolean isExternal(Position<E> position)  throws InvalidPositionException {
		return !isInternal(position);
	}
	
	/** Retorna se um dado nodo eÌ� a raiz da aÌ�rvore. */
	public boolean isRoot(Position<E> position) throws InvalidPositionException {
		if (position == null) {
			throw new InvalidPositionException("Posição inválida");
		}

		return root.equals(position);
	}

	/** Retorna a quantidade de nodos da aÌ�rvore. */
	public int size() {
		return size;
	}
	
	/** Retorna se a aÌ�rvore estaÌ� vazia. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Retorna uma colecÌ§aÌƒo iteraÌ�vel dos nodos. */
	public Iterator<Position<E>> positions(){
		return root.getChildren();
	}
	
	/** Substitui o elemento armazenado em um dado nodo. */
	public E replace(Position<E> position, E element) throws InvalidPositionException {
		if (position == null) {
			throw new InvalidPositionException("Posição inválida");
		}
		
		position.setElement(element);

		return element;
	}
	
	/**
	 * Adiciona a raiz em uma Ã¡rvore vazia
	 * @param element
	 */
	public Position<E> addRoot(E element) throws NonEmptyTreeException {
		if (root != null) {
			throw new NonEmptyTreeException("Já existe uma raiz");
		}
		size++;
		
		root = new Position<E>(element);

		return root;
	}
	
	/** Adicione um filho no nodo */
	public Position<E> addChild(Position<E> parent, E element) throws InvalidPositionException{
		if (parent == null) {
			throw new InvalidPositionException("Posição inválida");
		}
		size++;

		return parent.addChild(element);
	}
	
}
