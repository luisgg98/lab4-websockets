package websockets;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import websockets.web.ElizaServerEndpoint;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import static java.lang.String.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElizaServerTest {

    private static final Logger LOGGER = Grizzly.logger(ElizaServerTest.class);

	private Server server;

	@Before
	public void setup() throws DeploymentException {
		server = new Server("localhost", 8025, "/websockets",
            new HashMap<>(), ElizaServerEndpoint.class);
		server.start();
	}

	@Test(timeout = 5000)
	public void onOpen() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		List<String> list = new ArrayList<>();
		// Client endpoint ( remember bidirectional communication)
		ClientEndpointConfig configuration = ClientEndpointConfig.Builder.create().build();
		ClientManager client = ClientManager.createClient();
		// The client is connected to the server through the endpoint
        // It creates a new session
		Session session = client.connectToServer(new Endpoint() {

			@Override
			public void onOpen(Session session, EndpointConfig config) {
				session.addMessageHandler(new ElizaOnOpenMessageHandler(list, latch));
			}

		}, configuration, new URI("ws://localhost:8025/websockets/eliza"));
		// It says goodbye
        session.getAsyncRemote().sendText("bye");
        latch.await();
		assertEquals(3, list.size());
		System.out.println(list);
		assertEquals("The doctor is in.", list.get(0));
	}

	@Test(timeout = 1000)
	public void onChat() throws DeploymentException, IOException, URISyntaxException, InterruptedException {
		List<String> list = new ArrayList<>();
		ClientEndpointConfig configuration = ClientEndpointConfig.Builder.create().build();
		ClientManager client = ClientManager.createClient();
		Session session = client.connectToServer(new ElizaEndpointToComplete(list), configuration,
                new URI("ws://localhost:8025/websockets/eliza"));
		// This list contains the answers of the doctor
        // We should have any answers yet
        assertEquals(0, list.size());
        // Greet the doctor
        session.getAsyncRemote().sendText("you are the doc");
        // Speak about your feelings
        session.getAsyncRemote().sendText("i feel sad");
        // Tell the doctor something random
        session.getAsyncRemote().sendText("Grab a brush and put a little makeup");
        // Say goodbye
        session.getAsyncRemote().sendText("bye");
        Thread.sleep(250);

        assertEquals("The doctor is in.", list.get(0));
        assertEquals("What's on your mind?", list.get(1));
        assertEquals("We were discussing you, not me.", list.get(3));
        assertTrue(list.get(5).contains("feel"));
        assertEquals(9,list.size());
        System.out.println(list);
	}

	@After
	public void close() {
		server.stop();
	}

    private static class ElizaOnOpenMessageHandler implements MessageHandler.Whole<String> {

        private final List<String> list;
        private final CountDownLatch latch;

        ElizaOnOpenMessageHandler(List<String> list, CountDownLatch latch) {
            this.list = list;
            this.latch = latch;
        }

        @Override
        public void onMessage(String message) {
            LOGGER.info(format("Client received \"%s\"", message));
            list.add(message);
            latch.countDown();
        }
    }

    private static class ElizaEndpointToComplete extends Endpoint {

        private final List<String> list;

        ElizaEndpointToComplete(List<String> list) {
            this.list = list;
        }

        @Override
        public void onOpen(Session session, EndpointConfig config) {

            // COMPLETE ME!!!

            session.addMessageHandler(new ElizaMessageHandlerToComplete());
        }

        private class ElizaMessageHandlerToComplete implements MessageHandler.Whole<String> {

            @Override
            public void onMessage(String message) {
                list.add(message);
                // COMPLETE ME!!!
            }
        }
    }
}
