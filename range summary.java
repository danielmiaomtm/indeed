/*
Input = [1, 2, 3, 5, 7, 8, 9, 10,15]
Out = "1-3, 5, 7-9, 10, 15"
*/

public String rangeSummary (int[] nums) {
		StringBuilder sb = new StringBuilder();
		int pre = 0;
		int preVal = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - nums[pre] == 1) {
				preVal = nums[i];
				continue;
			} else {
				if (i - pre == 1) {
					sb.append(nums[pre]).append(",");
				} else {
					sb.append(nums[pre]).append("-").append(nums[i - 1]).append(",");
				}
				pre = i;
				preVal = nums[i];
			}
		}
		
		if (pre == nums.length - 1) {
			sb.append(nums[pre]);
		} else {
			sb.append(preVal).append("-").append(nums[nums.length - 1]);
		}
		
		return sb.toString();
	}
