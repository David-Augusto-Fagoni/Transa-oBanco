package View;

import java.util.concurrent.Semaphore;

import Controller.Calculos;

public class Principal {

	public static void main(String[] args) {
		Semaphore Semaforo = new Semaphore(1);
		
		for (int J=1; J<=21;J++) {
			Calculos Calc = new Calculos(J,Semaforo);
			Calc.start();
		}

	}

}
