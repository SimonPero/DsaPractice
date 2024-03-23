package IntentosLeetcode.arrAndTwoPointer.maxWaterContainer;
public class wawa {
    /* public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    } */

    public int maxArea(int[] height) {
        int a = 0;
        int b = height.length - 1;
        int maxArea = 0;
        for(int i = 0; i<height.length; i++){
            while (b >= a) {
                int area =(b-a) * Math.min(height[a],height[b]);
                maxArea=Math.max(area, maxArea);
                b--;
            }
            a++;
            b = height.length - a;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        wawa wa = new wawa();
        int[] height = {1,8,6,2,5,4,8,3,7};

        wa.maxArea(height);
    }
}
