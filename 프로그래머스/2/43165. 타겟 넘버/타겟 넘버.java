class Solution {
    
    static int n, target, answer;
    
    static void rec(int[] numbers, int sum, int index) {
        if(index == n) {
            if(sum == target) ++answer;
            return;
        }
        rec(numbers, sum + numbers[index], index + 1);
        rec(numbers, sum - numbers[index], index + 1);        
    }
    
    public int solution(int[] numbers, int target) {
        n = numbers.length;
        this.target = target;
        answer = 0;
        rec(numbers, 0, 0);
        return answer;
    }
}