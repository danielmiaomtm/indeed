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
