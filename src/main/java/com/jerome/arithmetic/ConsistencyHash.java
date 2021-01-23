package com.jerome.arithmetic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 模拟一致性Hash算法
 * http://blog.csdn.net/jerome_s/article/details/52492862
 * 这段代码网上找的，输出各个节点的负载是差不多的，但是我在想可以自己写一个达到完全的负载均衡
 * 比如：VIRTUAL_NODE_COUNT = 150
 * 维护0~(2^32)/150的数字,新加入节点就取(2^32)/150内一个没有使用过的数,
 * 然后在这个数的基础上面加150次的(2^32)/150可以生成150个分布均衡的虚拟节点。
 * 如果有新增或者减少节点需要维护。
 *
 * @author jerome
 * @date 2016/9/1 9:26
 */
public class ConsistencyHash {

    // 环的所有节点
    private TreeMap<Long, Object> allNodes = null;
    // 真实服务器节点
    private List<Object> realNodes = new ArrayList();
    // 设置虚拟节点数目
    // 太多会影响性能，太少又会导致负载不均衡，一般说来，经验值是150，
    // 当然根据集群规模和负载均衡的精度需求，这个值应该根据具体情况具体对待。
    private int VIRTUAL_NODE_COUNT = 150;

    /**
     * 初始化一致环
     */
    public void init() {

        // 加入五台真实服务器
        realNodes.add("192.168.0.0-服务器0");
        realNodes.add("192.168.0.1-服务器1");
        realNodes.add("192.168.0.2-服务器2");
        realNodes.add("192.168.0.3-服务器3");
        realNodes.add("192.168.0.4-服务器4");

        // 构造每台真实服务器的虚拟节点
        allNodes = new TreeMap<>();
        for (int i = 0; i < realNodes.size(); i++) {
            Object nodeInfo = realNodes.get(i);
            for (int j = 0; j < VIRTUAL_NODE_COUNT; j++) {
                allNodes.put(hash(computeMd5("NODE-" + i + "-VIRTUAL-" + j), 0), nodeInfo);
                // allNodes.put(hash(nodeInfo.toString() + j), nodeInfo);
            }
        }
    }

    /**
     * 计算MD5值
     */
    public byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return md5.digest();
    }

    /**
     * 根据2^32把节点分布到环上面
     *
     * @param digest
     * @param nTime
     * @return
     */
    public long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
                | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
                | (digest[0 + nTime * 4] & 0xFF);

        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }


    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public Object getNodeInfo(long hash) {
        Long key = hash;
        SortedMap<Long, Object> tailMap = allNodes.tailMap(key);
        if (tailMap.isEmpty()) {
            key = allNodes.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        return allNodes.get(key);
    }

    public static void main(String[] args) {

        ConsistencyHash consistencyHash = new ConsistencyHash();
        consistencyHash.init();

        // 循环50次，是为了取500个数来测试效果，当然也可以用其他任何的数据来测试
        int _0 = 0;
        int _1 = 0;
        int _2 = 0;
        int _3 = 0;
        int _4 = 0;

        Random ran = new Random();
        for (int i = 0; i < 500; i++) {
            // 随便取一个数的md5
            byte[]  ranNum = consistencyHash.computeMd5(String.valueOf(i));

            // 分配到随即的hash环上面
            long key = consistencyHash.hash(ranNum, 2);
            // long key = consistencyHash.hash(ranNum, ran.nextInt(consistencyHash.VIRTUAL_NODE_COUNT));

            // 获取他所属服务器的信息
            // System.out.println(consistencyHash.getNodeInfo(key));
            if (consistencyHash.getNodeInfo(key).equals("192.168.0.0-服务器0")){
                _0++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.1-服务器1")){
                _1++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.2-服务器2")){
                _2++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.3-服务器3")){
                _3++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.4-服务器4")){
                _4++;
            }else{
                System.out.println("error");
            }
        }

        // 输出每台服务器负载情况
        System.out.println("_0 = " + _0);
        System.out.println("_1 = " + _1);
        System.out.println("_2 = " + _2);
        System.out.println("_3 = " + _3);
        System.out.println("_4 = " + _4);
    }

}
