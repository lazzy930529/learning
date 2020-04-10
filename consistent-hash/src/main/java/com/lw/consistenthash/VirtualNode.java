package com.lw.consistenthash;

import lombok.Data;

/**
 * @author liuwei
 * @date 2020-04-10 15:43
 */
@Data
public class VirtualNode implements WebNode{
    private String ip;

    private WebNode realNode;

    public VirtualNode(WebNode realNode, Integer idx) {
        this.ip = realNode.getIp() + "@" + idx;
        this.realNode = realNode;
    }


    @Override
    public void service() {
        this.realNode.service();
    }

    @Override
    public Integer getCount() {
        return null;
    }
}
