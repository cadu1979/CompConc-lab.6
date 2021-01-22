class Vetor
{
	private int[] vet;
	private int tam; //tamanho do vetor

	//construtor
	public Vetor( int tam )
	{ 
		this.vet = new int[ tam ];
		this.tam = tam;
	}

	//inicializa todo o vetor para 3
	public void inicializaVetor()
	{
		for( int i = 0; i < this.tam; i++ )
			vet[ i ] = 3;
	}

	public void imprimeVetor()
	{
		for( int i = 0; i < this.tam; i++ )
			System.out.printf( "%d ", vet[ i ] );
			
		System.out.println();
	}

	public int getLength()
	{
		return this.tam;
	}
	
	//retorna o elemento no indice passado como argumento
	public int getElement( int indice )
	{
		return this.vet[ indice ];
	}
	
	//modifica o elemento no indice para o arg valor
	public void setElement( int indice, int valor )
	{
		this.vet[ indice ] = valor;
	}
}

//Soma os vetores A & B no vetor C 
class Somador extends Thread
{
	private int id; //identificador da thread

	Vetor vetA = somaVetor.A;
	Vetor vetB = somaVetor.B;
	Vetor vetC = somaVetor.C;
  
	public Somador ( int tid )
	{ 
		this.id = tid; 
	}

	public void run()
	{
		System.out.println( "Thread " + this.id + " iniciou." );
		for( int i = id; i < vetA.getLength(); i += somaVetor.N_THREADS )
		{
			this.vetC.setElement( i, vetA.getElement( i ) + vetB.getElement( i ) );
		}
		System.out.println( "Thread " + this.id + " terminou." ); 
	}
}

//classe da aplicacao
class somaVetor
{
	static final int N_THREADS = 4;
	static final int TAM_VETOR = 100;
	
	static Vetor A;
	static Vetor B;
	static Vetor C;

	public static void main ( String[] args )
	{
		//reserva espaço para um vetor de threads
		Thread[] threads = new Thread[ N_THREADS ];
		
		A = new Vetor( TAM_VETOR );
		B = new Vetor( TAM_VETOR );
		C = new Vetor( TAM_VETOR );
		
		A.inicializaVetor();
		B.inicializaVetor();
		C.inicializaVetor();
		
		//cria as threads da aplicacao
		for( int i = 0; i < threads.length; i++ )
		{
			threads[ i ] = new Somador( i );
		}

		//inicia as threads
		for( int i=0; i<threads.length; i++ )
		{
			threads[i].start();
		}

		//espera pelo termino de todas as threads
		for( int i=0; i<threads.length; i++ )
		{
			try { threads[i].join(); } 
			catch ( InterruptedException e ) { return; }
		}

		C.imprimeVetor();
	}
}
