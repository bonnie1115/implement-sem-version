package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Version {
    /**
     * 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，比较两个"语义化版本"
     *
     * <p>传入两个形如x.y.z的字符串，比较两个语义化版本的大小。如果version1小于version2，返回-1；如果version1大于
     * version2，返回1。如果二者相等，返回0。
     *
     * <p>注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * @param version1 传入的版本字符串1，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @param version2 传入的版本字符串2，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @return -1/0/1 当version1 小于/等于/大于 version2时
     */
    public static int compare(String version1, String version2) {
        List<String> version1AsList = Arrays.asList(version1.split("\\."));
        ArrayList<String> newVersion1AsList= new ArrayList<>(version1AsList);
        List<String> version2AsList = Arrays.asList(version2.split("\\."));
        ArrayList<String> newVersion2AsList=new ArrayList(version2AsList);
        int largeVersionNumber = Math.max(newVersion1AsList.size(), newVersion2AsList.size());
        if (newVersion1AsList.size() < largeVersionNumber) {
            formatVersion(newVersion1AsList,largeVersionNumber);
        } else formatVersion(newVersion2AsList, largeVersionNumber);

        for (int i = 0; i <largeVersionNumber; i++) {
            if (Integer.parseInt(newVersion1AsList.get(i)) > Integer.parseInt(newVersion2AsList.get(i))) {
                return 1;
            } else if (Integer.parseInt(newVersion1AsList.get(i)) < Integer.parseInt(newVersion2AsList.get(i))) {
                return -1;
            }
        }
        return 0;
    }

    private static void formatVersion(List<String> version, int Number) {
        for (int i = version.size(); i <Number; i++) {
            version.add(String.valueOf(0));
        }
    }

    public static void main(String[] args) {
        compare("1.0", "2.2");
        compare("1.1", "2.2.1");
        compare("1", "2.2.2");
    }
}
