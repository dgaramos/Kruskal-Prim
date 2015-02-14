import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class agm {

	static int verticesLength;
	static String[] vertices;
	static int arestasLength;
	static Integer[] [] arestas;
	
	//Esse é o bloco de código responsável por ler o arquivo
	
	private static void lerGrafo(String grafo){
	
		try{
			File grafoTXT = new File(grafo);
			Scanner sc1 = new Scanner(grafoTXT);
//Nesse bloco de código ele lê quantos vértices existem
//no grafo e os coloca em um array de rotulos
			verticesLength = Integer.parseInt(sc1.next().toString());
			System.out.println(verticesLength);//verificação do numero n de vértices
			vertices = new String[verticesLength];
			for (int i=0 ; i < verticesLength; i++){
					vertices[i] = sc1.next().toString();
//linha de verificação do vetor com os rotulos dos vértices					
					System.out.println(vertices[i]);
			}
//fim do bloco de tratamento dos vértices
		
//Nesse bloco de código ele trata as arestas
//e armazena em um array multidimensional
//onde os indices 0 e 1 são o indice dos vértices
//que a aresta liga e o indice 2 é o peso da aresta
			arestasLength = Integer.parseInt(sc1.next().toString());
			System.out.println(verticesLength - 1);//Verificação do numero n de arestas
			arestas = new Integer [arestasLength] [3];
			for (int i=0 ; i < arestasLength; i++){
				for (int j = 0; j < 3 ; j++){
					arestas[i][j] = Integer.parseInt(sc1.next().toString());
//linha de verificação da matriz de representação das arestas				
				}
			}
//fim do bloco tratamento de arestas
			sc1.close();
		
		}	catch (Exception e){
			System.out.println("O arquivo que deseja ler não existe");
			e.printStackTrace();
		}
	}

//Método responsável pela execução do Algoritmo de Kruskal
	private static void kruskal(){
//Inicio do bloco de ordenação das arestas por peso
		Arrays.sort(arestas,new Comparator<Integer[]>(){
			public int compare(final Integer[] entry1, final Integer[] entry2){
				 final Integer peso1 = entry1[2];
				 final Integer peso2 = entry2[2];
				return peso1.compareTo(peso2);
			}
		});
//fim do bloco de ordenação das arestas por peso
		
		
//Inicialização do vetor que trata os vértices como subconjuntos, 
//o vetor completo é o conjunto com todos os vértices (sub árvores)				
		int[] setVert = new int [verticesLength];
		for (int i=0 ; i < verticesLength; i++){
			setVert[i] = i;
		}
	
//Inicio do bloco de montagem da matriz representando as arestas
//da Árvore Geradora Minima de resultado, analise dos casos
		int j;
		int i = 0;
		int w = 0;
		while(i < arestasLength){
			if (setVert[arestas[i][0]] == setVert[arestas[i][1]]){
				i++;	
			}
			else if (setVert[arestas[i][0]] != setVert[arestas[i][1]]){
				j = setVert[arestas[i][1]];
				System.out.println( arestas[i][0]+" "+ arestas[i][1]+ " " + arestas[i][2]);
				for (int k=0; k < verticesLength; k++ ){
					if (setVert[k] == j){
						setVert[k] = setVert[arestas[i][0]];						
					}
				}
				i++;
				w++;
			}			
		}
		
	}
	
	private static boolean checkVert(boolean[] vert)
	{
		for (int i=0 ; i < verticesLength; i++){ 
			if(!vert[i]) return false;
		}
	    return true;
	}
//Método responsável pela execução do Algoritmo de Prim	
	private static void prim(){
//Inicio do bloco de ordenação das arestas por peso
			Arrays.sort(arestas,new Comparator<Integer[]>(){
				public int compare(final Integer[] entry1, final Integer[] entry2){
					 final Integer peso1 = entry1[2];
					 final Integer peso2 = entry2[2];
					return peso1.compareTo(peso2);
				}
			});
//fim do bloco de ordenação das arestas por peso
								
//Inicialização do vetor que trata os vértices como subconjuntos, 
//o vetor completo é o conjunto com todos os vértices (sub árvores)				
			boolean[] boolVert = new boolean [verticesLength];
			for (int i=0 ; i < verticesLength; i++){
				boolVert[i] = false;
			}
			//	
			Random s = new Random();
			int y = s.nextInt(verticesLength - 1);
			boolVert[y]= true;
			//
			
//Inicio do bloco de montagem da matriz representando as arestas
//da Árvore Geradora Minima de resultado, analise dos casos
			if (!checkVert(boolVert)){
				int i=0 ;
				int j=0;
			while ( i < arestasLength){
				if (boolVert[arestas[i][0]] == true && boolVert[arestas[i][1]] == true){i++;}
				else if (boolVert[arestas[i][0]] == false && boolVert[arestas[i][1]] == false){i++; }
				else if (boolVert[arestas[i][0]] == true && boolVert[arestas[i][1]] == false){
					boolVert[arestas[i][1]] = true;
					System.out.println( arestas[i][0]+" "+ arestas[i][1] + " " + arestas[i][2]);
					i = 0;
					j++;
				}
				
				else if (boolVert[arestas[i][0]] == false && boolVert[arestas[i][1]] == true){
					boolVert[arestas[i][0]] = true;
					System.out.println( arestas[i][0]+" "+ arestas[i][1]+ " " + arestas[i][2]);
					i = 0;
					j++;
				}
				
				
			}
			}
				
			}
		
		
//=================================================================================	
//Inicio do metodo principal main	
	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("São necessários dois argumentos, sendo o primeiro qual algoritmo você deseja"); 
			System.out.println("utilizar (1 para Kruskal e 2 para Prim) e o arquivo de texto (.txt) com o "); 
			System.out.println("grafo no qual é desejado rodar o arquivo");
		}
		else{
//Inicialização do método para ler o arquivo .txt com o grafo
			lerGrafo(args[1]);
		
// Para escolher o algoritmo de Kruskal use a opção 1
// Para escolher o algoritmo de Prim é use a opção 2
			switch(args[0]){
//Algoritmo de Kruskal 			
				case "1":	
					kruskal();
						break;
				
				case "2":	
					prim();
					break;
			}
		}
	}

}
