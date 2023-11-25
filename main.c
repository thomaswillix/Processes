#include <stdio.h>
#include <stdlib.h>
#include "juego.h"
#define NUM_JUEGOS 3

int main (int argc, char** argv){
    
    Juego *juegos[NUM_JUEGOS];
    int i = 0;

    for(i = 0;i<NUM_JUEGOS; i++)
    {
        juegos[i] = nuevoJuego();
        
        //leer jugador
        leerJuego(juegos[i]);
    }
    
    //mostrar juegos desordenados
    for(i = 0;i<NUM_JUEGOS; i++)
    {
        printf("%s",toStringJuego(*juegos[i]));
    }
    //ordenar el array
    int cambios = 0;
    Juego *auxiliar;
    while (cambios>0)
    {
        for (i = 0; i < NUM_JUEGOS - 1; i++)
        {
            if (compararJuego(*juegos[i], *juegos[i+1])>0)
            {
                //intercambiar
                auxiliar=juegos[i];
                juegos[i] = juegos[i+1] ;
                juegos[i+1]=auxiliar;
                cambios++;
            }   
        }   
        printf("cambios: %d\n", cambios);
    }

    //mostrar juegos ordenados
    for(i = 0;i<NUM_JUEGOS; i++)
    {
        printf("%s",toStringJuego(*juegos[i]));
    }
    return(EXIT_SUCCESS);
}
