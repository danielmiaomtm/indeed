// [java, python, python, java]  第一个出现的重复单词 return java

public String findFirstDuplicate (String[] words) {
	Set<String> visited = new HashSet<.();
	for (int i = 0; i < words.length; i++) {
		if (!visited.contains(words[i])) {
			visited.add(words[i]);
		} else {
			return i;
		}
	}
	return -1;
}


// [java, python, python, java]  最早出现的重复单词 return java

public String findFirstDuplicate (String[] words) {
		
		Map<String, Integer> map = new HashMap<>();
		int minVal = words.length + 1;
		for (int i = 0; i < words.length; i++) {
			if (!map.containsKey(words[i])) {
				map.put(words[i], i);
			} else {
				minVal = Math.min(minVal, map.get(words[i]));
			}
		}
		return minVal == words.length + 1 ? "" : words[minVal];
	}
