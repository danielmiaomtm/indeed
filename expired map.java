class Node {
	long time;
	K key;
	Node (K key, long time) {
		key = key;
		time = time;
	}
}
class ExpiredMap {
	Map<K, V> map = new HashMap<>();
	Map<K, long> timeMap = new HashMap<>();
	PriorityQueue<Map.Entry<K, long>> heap = new PriorityQueue<>(11, new Comparator<Map.Entry<K, long>> () {
		public int compare (Map.Entry<K, long> entry1, Map.Entry<K, long> entry2) {
			return entry2.getValue() - entry1.getValue();
		}
	});

	public void put (K key, V val, long duration) {
		long curTime = System.currentTimeMillis();
		while (!heap.isEmpty() && heap.peek().getValue() < curTime) {
			Node temp = heap.poll();
			map.remove(temp.key);
		}
		map.put(key, val);
		timeMap.put(key, curTime + duration);
	}

	public V get (K key) {
		long curTime = System.currentTimeMillis();
		while (!heap.isEmpty() && heap.peek().time < curTime) {
			Node temp = heap.poll();
			map.remove(temp.key);
		}
		if (!map.containsKey(key)) {
			return null;
		}
		return map.get(key);
	}

	//ex :
	//put(k, v, 1000)
	//get(k) -> v (less than 1000ms has passed since put)
	//get(k) -> null (more than 1000ms has passed since put)
}







//LRU思想


class Node<K,V> {
	Node pre;
	Node next;
	V val;
	K key;
	long time;
	Node (K key, V val, long time) {
		this.key = key;
		this.val = val;
		this.pre = null;
		this.next = null;
		this.time = time;
	}
}


public class ExpiredMap<K, V> {
	Map<K, Node> map = new HashMap<>();
	Node head =  new Node(0, null, null, 0)
	Node tail = new Node(0, null, null, 0);

	public void put (K key, V val, long duration) {
		long curTime = System.currentTimeMillis();

		checkExpired(head, curTime);

		Node temp = new Node(k, val, curTime + duration);
		if (!map.containsKey(key)) {
			//x-y-tail
			moveToTail(temp, tail);
		} else {
			node.pre.next = node.next;
			node.next.pre = node.pre;
			moveToTail(temp, tail);
		}

		map.put(key, temp);

	}


	public void moveToTail (Node node, Node tail) {
		tail.pre.next = temp;
		temp.next = tail;
		temp.pre = tail.pre;
		tail.pre = temp;
	}


	public V get (K key) {		
		long curTime = System.currentTimeMillis();

		checkExpired(head, curTime);
		return map.containsKey(key) ? map.get(key).val : null;
	}

	public void checkExpired (Node head, long curTime) {
		Node node = head.next;
		while (node != tail && node.time < curTime) {
			map.remove(node.key);
			head.next = node.next;
			node.next.pre = head;
			node.pre = null;
			node.next = null;
			node = head.next;
		}
	}
}
