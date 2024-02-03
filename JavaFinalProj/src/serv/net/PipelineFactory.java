package serv.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import serv.codec.Decoder;

public class PipelineFactory implements ChannelPipelineFactory{


    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline next = Channels.pipeline();
        next.addLast("handler",new ChannelHandler());
        next.addLast("decoder",new Decoder());
        return next;
    }
}
