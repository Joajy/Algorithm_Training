class Solution {
    
    static int answer;
    
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        rec(picks, minerals, 0, 0);
        return answer;
    }
    
    static int mining(int pick, String mineral) {
        if(pick == 0) {
            return 1;
        } else if(pick == 1) {
            if(mineral.equals("diamond")) return 5;
            return 1;
        } else {
            if(mineral.equals("diamond")) return 25;
            else if(mineral.equals("iron")) return 5;
            return 1;
        }
    }
    
    static void rec(int[] picks, String[] minerals, int index, int total) {
        if(index >= minerals.length || (
            picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            answer = Math.min(answer, total);
            return;
        }
        int maxVal = Integer.MAX_VALUE;
        for(int p = 0; p < 3; ++p) {
            if(picks[p] == 0) continue;
            int adder = 0;
            for(int i = index; i < index + 5; ++i) {
                if(minerals.length == i) break;
                adder += mining(p, minerals[i]);
            }
            --picks[p];
            rec(picks, minerals, index + 5, total + adder);
            maxVal = Math.min(maxVal, total + adder);
            ++picks[p];
        }
    }
}