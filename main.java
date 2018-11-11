
public class main {
	//Constante con lugares del estacionamiento y cant maxima de autos
	static int CANTMAX = 20;
	
	
	//arreglos globales de estacionamiento y autos
	static int parking[] = new int [CANTMAX];
	static int autos[] = new int [CANTMAX];

	public static void main(String[] args) {
		
		//
		//Llamo a la función que ingresa los autos al estacionamiento
		//
		
		int cantAutos = 20;
		int ingresoAutos = ingresarAuto(cantAutos);
		
		//Muestro por consola cuántos autos fueron ingresados
		if (ingresoAutos!=-1) {
			System.out.println("PUNTO A-> La cantidad de autos ingresados hasta que hubo colisión fue de: " + ingresoAutos);
		}
		else {
			System.out.println("PUNTO A-> Se ingresaron todos los autos!");
		}
		
		System.out.println("");
		System.out.println("-----------------------------------");
		System.out.println("");
		
		//
		//Llamo a la funcion que calcula la probabilidad de colision
		//
		
		int cantAutosProb = 8;
		int repeticiones = 1000;
		
		float probColision = probColision(cantAutosProb, repeticiones);
		
		//Muestro por consola la probabilidad de colision
		System.out.println("PUNTO B-> La probabilidad de colisión si quieren ingresar " + cantAutosProb + " autos es de: " + probColision);
		System.out.println("");
		System.out.println("-----------------------------------");
		System.out.println("");
		
		//
		//Pruebo la funcion probColision con 3 numeros distintos y los valores 10, 100 y 1000 para m
		//
		
		// los tres n distintos
		int n1 = 5;
		int n2 = 7;
		int n3 = 13;
		
		// los m 10, 100 y 1000
		int m10 = 10;
		int m100 = 100;
		int m1000 = 1000;
	
		//imprimo los resultados en forma de tabla
		System.out.println("PUNTO C->");
		System.out.println("");
		System.out.println("N  " + "M=10  " + "M=100  " + "M=1000");
		System.out.println(n1 + "  " + probColision(n1, m10) + "  " + probColision(n1, m100) + "  " + probColision(n1, m1000));
		System.out.println(n2 + "  " + probColision(n2, m10) + "  " + probColision(n2, m100) + "  " + probColision(n2, m1000));
		System.out.println(n3 + "  " + probColision(n3, m10) + "  " + probColision(n3, m100) + "  " + probColision(n3, m1000));
		System.out.println("");
		System.out.println("-----------------------------------");
		System.out.println("");
		
		//
		//llamo a la funcion del inciso D con una cantidad de autos y un epsilon
		//
		
		int cantAutosEpsilon = 5;
		float epsilon = 0.0001f;
		
		probEpsilon(cantAutosEpsilon, epsilon);
	}

	private static void probEpsilon(int cantAutos, float epsilon) {
		int i = 1;
		float probAnterior = 0;
		float probActual = 0;
		float diferencia = 0;
		int casoFavorable = 0;
		
		while (i<=10 || diferencia>epsilon) {
			int resultado = ingresarAuto(cantAutos);
			
			if (resultado==-1) {
				casoFavorable++;
			}
			
			if (i>1) {
				probAnterior = probActual;
				probActual = (float) casoFavorable/i;
				diferencia = probActual-probAnterior;
				i++;
			}
			else {
				probActual = (float) casoFavorable/i;
				i++;
			}
		}
		
		System.out.println("PUNTO D-> ");
		System.out.println("");
		System.out.println("Se detuvo luego de hacer " + i + " iteraciones.");
		System.out.println("");
	}

	private static int ingresarAuto(int cantAutos) {
		int i = 0;
		int autosIngresados = 0;
		
		//Creo autos según el valor del parámetro
		crearAutos(cantAutos);
		
		//Mientras no me pase de los 20 lugares que tiene el estacionamiento, aplico la función para asignar
		//el lugar a cada auto, y según si hay o no colisiones devuelvo un resultado
		while (i<cantAutos) {
			int lugarAuto = asignarLugar(autos[i]);
			
			if (parking[lugarAuto]==0) {
				parking[lugarAuto] = autos[i];
				i++;
				autosIngresados++;
			}
			else {
				i=cantAutos;
			}	
		}
		
		//vacio el arreglo del estacionamiento
		for (int j=0; j<parking.length; j++) {
			parking[j] = 0;
		}
		
		if(autosIngresados==cantAutos) {
			return -1;
		}
		else {
			return autosIngresados;
		}
	}
	
	
	//Crea una patente y la mete en el arreglo autos
	private static void crearAutos(int cantAutos) {
		for (int i=0; i<cantAutos; i++) {
			int patente = (int) Math.floor(Math.random() * 1000);
			autos[i] = patente;
		}
	}

	//Asigna el lugar del estacionamiento según la patente
	private static int asignarLugar(int patente) {
		return patente % 20;
	}
	
	//Llama a la funcion ingresar autos una cierta cantidad de veces con cierta cantidad de autos
	//y calcula la probabilidad de colision
	private static float probColision(int cantAutos, int repeticiones) {
		int colisiones = 0;
		
		for (int i=0; i<repeticiones; i++) {
			int resultado = ingresarAuto(cantAutos);
			
			if (resultado!=-1) {
				colisiones++;
			}
		}

		return  ((float)colisiones/(float)repeticiones);
	}
}
