class Solution {
    
    static int[] weight;
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 각 사람의 무게 합을 weight에 대입(최대 2명이므로 탐색 용이함)
        weight = new int[241];
        for(int p : people) {
            ++weight[p];
        }
        // weight 돌면서 가능한 무게의 인원을 태워간다
        for(int i = limit; i >= 1; --i) {
            for(int j = limit - i; j >= 1; --j) {
                if(i == j) continue;
                // i번 사람과 j번 사람의 무게 합이 limit를 넘지 않아야 한다
                // if(i + j > limit) continue;
                if(weight[i] > 0 && weight[j] > 0) {
                    // 둘의 합산이 limit를 넘을 경우 계산에 포함하지 않는다.
                    if(i + j > limit) continue;
                    int min = Math.min(weight[i], weight[j]);
                    weight[i] -= min;
                    weight[j] -= min;
                    answer    += min;
                }
            }
        }
        // 남은 인원들 
        for(int i = limit; i >= 1; --i) {
            if(i * 2 <= limit) {
                answer += (weight[i] / 2) + weight[i] % 2;
            } else {
                answer += weight[i];
            }
        }
        return answer;
    }
}