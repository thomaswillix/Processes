
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

typedef struct{
    char *titulo;
    //char *titulo
    double horas;
} Juego;
/* 
Crea un juego con título 'por resolver'
y horas de juego 0 
*/
Juego* nuevoJuegoGenerico();
/*
Devuelve una cadena con los datos del
juego (titulo y horas de juego)
*/
char* toStringJuego(Juego j);

Juego* nuevoJuego();
void leerJuego(Juego *j);

char* getTitulo(Juego j);
/*
Si  a=b devuelve 0
    a<b devuelve -1
    a>b devuelve 1
    
    Se compararán por horas de juego de cada uno
*/
int compararJuego(Juego a, Juego b);
