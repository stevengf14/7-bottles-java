/*
Programa para solucionar: 
Suponga que se quieren repartir 7 botellas llenas de vino, 7 botellas medias llenas, y 7 botellas vacías
entre 3 personas, de tal manera que a cada una le toque la misma cantidad de botellas y la misma cantidad de vino.
Autor: Steven Guamán - Jonathan Almeida
Email: stevengf14@gmail.com
*/

package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String args[])
	{
		CMatriz cm = new CMatriz();
		Scanner sc = new Scanner(System.in);
		int poblacion=0,iteraciones=0,fiabilidad,mejor1=0,mejor2=0,pos1=0,pos2=0,posaux=0, peor1=1000, peor2=1000, ppos1=0,ppos2=0;
		float mutacion=0;
		boolean val=false;
		int num=0;
		System.out.print("Ingrese poblacion inicial: ");
		poblacion=sc.nextInt();
		System.out.print("Ingrese Numero de iteraciones: ");
		iteraciones=sc.nextInt();
		System.out.print("Ingrese Probabilidad de mutacion (Ejemplo 0,5): ");
		mutacion=sc.nextFloat();
		System.out.print("Ingrese Fiabilidad (Valor entero entre 0 y 20): ");
		fiabilidad=sc.nextInt();
		cm.llenarDatos(poblacion);
		while(num<iteraciones)
		{
			
			num++;
			int[] fitness= new int[poblacion];
			//Calculo del fitness por individuo
			for(int i=0;i<poblacion;i++)
			{
				fitness[i]=cm.fitness(i);
				System.out.println("Individuo "+(i+1)+": Costo="+fitness[i]);
				cm.imprimirMatriz(i);
			}
			
			//Verificacion de los mejores individuos
			for(int i=0;i<poblacion;i++)
			{
				if (fitness[i]>mejor1)
				{
					   mejor1 = fitness[i];
					   pos1 = i;
				}
			}
			for(int i=0;i<poblacion;i++)
			{
				if (fitness[i]>mejor2 )
				{
					posaux=i;
					if(posaux!=pos1)
					{
						mejor2 = fitness[i];
						pos2 = i;
					}
				}
			}
			
			posaux=0;
			//Verificacion de peores individuos
			
			for(int i=0;i<poblacion;i++)
			{
				if (fitness[i]<peor1)
				{
					posaux=i;
					if(posaux!=pos1&&posaux!=pos1)
					{
					   peor1 = fitness[i];
					   ppos1 = i;
					}
				}
			}
			for(int i=0;i<poblacion;i++)
			{
				if (fitness[i]<peor2 )
				{
					posaux=i;
					if(posaux!=ppos1&&posaux!=pos1&&posaux!=pos1)
					{
						peor2 = fitness[i];
						ppos2 = i;
					}
				}
			}
			
			
			System.out.println("mejor 1: "+(pos1+1)+" mejor 2: "+(pos2+1));
			System.out.println("peor 1: "+(ppos1+1)+" peor 2: "+(ppos2+1));
			if(mejor1 >= fiabilidad)
			{
				val=true;
				break;
			}
			//Reproduccion Mejores individuos
			cm.Hijo1(pos1,pos2,ppos1,ppos2);
			cm.Hijo2(pos1,pos2,ppos1,ppos2);
			
			
			//Mutacion
			cm.MutarHijo1(ppos2,mutacion);
			cm.MutarHijo2(ppos1,mutacion);
			mejor1=0;mejor2=0;peor1=1000;peor2=1000;
		}
		
		System.out.println("\n\nIteracion: "+(num)+", Individuo: "+(pos1+1)+ "\nMejor solucion: ");
		cm.imprimirMatriz(pos1);
		
		
		
		
		
	}
	
	
}
