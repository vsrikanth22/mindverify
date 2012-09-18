package verify.concurrency.disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Disruptor<ValueEvent> disruptor = new Disruptor<>(new ValueEventFactory(), executor,
				new SingleThreadedClaimStrategy(1024), new SleepingWaitStrategy());
		disruptor.handleEventsWith(new EventHandler<ValueEvent>() {
			@Override
			public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println(event);
			}
		});

		RingBuffer<ValueEvent> ringBuffer = disruptor.start();

		for (int i = 0; i < 1000; i++) {
			disruptor.publishEvent(new EventTranslator<ValueEvent>() {

				@Override
				public void translateTo(ValueEvent event, long sequence) {
					event.setId(sequence);
				}
			});
		}
		
		
		disruptor.shutdown();
		executor.shutdown();
	}

}
