package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class CMatriz {
	Random r = new Random();
	int valorDado;
	int num=0;
	ArrayList<Integer[][]> individuo = new ArrayList<Integer[][]>();
	Integer[][] matriz;
	
	public void llenarDatos(int poblacion)
	{
		for(int i=0;i<poblacion;i++)
		{
			matriz= new Integer[3][3];
			for(int j=0;j<3;j++)
			{
				for(int k=0;k<3;k++)
				{
					valorDado = r.nextInt(5)+1; 
					matriz[j][k]=valorDado;
				}
				
			}
			individuo.add(matriz);
			num++;
		}
	}
	public void imprimirMatriz(int i)
	{
		String mat="";
		
			for(int j=0;j<3;j++)
			{
				for(int k=0;k<3;k++)
				{
					mat=mat+" "+individuo.get(i)[j][k];
				}
				mat=mat+"\n";
			}
		System.out.println(mat);
	}
	
	public int fitness(int i)
	{
		return fitness_botellas_repetidas(i)+fitness_cantidad_vino(i);
	}
	public int fitness_botellas_repetidas(int i)
	{
		int costo=10;
		int fila=0;
		for(int j=0;j<3;j++)
		{
			fila=0;
			for(int k=0;k<3;k++)
			{
				fila=fila+individuo.get(i)[j][k];
			}
			if(fila!=7)
				costo=costo-1;
		}
		return costo;
	}
	public int fitness_cantidad_vino(int i)
	{
		int costo=10;
		int lleno=1, vacio=0;
		float medio=(float) 0.5;
		float col1=0,col2=0,col3=0;
		
			for(int j=0;j<3;j++)
			{
				for(int k=0;k<3;k++)
				{
					if(k==0)
					{
						if(j==0)
							col1=col1+lleno*individuo.get(i)[j][k];
						else if(j==1)
							col1=col1+medio*individuo.get(i)[j][k];
						else if(j==2)
							col1=col1+vacio*individuo.get(i)[j][k];
					}
					else if(k==1)
					{
						if(j==0)
							col2=col2+lleno*individuo.get(i)[j][k];
						else if(j==1)
							col2=col2+medio*individuo.get(i)[j][k];
						else if(j==2)
							col2=col2+vacio*individuo.get(i)[j][k];
					}
					else if(k==2)
					{
						if(j==0)
							col3=col3+lleno*individuo.get(i)[j][k];
						else if(j==1)
							col3=col3+medio*individuo.get(i)[j][k];
						else if(j==2)
							col3=col3+vacio*individuo.get(i)[j][k];
					}
				}
				
			}
			if(col1!=col2)
			{
				costo=costo-2;
			}
			if(col2!=col3)
			{
				costo=costo-2;
			}
			if(col3!=col1)
			{
				costo=costo-2;
			}
		
		return costo;
	}
	public void Hijo1(int pos1, int pos2, int ppos1, int ppos2)
	{
		Integer[][] matrizHija= new Integer[3][3];
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<3;j++)
			{
				matrizHija[i][j]=individuo.get(pos1)[i][j];
			}
		}
		for(int j=0;j<3;j++)
		{
			matrizHija[2][j]=individuo.get(pos2)[2][j];
		}
		individuo.set(ppos1, matrizHija);
	}
	public void Hijo2(int pos1, int pos2, int ppos1, int ppos2)
	{
		Integer[][] matrizHija= new Integer[3][3];
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<3;j++)
			{
				matrizHija[i][j]=individuo.get(pos2)[i][j];
			}
		}
		for(int j=0;j<3;j++)
		{
			matrizHija[2][j]=individuo.get(pos1)[2][j];
		}
		individuo.set(ppos2, matrizHija);
	}
	public void MutarHijo1(int pos,float mutacion)
	{
		int valorDado=0;
		float rand1 = new Random().nextFloat();
		if(rand1<=mutacion)
		{
			for(int i=0;i<2;i++)
			{
				for(int j=0;j<3;j++)
				{
					valorDado = r.nextInt(5)+1;
					individuo.get(pos)[i][j]=valorDado;
				}
			}
		}
	}
	public void MutarHijo2(int pos,float mutacion)
	{
		int valorDado=0;
		float rand1 = new Random().nextFloat();
		if(rand1<=mutacion)
		{
			for(int j=0;j<3;j++)
			{
				valorDado = r.nextInt(5)+1;
				individuo.get(pos)[2][j]=valorDado;
			}
		}
	}
}
