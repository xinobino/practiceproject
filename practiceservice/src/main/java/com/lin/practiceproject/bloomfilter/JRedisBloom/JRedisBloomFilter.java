package com.lin.practiceproject.bloomfilter.JRedisBloom;


import io.rebloom.client.Client;

/**
 * JRedis 布隆过滤器
 */
public class JRedisBloomFilter {


    public static void main(String args[]){
        Client client = new Client("127.0.0.1", 6379);
        // add
        // client.addMulti("age","18","19","20");
        // 判断
        System.out.println(client.exists("name","lin.li02"));
        System.out.println(client.exists("age","10"));
        System.out.println(client.exists("age","19"));
    }
}
