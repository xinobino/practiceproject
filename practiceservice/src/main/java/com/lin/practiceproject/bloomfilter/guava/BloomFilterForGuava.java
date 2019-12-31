package com.lin.practiceproject.bloomfilter.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * guava 自带 布隆过滤器
 * 缺点 -只支持单机 不支持分布式
 * @author lin
 */
public class BloomFilterForGuava {

    public static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),1500,0.01);

    public static void main(String args[]){
//      bloomFilter.put("www.baidu.com");
        System.out.println(bloomFilter.mightContain("www.baidu.com"));

    }
}
