#include "juego.h"

Juego* nuevoJuegoGenerico(){
    Juego* j= malloc(sizeof(Juego));

    strcpy(j->titulo, "por resolver");
    j->horas = 0;
    return j;
}
char* toStringJuego(Juego j){
    char* cadena=malloc(30*sizeof(char));
    
    sprintf(cadena,"Titulo: %s horas: %lf\n"
                ,j.titulo,j.horas);
    
    return cadena;
}
Juego* nuevoJuego(char *tit, double h){
    Juego *j=malloc(sizeof(Juego));

    strcpy(j->titulo, tit);
    j->horas=h;
}

void leerJuego(Juego *j){
    double horas=0;

    printf("Título: ");
    scanf("%s",j->titulo);
    printf("Horas: ");
    scanf("%lf",&horas);
    j->horas = horas;
}
int compararJuego(Juego a, Juego b){
    if(a.horas==b.horas)
        return 0;
    else
        if(a.horas<b.horas)
        return -1;
    else
        return 1;
}