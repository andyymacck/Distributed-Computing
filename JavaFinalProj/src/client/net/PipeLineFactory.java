package client.net;

import client.codec.Decoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import client.net.ChannelHandler;

public class PipeLineFactory implements ChannelPipelineFactory{


    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline next = Channels.pipeline();
        next.addLast("handler",new ChannelHandler());
        next.addLast("decoder",new Decoder());
        return next;
    }
}