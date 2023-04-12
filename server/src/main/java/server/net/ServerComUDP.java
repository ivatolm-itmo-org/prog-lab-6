package server.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;

import org.apache.commons.lang3.SerializationUtils;

import core.net.Com;
import core.net.packet.Packet;

public class ServerComUDP implements Com {
    // Channel
    private DatagramChannel channel;

    // Client address
    private SocketAddress clientAddress;

    /**
     * Constructs new {@code ClientComUDP} with provided arguments.
     *
     * @param ip server ip
     * @param port server port
     * @throws IOException if cannot open datagram channel
     */
    public ServerComUDP(String ip, Integer port) throws IOException {
        InetSocketAddress address = new InetSocketAddress(ip, port);
        this.channel = DatagramChannel.open();
        this.channel.bind(address);
        this.clientAddress = null;
    }

    /**
     * Implements {@code isAlive} method of {@code Com}.
     */
    @Override
    public boolean isAlive() {
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    /**
     * Implements {@code send} method of {@code Com}.
     */
    @Override
    public void send(Packet packet) {
        byte[] data = SerializationUtils.serialize(packet);
        ByteBuffer buffer = ByteBuffer.wrap(data);

        if (this.clientAddress == null) {
            System.err.println("Unknown destination");
            return;
        }

        try {
            this.channel.send(buffer, this.clientAddress);
        } catch (IOException e) {
            System.err.println("Cannot send packet: " + e);
        }
    }

    /**
     * Implements {@code receive} method of {@code Com}.
     */
    @Override
    public Packet receive() {
        byte[] data = new byte[16384];
        ByteBuffer buffer = ByteBuffer.wrap(data);

        try {
            this.clientAddress = this.channel.receive(buffer);
        } catch (IOException e) {
            System.err.println("Cannot receive packet: " + e);
            return null;
        }

        return SerializationUtils.deserialize(data);
    }

    /**
     * Implements {@code getChannel} method of {@code Com}.
     */
    @Override
    public SelectableChannel getChannel() {
        return this.channel;
    }

}
