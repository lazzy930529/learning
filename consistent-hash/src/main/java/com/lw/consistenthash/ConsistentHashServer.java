package com.lw.consistenthash;

import java.util.List;

/**
 * https://mp.weixin.qq.com/s/P_ibPT_lfIRpARGXyV2Nog
 * 一致性hash 负载均衡server
 * @author liuwei
 * @date 2020-04-10 16:02
 */
public interface ConsistentHashServer {
    WebNode getNode(String hash);

    WebNode getNodeByIp(String ip);

    void addNode(WebNode node);

    void removeNode(String hash);

    void removeNodeByIp(String ip);

    WebNode route(String request);

    List<WebNode> getNodes();
}
