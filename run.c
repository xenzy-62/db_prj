#include <stdlib.h>

int main() {
    const char *command = "java -cp \".:ojdbc11.jar\" main";
    
    return system(command);
}
