package com.lw.consistenthash;

/**
 * 不含虚拟节点的一致性hash
 * @author liuwei
 * @date 2020-04-10 15:29
 */
public class ConsistentHashWithoutVirtualNodeServer extends AbstractConsistentHashServer {


    public ConsistentHashWithoutVirtualNodeServer() {
        this.addNode(new RealNode("192.168.0.1"));
        this.addNode(new RealNode("192.168.0.2"));
        this.addNode(new RealNode("192.168.0.3"));
        this.addNode(new RealNode("192.168.0.4"));
        this.addNode(new RealNode("192.168.0.5"));
    }


    public static void main(String[] args) {
        ConsistentHashServer server = new ConsistentHashWithoutVirtualNodeServer();
        for (int i = 0; i < 100000; i++) {
            server.route(String.valueOf(i)).service();
        }
        for (WebNode node:server.getNodes()){
            System.out.println(node.toString());
        }
    }

}
