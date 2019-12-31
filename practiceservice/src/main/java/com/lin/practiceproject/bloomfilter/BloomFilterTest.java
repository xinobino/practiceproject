package com.lin.practiceproject.bloomfilter;

public class BloomFilterTest {

    public static void main(String args[]){
        BloomFilter bloomFilter = new BloomFilter();
        // add
        bloomFilter.add("www.baidu.com");
        bloomFilter.add("lin.li02");
        bloomFilter.add("123");
        // contain
        System.out.println(bloomFilter.contains("www.baidu.cm"));
        System.out.println(bloomFilter.contains("lin.li02"));
        System.out.println(bloomFilter.contains("1234"));


    }

}
