public static String reverse(String s){
        if(s == null || s.length() == 0){
                return s;
        }
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--){
                sb.append(s.charAt(i));
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < sb.length(); i++){
                if (sb.charAt(i) == ';'){
                        left = i;
                }
                if (sb.charAt(i) == '&' && sb.charAt(left) == ';'){
                        right = i;
                        while (left < right){
                                char tmp = sb.charAt(right);
                                sb.setCharAt(right--, sb.charAt(left));
                                sb.setCharAt(left++, tmp);
                        }
                        left = i + 1;
                }
        }
        return sb.toString();
}
