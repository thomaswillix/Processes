
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

typedef struct{
    char titulo[20];
    //char *titulo
    double horas;
} Juego;
/* 
Crea una pelicula con título 'por resolver'
y minutos 0 
*/
Juego* nuevoJuegoGenerico();
/*
Devuelve una cadena con los datos del
jugador (nombre y puntos)
*/
char* toStringJuego(Juego j);

Juego* nuevoJuego(char *nombre, double horas);
void leerJuego(Juego *j);

char* getTitulo(Juego j);
/*
Si  a=b devuelve 0
    a<b devuelve -1
    a>b devuelve 1
    
    Se compararán por horas de juego de cada uno
*/
int compararJuego(Juego a, Juego b);
