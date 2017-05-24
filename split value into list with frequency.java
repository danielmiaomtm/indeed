	public static List<List<Integer>> splitList (List<Integer> input) {
		List<List<Integer>> result = new ArrayList<>();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < input.size(); i++) {
			int curNum = input.get(i);
			if (!map.containsKey(curNum)) {
				map.put(curNum, 1);
			} else {
				map.put(curNum, map.get(curNum) + 1);;
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int count = entry.getValue();
			int val = entry.getKey();
			for (int i = 0; i < count; i++) {
				if (result.size() < i + 1) {
					List<Integer> list = new ArrayList<>();
					list.add(val);
					result.add(list);
				} else {
					List<Integer> list = result.get(i);
					list.add(val);
					result.set(i, list);
				}
			}
		}
		return result;
	}
