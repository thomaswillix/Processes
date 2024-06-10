#include <stdio.h>
#include <unistd.h>

int main(){
    int n=0, sum=0, cont = -1, valor = 0;
    double media = 0;
    do
    {
        printf("Introduce un número: (0 para salir) \n");
        valor = scanf("%d", &n);
        if (valor){
            sum +=n;
            cont++;
        }else{
            n=0;
            fprintf(stderr, "error");
        }
    } while (n!=0);
    media =1.0*sum/cont;
    printf("media: %.2f\n" , media);
}