package Controller;

import java.util.concurrent.Semaphore;

public class Calculos extends Thread {
	private int ID;
	private Semaphore semaforo;
	
	public Calculos (int ID, Semaphore semaforo) {
		this.ID = ID;
		this.semaforo = semaforo;
	}
	
	public void run () {
		double seg = 0.2;
		if (ID % 3 == 1) {
			Calculo(seg);
			Transação(seg);
			Calculo(seg);
			Transação(seg);
		}else if (ID % 3 == 2) {
			seg = seg + 0.3;
		}else {
			seg = seg + 0.8;
		}
		if (ID % 3 != 1) {
			Calculo(seg);
			Transação(seg);
			Calculo(seg);
			Transação(seg);
			Calculo(seg);
			Transação(seg);
		}
	}
	public void Transação(double seg) {
		try {
			semaforo.acquire();
			System.out.println("A Thread N"+ID+"º esta realizando uma transação de BD.");
			if (ID % 3 == 1) {
				sleep(1000);
			} else {
				sleep(1500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("A Thread N"+ID+"º terminou a transação.");
			semaforo.release();
		}
		
		
	}
	public void Calculo(double seg) {
			System.out.println("A Thread N"+ID+"º esta realizando um calculo.");
			try {
				if (ID % 3 == 1) {
					sleep((long) (Math.random()*0.9 + seg));
				} else {
					sleep((long) (Math.random()*1.1+seg));
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("A Thread N"+ID+"º terminou o calculo.");	
		
	}
}
