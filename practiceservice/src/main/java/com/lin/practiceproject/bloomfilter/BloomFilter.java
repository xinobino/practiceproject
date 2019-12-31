package com.lin.practiceproject.bloomfilter;

import java.util.BitSet;

/**
 * 本地布隆过滤器的实现
 */
public class BloomFilter {

    /**
     * 定义位数组的大小
     */
    private static final int DEFAULT_SIZE = 2<<24;

    /**
     * 通过这个数组可以创建6个不同的hash函数
     */
    private static final int [] HASH = new int[]{3,13,46,71,91,134};

    /**
     * 位数组 数组上的元素只能是 0或者是1
     */
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);

    /**
     * 存放包含hash函数的类的数组
     */
    private SimpleHash [] hashs = new SimpleHash[HASH.length];

    /**
     * 初始化类的构造函数
     */
    public BloomFilter (){
        for(int i = 0; i< HASH.length;i++){
            hashs[i] = new SimpleHash(DEFAULT_SIZE,HASH[i]);
        }
    }

    /**
     * 添加元素到位数组上
     */
    public void add(Object value){
        for(SimpleHash simpleHash : hashs){
            bitSet.set(simpleHash.hash(value),true);
        }
    }

    /**
     * 判断指定元素是否在位数组上(布隆过滤器上)
     */
    public boolean contains(Object value){
        boolean result = true;
        for(SimpleHash f: hashs){
            result = true && bitSet.get(f.hash(value));
        }
        return result;
    }

    /**
     * 静态内部类
     */
    public static class SimpleHash{
        private int cap;
        private int seed;

        public SimpleHash(int cap,int seed){
            this.cap = cap;
            this.seed = seed;
        }
        /**
         * 计算hash 值
         */
        public int hash(Object value){
            int h;
            return (value == null ? 0 : Math.abs(seed *(cap-1) &((h = value.hashCode()) ^(h  >>>16))));
        }
    }



}
