import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;


public class EdgeList implements List<Edge2> {

	List<Edge2> list = new LinkedList<Edge2>();
	
	@Override
	public boolean add(Edge2 e) {
		return list.add(e);
	}

	@Override
	public void add(int index, Edge2 element) {
		list.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Edge2> c) {
		return addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Edge2> c) {
		return addAll(index, c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return containsAll(c);
	}

	@Override
	public Edge2 get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<Edge2> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<Edge2> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<Edge2> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public Edge2 remove(int index) {
		return list.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public Edge2 set(int index, Edge2 element) {
		return list.set(index, element);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<Edge2> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	public Edge2[] getEdgesFrom(int source){
		Vector<Edge2> values = new Vector<Edge2>();
		for(Edge2 e : list){
			if(e.getSource() == source)
				values.add(e);
		}
		return (Edge2[]) values.toArray();
	}
	
	public Edge2[] getEdgesTo(int destination){
		Vector<Edge2> values = new Vector<Edge2>();
		for(Edge2 e : list){
			if(e.getDestination() == destination)
				values.add(e);
		}
		return (Edge2[]) values.toArray();
	}
	
	public Edge2[] getEdgesWeight(int weight){
		Vector<Edge2> values = new Vector<Edge2>();
		for(Edge2 e : list){
			if(e.getWeight() == weight)
				values.add(e);
		}
		return (Edge2[]) values.toArray();
	}

}
