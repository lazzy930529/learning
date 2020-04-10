package com.lw.consistenthash;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 抽象一致性哈希服务，使用TreeMap（红黑树）来构建hash环
 * @author liuwei
 * @date 2020-04-10 16:06
 */
public abstract class AbstractConsistentHashServer implements ConsistentHashServer {

    //hash环
    protected SortedMap<String, WebNode> nodes = new TreeMap<>();


    @Override
    public WebNode getNode(String hash) {
        return nodes.get(hash);
    }

    @Override
    public WebNode getNodeByIp(String ip) {
        return nodes.values().stream().filter(node -> node.getIp().equals(ip)).findFirst().orElse(null);
    }

    @Override
    public void addNode(WebNode node) {
        String hash = HashUtil.getMD5Hash(node.getIp().getBytes());
        nodes.put(hash, node);
        System.out.println("增加节点 " + node.getIp() + "[" + hash + "]");
    }

    @Override
    public void removeNode(String hash) {
        WebNode node = nodes.get(hash);
        transfer(node);
        nodes.remove(hash);
        System.out.println("节点 " + node.getIp() + " 已删除");
    }

    @Override
    public void removeNodeByIp(String ip) {
        WebNode node = getNodeByIp(ip);
        transfer(node);
        nodes.remove(HashUtil.getMD5Hash(ip.getBytes()));
        System.out.println("节点 " + node.getIp() + " 已删除");
    }

    @Override
    public WebNode route(String request) {
        String hash = HashUtil.getMD5Hash(request.getBytes());
        // 只取出所有大于该hash值的部分而不必遍历整个Tree
        SortedMap<String, WebNode> tailMap = nodes.tailMap(hash);
        if (tailMap.isEmpty()) {
            // hash值在最尾部，应该映射到第一个node上
            return nodes.get(nodes.firstKey());
        } else {
            return tailMap.get(tailMap.firstKey());
        }
    }

    @Override
    public List<WebNode> getNodes() {
        return new ArrayList<>(nodes.values());
    }


    public void transfer(WebNode node) {
        Integer count = node.getCount();
        //todo: 节点数据迁移
        System.out.println("节点 " + node.getIp() + " 的数据已迁移");
    }
}
