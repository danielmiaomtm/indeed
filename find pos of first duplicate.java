public String findFirstDuplicate (String[] words) {
		
		Map<String, Integer> map = new HashMap<>();
		int minVal = words.length;
		for (int i = 0; i < words.length; i++) {
			if (!map.containsKey(words[i])) {
				map.put(words[i], i);
			} else {
				minVal = Math.min(minVal, map.get(words[i]));
			}
		}
		return words[minVal];
	}
