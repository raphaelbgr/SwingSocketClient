package threads;

import java.io.IOException;

import sync.ClientStream;

public class ConnectionCheckerThread implements Runnable {

	@Override
	public void run() {
		System.out.println("test1");
		while(true) {
			System.out.println("test");
			if (ClientStream.getInstance().getSock() != null) {
				try {
					if (ClientStream.getInstance().getSock().getInputStream().read() != -1) {
						System.out.println("socket is alive");
					} else if (ClientStream.getInstance().getSock().getInputStream().read() == -1) {
						System.out.println("socket is alive and returning -1");
					}
				} catch (IOException e) {
					System.out.println("socket is dead");
					e.printStackTrace();
				} 
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
