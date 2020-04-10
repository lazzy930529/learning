package com.lw.consistenthash;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 含有虚拟节点的一致性hash，相当于增加很多slot来使得分布更加均匀
 * @author liuwei
 * @date 2020-04-10 15:29
 */
public class ConsistentHashWithVirtualNodeServer extends AbstractConsistentHashServer {

    @Getter
    private List<WebNode> realNodes = Arrays.asList(
            new RealNode("192.168.0.1"),
            new RealNode("192.168.0.2"),
            new RealNode("192.168.0.3"),
            new RealNode("192.168.0.4"),
            new RealNode("192.168.0.5")
    );

    public ConsistentHashWithVirtualNodeServer(Integer virtualCount) {
        for (WebNode realNode : realNodes) {
            for (int i = 1; i <= virtualCount; i++) {
                this.addNode(new VirtualNode(realNode, i));
            }
        }
    }


    public static void main(String[] args) {
        ConsistentHashWithVirtualNodeServer server = new ConsistentHashWithVirtualNodeServer(1000);
        for (int i = 0; i < 100000; i++) {
            server.route(String.valueOf(i)).service();
        }
        for (WebNode node : server.getRealNodes()) {
            System.out.println(node.toString());
        }
    }

}
