import java.io.*;
public class AppGrafo {
    public static void main(String[] args) {
        System.out.println("---------------------Grafo 1---------------------");
        RutaMasCorta("./src/U4_EstructurasNoLineales/grafo1.cvs");
        System.out.println("\n---------------------Grafo 2---------------------");
        RutaMasCorta("./src/U4_EstructurasNoLineales/grafo2.cvs");
        /*
        Cadiz       =   0       
        Sevilla     =   1     
        Granada     =   2    
        Jaén        =   3        
        Madrid      =   4      
        Badajoz     =   5     
        Albacete    =   6    
        Murcia      =   7      
        Valencia    =   8      
        Barcelona   =   9   
        Gerona      =   10     
        Zaragoza    =   11   
        Bilbao      =   12     
        Oviedo      =   13     
        Valladolid  =   14 
        Coruña      =   15     
        Vigo        =   16
        */
    }

    public static void RutaMasCorta(String grafoRuta){
        Lista<Vertice> grafo = new Lista<Vertice>();
        try{
            FileReader fr = new FileReader(grafoRuta);
            BufferedReader bf = new BufferedReader(fr);
            String line;

            while((line=bf.readLine())!=null){
                String [] vertice = line.split(","); // [0] = origen | [1] = destino | [2] = distancia

                if(!grafo.Buscar(new Vertice(Integer.parseInt(vertice[0])))){
                    grafo.InsertarFin(new Vertice(Integer.parseInt(vertice[0])));
                }
                grafo.Buscar(new Vertice(Integer.parseInt(vertice[0])));
                grafo.getDr().getListaAdyacente().InsertarFin(new Vertice(Integer.parseInt(vertice[1]), Integer.parseInt(vertice[2])));
            }
            fr.close();
            bf.close();
        }
        catch(FileNotFoundException e){
            System.out.println("El archivo no ha sido encontado");
        }
        catch(IOException e){
            System.out.println(e);
        }

        Imprime(grafo);
        new GrafosRecorridos().Dijkstra(grafo, 0);
        Imprime(grafo);
    }

    public static void Imprime(Lista<Vertice> G){
        Nodo<Vertice> Aux=G.getFrente();
        System.out.println("Vertice Distancia  Previo  Visitado Peso");
        while(Aux != null){
            Nodo<Vertice> AuxF=null;//Aux.getInfo().getListaAdyacente().getFrente();
            System.out.println(Aux.getInfo().toString(1));
            while(AuxF != null){
                System.out.println("\t adyacente "+AuxF.getInfo().toString(1));
                AuxF=AuxF.getSig();
            }
            Aux=Aux.getSig();
        }
        
    }
}
